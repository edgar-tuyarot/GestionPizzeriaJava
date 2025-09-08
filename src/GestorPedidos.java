import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GestorPedidos {
    private List<Pizza> menu;
    private List<Pedido> pedidos;

    public GestorPedidos() {
        this.menu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        //Agregamos unas pizzas al menu
        PizzaDAO pizza = new PizzaDAO();
        try {
            for (Pizza p : pizza.listar()) {
                menu.add(p);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }




    }


    // Metodo para agregar pizza desde consola
    public void agregarPizzaDesdeConsola(Scanner scanner) {
        limpiar();
        PizzaDAO pizza = new PizzaDAO();
        String ingredientesString = "";
        int id = 0;

        System.out.print("Nombre de la pizza: ");
        String nombre = scanner.nextLine();

        System.out.print("Tamaño (chica/mediana/grande): ");
        String tamanio = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer

        List<String> ingredientes = new ArrayList<>();
        System.out.println("Ingrese ingredientes (escriba 'fin' para terminar):");
        while (true) {
            String ing = scanner.nextLine();
            if (ing.equalsIgnoreCase("fin")){
                ingredientesString = String.join(",", ingredientes);
                break;
            }
            ingredientes.add(ing);
        }

        try {
            Pizza nuevaPizza = pizza.insertar(new Pizza(id,nombre, tamanio, precio, ingredientesString));
            id = nuevaPizza.getId();
            menu.add(nuevaPizza);
            System.out.println("✅ Pizza agregada al menú: " + nuevaPizza);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        pausar();
        limpiar();
    }

    public void registrarPedido(Pedido p) {  }

    public void guardarPedidosEnArchivo() {  }
    //Mostrar todos los pedidos
    public void mostrarHistorial() {
        limpiar();
        for (int i = 0; i < this.pedidos.size(); i++) {
            System.out.println((i + 1) + ". " + this.pedidos.get(i));
        }
        pausar();
    }
    //Muestra el menu de pizzas
    public void mostrarMenu() {
        limpiar();
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }


    }
    //Carga un pedido
    public void tomarPedidoDesdeConsola(Scanner scanner) {
        limpiar();
        ClienteDAO dao = new ClienteDAO();
        String nombre = "", telefono = "",direccion = "";
        int id = 0;

        System.out.println("Es cliente nuevo? S/N");
        String rta_cliente = scanner.nextLine();

        if(rta_cliente.equalsIgnoreCase("n")){

            try {
                for (Cliente c : dao.listar()) {
                    System.out.println(c.getId() + " - " + c.getNombre() + " - " + c.getTelefono());
                }

                System.out.println("Seleccione el numero de cliente.");
                int clienteSeleccionado = scanner.nextInt();
                for (Cliente c : dao.listar()) {
                    if(c.getId() == clienteSeleccionado){
                        nombre = c.getNombre();
                        direccion = c.getDireccion();
                        telefono = c.getTelefono();
                        id = c.getId();
                    }
                    System.out.println(c);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{

            System.out.println("Nombre del cliente");
            nombre = scanner.nextLine();
            System.out.println("Telefono");
            telefono = scanner.nextLine();
            System.out.println("Direccion");
            direccion = scanner.nextLine();
            try {
                Cliente cliente = dao.insertar(new Cliente(0,nombre,telefono,direccion));
                id = cliente.getId();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        List<Pizza> pizzasPedidas;
        double total = 0;
        boolean continuarPedido = true;

        Cliente cliente = new Cliente(id,nombre,telefono,direccion);
        pizzasPedidas = new ArrayList<>();

        System.out.println("A continuacion mostraremos el menu");

        while(continuarPedido){
            mostrarMenu();
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccionar opcion");
            int eleccion = scanner.nextInt()-1;
            pizzasPedidas.add(menu.get(eleccion));
            total = total + menu.get(eleccion).getPrecio();
            limpiar();
            verPedido(pizzasPedidas,total,"Pedido Parcial");
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccione como continuar");
            System.out.println("1.Agregar\n2.Cerrar Pedido\n");
            int rta = scanner.nextInt();
            if(rta == 2 ){
                continuarPedido = false;
                System.out.println("---------------------------------------------------------");
            }
        }
        limpiar();
        Pedido pedido = new Pedido(cliente,pizzasPedidas,total);
        this.pedidos.add(pedido);
        System.out.println(pedido);

        pausar();


    }
    //Metodo para ver el pedido
    public void verPedido( List<Pizza> pizzasPedidas, double total,String estado){
        System.out.println("-----------"+estado+"---------");
        for (int i = 0; i < pizzasPedidas.size(); i++) {
            System.out.println((i + 1) + ". " + pizzasPedidas.get(i));
        }
        System.out.println("------------------------------");
        System.out.println("Total: $"+total);
    }

    //Helpers
    public void pausar() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPresiona ENTER para continuar...");
        scan.nextLine();
    }
    public void limpiar(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}

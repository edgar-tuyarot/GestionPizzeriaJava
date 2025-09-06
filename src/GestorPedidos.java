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
        menu.add(new Pizza("Napo", "Grande", 9000, List.of("Muzza", "Tomate", "Pesto")));
        menu.add(new Pizza("Muzza", "Grande", 7500, List.of("Muzza", "oregano", "oliva")));

    }


    // Metodo para agregar pizza desde consola
    public void agregarPizzaDesdeConsola(Scanner scanner) {
        limpiar();
        System.out.print("Nombre de la pizza: ");
        String nombre = scanner.nextLine();

        System.out.print("Tamaño (chica/mediana/grande): ");
        String tamaño = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer

        List<String> ingredientes = new ArrayList<>();
        System.out.println("Ingrese ingredientes (escriba 'fin' para terminar):");
        while (true) {
            String ing = scanner.nextLine();
            if (ing.equalsIgnoreCase("fin")) break;
            ingredientes.add(ing);
        }

        Pizza nuevaPizza = new Pizza(nombre, tamaño, precio, ingredientes);
        menu.add(nuevaPizza);
        System.out.println("✅ Pizza agregada al menú: " + nuevaPizza);
        pausar();
    }

    public void registrarPedido(Pedido p) {  }
    public void guardarPedidosEnArchivo() {  }
    public void mostrarHistorial() {
        for (int i = 0; i < this.pedidos.size(); i++) {
            System.out.println((i + 1) + ". " + this.pedidos.get(i));
        }
    }

    public void mostrarMenu() {
        limpiar();
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }


    }

    public void tomarPedidoDesdeConsola(Scanner scanner) {
        limpiar();
        String nombre;
        String telefono;
        List<Pizza> pizzasPedidas;
        double total = 0;
        boolean continuarPedido = true;

        System.out.println("Nombre del cliente");
        nombre = scanner.nextLine();
        System.out.println("Telefono");
        telefono = scanner.nextLine();

        Cliente cliente = new Cliente(nombre,telefono);
        pizzasPedidas = new ArrayList<>();

        System.out.println("A continuacion mostraremos el menu, cua");

        while(continuarPedido){

            mostrarMenu();
            System.out.println("---------------------------------------------------------");
            System.out.println("Selecciona cual pizza (1,2..)");
            int eleccion = scanner.nextInt()-1;
            pizzasPedidas.add(menu.get(eleccion));
            total = total + menu.get(eleccion).getPrecio();
            limpiar();
            verPedido(pizzasPedidas,total,"Pedido Parcial");
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccione como continuar");
            System.out.println("1.Cerrar Pedido\n2.Agregar Pizza\n");
            int rta = scanner.nextInt();
            if(rta == 1 ){
                continuarPedido=false;
                System.out.println("---------------------------------------------------------");
            }
        }
        limpiar();
        Pedido pedido = new Pedido(cliente,pizzasPedidas,total);
        this.pedidos.add(pedido);
        System.out.println(pedido);

        pausar();


    }

    public void verPedido( List<Pizza> pizzasPedidas, double total,String estado){
        System.out.println("-----------"+estado+"---------");
        for (int i = 0; i < pizzasPedidas.size(); i++) {
            System.out.println((i + 1) + ". " + pizzasPedidas.get(i));
        }
        System.out.println("------------------------------");
        System.out.println("Total: $"+total);
    }

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

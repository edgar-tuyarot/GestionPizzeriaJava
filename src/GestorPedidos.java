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
    }
    public void registrarPedido(Pedido p) {  }
    public void guardarPedidosEnArchivo() {  }
    public void mostrarHistorial() {  }

    public void mostrarMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }

    }

    public void tomarPedidoDesdeConsola(Scanner scanner) {
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

        System.out.println("A continuacion mostraremos el menu");

        while(continuarPedido){
            mostrarMenu();
            System.out.println("Selecciona el numero de pizza");
            int eleccion = scanner.nextInt()-1;
            pizzasPedidas.add(menu.get(eleccion));
            total = total + menu.get(eleccion).getPrecio();

            System.out.println("Agregar otra pizza? si/no?");
            String rta = scanner.next();
            if(rta.equalsIgnoreCase("no")){
                continuarPedido=false;
            }


        }
        System.out.println("***************************************************************************");
        System.out.println("Tu pedido:");
        System.out.println("---------------------------------------------------------------------------");
        for (int i = 0; i < pizzasPedidas.size(); i++) {
            System.out.println((i + 1) + ". " + pizzasPedidas.get(i));
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Importe total: $"+total);
        System.out.println("****************************************************************************");



    }
}

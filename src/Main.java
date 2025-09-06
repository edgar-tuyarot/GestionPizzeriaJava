import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorPedidos gestor = new GestorPedidos();

        int opcion;
        do {

            System.out.println("--- Pizzería Popones ---");
            System.out.println("1. Agregar pizza al menú");
            System.out.println("2. Mostrar menú");
            System.out.println("3. Tomar pedido");
            System.out.println("4. Ver historial de pedidos");
            System.out.println("5. Guardar y salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    gestor.agregarPizzaDesdeConsola(scanner);
                    break;
                case 2:
                    gestor.mostrarMenu();

                    gestor.pausar();
                    break;
                case 3:
                    gestor.tomarPedidoDesdeConsola(scanner);
                    break;
                case 4:
                    gestor.mostrarHistorial();
                    break;
                case 5:
                    gestor.guardarPedidosEnArchivo();
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
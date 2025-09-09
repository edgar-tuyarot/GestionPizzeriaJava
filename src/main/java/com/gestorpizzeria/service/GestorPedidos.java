package main.java.com.gestorpizzeria.service;

import main.java.com.gestorpizzeria.dao.ClienteDAO;
import main.java.com.gestorpizzeria.dao.PedidoDAO;
import main.java.com.gestorpizzeria.dao.PedidosPizzasDAO;
import main.java.com.gestorpizzeria.dao.PizzaDAO;
import main.java.com.gestorpizzeria.models.Cliente;
import main.java.com.gestorpizzeria.models.Pedido;
import main.java.com.gestorpizzeria.models.PedidosPizzas;
import main.java.com.gestorpizzeria.models.Pizza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPedidos {
    private List<Pizza> menu;
    private List<Cliente>clientes;
    private List<Pedido> pedidos;
    private List<PedidosPizzas> pedidosPizzas;
    private Helpers helper = new Helpers();

    public GestorPedidos() {
        this.menu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidosPizzas = new ArrayList<>();
        //Agregamos pizzas al menu desde BBDD
        PizzaDAO pizza = new PizzaDAO();
        try {
            for (Pizza p : pizza.listar()) {
                menu.add(p);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //Agregamos pizzas al menu desde BBDD
        PedidosPizzasDAO pedidosPizzasDao = new PedidosPizzasDAO();
        try {
            for (PedidosPizzas p : pedidosPizzasDao.listar()) {
                pedidosPizzas.add(p);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //Cargamos desde bbdd los Clientes
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            for (Cliente c : clienteDAO.listar()) {
                clientes.add(c);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //Cargamos desde bbdd los pedidos
        PedidoDAO pedidoDao = new PedidoDAO();
        try {
            for (Pedido p : pedidoDao.listar()) {
                List<Pizza> pizzas = new ArrayList<>();

                for (Cliente c : clientes){
                    if(c.getId() == p.getCliente_id()){
                        p.setCliente(c);
                        pedidos.add(p);
                    }
                }
                for(PedidosPizzas pp : pedidosPizzas){
                    if(pp.getPedido_id()==p.getId()){
                        for (Pizza piz : menu){
                            if(pp.getPizza_id() == piz.getId()){
                                pizzas.add(piz);
                            }
                        }
                    }
                }
                p.setPizzas(pizzas);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }




    }


    // Metodo para agregar pizza desde consola
    public void agregarPizzaDesdeConsola(Scanner scanner) {
        helper.limpiar();
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
            System.out.println("✅ main.java.com.gestorpizzeria.models.Pizza agregada al menú: " + nuevaPizza);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.pausar();
        helper.limpiar();
    }

    public void registrarPedido(Pedido p) {  }

    public void guardarPedidosEnArchivo() {  }
    //Mostrar todos los pedidos Pendientes
    public void pedidosPendientes() {
        helper.limpiar();
        for (int i = 0; i < this.pedidos.size(); i++) {
            if(this.pedidos.get(i).getEstado().equalsIgnoreCase("Pendiente")){
                System.out.println(this.pedidos.get(i));
            }

        }
        helper.pausar();
    }
    //Mostrar todos los pedidos
    public void pedidosCompletos() {
        helper.limpiar();
        for (int i = 0; i < this.pedidos.size(); i++) {
            if(this.pedidos.get(i).getEstado().equalsIgnoreCase("Completo")){
                System.out.println(this.pedidos.get(i));
            }

        }
        helper.pausar();
    }
    //Muestra el menu de pizzas
    public void mostrarMenu() {
        helper.limpiar();
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }


    }
    //Carga un pedido
    public void tomarPedidoDesdeConsola(Scanner scanner) {
        helper.limpiar();
        //Definicion de variables

        int idPedido = 0;
        int idPedidoPizza = 0;
        int idCliente = 0;

        List<Pizza> pizzasPedidas;
        double total = 0;
        boolean continuarPedido = true;
        pizzasPedidas = new ArrayList<>();


        ClienteDAO clienteDao = new ClienteDAO();
        PedidoDAO pedidoDao = new PedidoDAO();
        PedidosPizzasDAO pedidosPizzasDao = new PedidosPizzasDAO();

        //Primero Definimos cliente, desde BBDD o Cargamos uno.

        String nombre = "", telefono = "",direccion = "";


        //Preguntamos si mostramos clientes desde BBDD o Cargamos un nuevo
        System.out.println("Es cliente nuevo? S/N");
        String rta_cliente = scanner.nextLine();

        if(rta_cliente.equalsIgnoreCase("n")){
            //Mostramos todos los clientes
            for (Cliente c : clientes) {
                System.out.println(c.getId() + " - " + c.getNombre() + " - " + c.getTelefono());
            }
            //Definimos los atributos del cliente a instanciar con los datos de la BBDD
            System.out.println("Seleccione el numero de cliente");
            int clienteSeleccionado = scanner.nextInt();
            for (Cliente c : clientes) {
                if(c.getId() == clienteSeleccionado){
                    nombre = c.getNombre();
                    direccion = c.getDireccion();
                    telefono = c.getTelefono();
                    idCliente = c.getId();
                    System.out.println(c.getId());
                    break;
                }

            }

        }else{
            //Creamos un cliente nuevo
            System.out.println("Nombre del cliente");
            nombre = scanner.nextLine();
            System.out.println("Telefono");
            telefono = scanner.nextLine();
            System.out.println("Direccion");
            direccion = scanner.nextLine();
            try {
                Cliente clienteNuevo = clienteDao.insertar(new Cliente(0,nombre,telefono,direccion));
                idCliente = clienteNuevo.getId();
                clientes.add(clienteNuevo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Instanciamos el cliente
        Cliente cliente = new Cliente(idCliente,nombre,telefono,direccion);

        //Luego Creamos el main.java.com.gestorpizzeria.models.Pedido.
        Pedido pedido = new Pedido(0,cliente.getId(),helper.fechaFormateada(),"Pendiente",total);

        try {
            //Un vez cargado el pedido de la bbdd definimos el id de ese pedido.
            idPedido = pedidoDao.insertar(pedido).getId();
            pedido.setId(idPedido);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("A continuacion mostraremos el menu");

        while(continuarPedido){
            mostrarMenu();
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccionar opcion");
            int eleccion = scanner.nextInt()-1;
            pizzasPedidas.add(menu.get(eleccion));

            try{

                PedidosPizzas pedidosPizzas = new PedidosPizzas(0,pedido.getId(),menu.get(eleccion).getId(),1);
                idPedidoPizza = pedidosPizzasDao.insertar(pedidosPizzas).getId();
                total = total + menu.get(eleccion).getPrecio();
                pedido.setTotal(total);
                pedidoDao.actualizar(pedido);
            }catch (SQLException e) {
                e.printStackTrace();
            }

            verPedido(pizzasPedidas,total,"main.java.com.gestorpizzeria.models.Pedido Parcial");
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccione como continuar");
            System.out.println("1.Agregar\n2.Cerrar main.java.com.gestorpizzeria.models.Pedido");
            int rta = scanner.nextInt();
            if(rta == 2 ){
                continuarPedido = false;
                System.out.println("---------------------------------------------------------");
                verPedido(pizzasPedidas,total,"main.java.com.gestorpizzeria.models.Pedido Cerrado");
                helper.pausar();
            }

        }
        helper.limpiar();
    }
    //Metodo para ver el pedido
    public void verPedido(List<Pizza> pizzasPedidas, double total,String estado){
        System.out.println("-----------"+estado+"---------");
        for (int i = 0; i < pizzasPedidas.size(); i++) {
            System.out.println((i + 1) + ". " + pizzasPedidas.get(i));
        }
        System.out.println("------------------------------");
        System.out.println("Total: $"+total);
    }



}

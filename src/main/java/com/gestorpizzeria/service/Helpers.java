package main.java.com.gestorpizzeria.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Helpers {
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
    public String fechaFormateada(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = ahora.format(formato);

        return fechaFormateada;
    }
}

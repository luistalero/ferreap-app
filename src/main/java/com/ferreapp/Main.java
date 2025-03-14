package com.ferreapp;

import java.sql.Connection;
import java.util.Map;

import com.ferreapp.domain.entities.Eps;
import com.ferreapp.infrastructure.adapter.ui.EpsUI;
import com.ferreapp.infrastructure.database.ConnMySql;


public class Main {
    public static void main(String[] args) {
        String entrada = """
        =========================================================
        |   SE HA ESTABLECIDO LA CONEXION A LA BASE DE DATOS    |
        =========================================================    
        """;
        String salida = """
        ===================================================        
        |    LA CONEXION A LA BASE DE DATOS HA FALLADO    |
        ===================================================
        """;
        ConnMySql conexionDB = new ConnMySql();
        try {
            Connection conexion = conexionDB.getConexion();
            if (conexion != null) {
                System.out.println(entrada);

                System.out.println("Cargando Menú Principal...");                 
                Thread.sleep(3000); // Esperar 5 segundos
                limpiarConsola();  
            }
        } catch (Exception e) {
            System.err.println(salida);
            return;
        }
        EpsUI epsUI = new EpsUI();
        Map<Integer, Eps> myEps = epsUI.FindAllEps();
        // myEps.forEach((k,v) -> System.out.println("Id: " + k + " Nombre: " + v.getName()));

        // for (Eps eps : epsUI.FindAllEpsList("Coomeva", myEps)) {
        //     System.out.println("Id: " + eps.getId() + "Nombre: " + eps.getName());
        // }
        Map<Integer, Eps> nose = epsUI.findFirstByName("Nueva", myEps);
        nose.forEach((k, v) -> System.out.println("Id: " + k + " Nombre: " + v.getName()));
        // epsUI.FindAllEpsList("Coomeva", myEps).iterator().forEachRemaining(eps -> System.out.println("Id: " + eps.getId() + "Nombre: " + eps.getName()));
    }

    private static void limpiarConsola() {
        try {
            // Intenta limpiar la consola en sistemas Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
               try {
                // Intenta limpiar la consola en sistemas Unix/Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (Exception ex) {
                // Si no se puede limpiar la consola, simplemente imprime varias líneas en blanco
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            }
        }
    }

}
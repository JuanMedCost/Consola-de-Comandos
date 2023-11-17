package com.example.proyecto;

public class ProcesoG {

    public static void aplicacion(String comando){
        ProcessBuilder escena = new ProcessBuilder(comando);
        int num;
        try{
            num = escena.start().waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


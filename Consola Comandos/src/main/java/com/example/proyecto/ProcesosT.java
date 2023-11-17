package com.example.proyecto;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.swing.JOptionPane;

public class ProcesosT extends ProcesoG{

    public static void cmd(){
        ProcessBuilder escena;
        escena = new ProcessBuilder("cmd.exe", "/C", "start");

        try{
            escena.start().waitFor();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*public static String formatoTexto(String cadena){
        byte[] bytes = cadena.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }*/
    public static void comandosT(TextArea areaT, String documento, String argumento){
        ProcessBuilder processBuilder = new ProcessBuilder("cmd","/C",argumento);
        processBuilder.directory(new File("src/main/java/com/"));
        try {
            String cadena;
            processBuilder.redirectOutput(new File(documento));
            Process p = null;
            int parar = processBuilder.start().waitFor();

            try {
                BufferedReader br = new BufferedReader(new FileReader(documento));
                String linea = br.readLine();
                while (linea != null) {
                    linea = br.readLine();
                    areaT.appendText(linea + "\n");
                }
                areaT.appendText("Fichero creado");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void destructor(String argumento){
        ProcessBuilder processBuilder = new ProcessBuilder("shutdown", argumento, "/t", "0");
        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   /*public static void mkdir(TextArea areaT){
        String regex = "\\[[0-9][0-9]:[0-9][0-9]:[0-9][0-9]\\]:";
        Random numero = new Random();
        String nombre = "";
        String respuesta = "";
        String nombreS = "";
        String[] nombreA = {"Nueva Carpeta","Nueva Carpeta(1)","Nueva Carpeta(2)","Nueva Carpeta(3)","Nueva Carpeta(4)","Nueva Carpeta(5)","Nueva Carpeta(6)",
                            "Nueva Carpeta(7)","Nueva Carpeta(8)","Nueva Carpeta(9)","Nueva Carpeta(10)"};
        try {
            areaT.appendText("Quieres ponerle nombre al directorio, si o no\n");
            BufferedReader br = new BufferedReader((new FileReader(areaT.getText())));
            String linea = br.readLine();
            while(linea != null){
                linea = br.readLine();
            }
            respuesta = areaT.getText().toLowerCase();
            if (linea.equals(regex + " si")) {
                areaT.appendText("Cual?");
                areaT.getText();
                nombre = areaT.getText();
                File f = new File("C:/" + nombre);
                f.mkdir();
                areaT.appendText("Directorio " + nombreS + " creado con exito en /C:");
            } else {
                areaT.appendText("El directorio se creara con un nombre aleatorio");
                int valor = numero.nextInt(11) + 1;
                File f = new File("C:/" + nombreA[valor]);
                f.mkdir();
                areaT.appendText("Directorio " + nombreA[valor] + " creado con exito en /C:\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void mkdir(TextArea areaT, String nombreCarpeta){
        File f = new File("C:/" + nombreCarpeta);
        f.mkdir();
        areaT.appendText("Se ha creado un directorio en \"C:/" + nombreCarpeta + "\"\n");
    }
    public static void mkdir(TextArea areaT){
        File f = new File("C:/Nueva Carpeta");
        f.mkdir();
        areaT.appendText("Se ha creado un directorio en \"C:/Nueva Carpeta\"\n" );
    }
    public static void elegirComand(String cadena, Pane ventana, TextArea areaT){
        Stage stage = (Stage) ventana.getScene().getWindow();
        if(cadena.charAt(0) == '/'){
            cadena = cadena.toLowerCase();
            String nombreCarpeta = "";
            if(cadena.length() >= 7){
                String mkdirP = cadena.substring(0,7);
                if(mkdirP.equals("/mkdir ")){
                    nombreCarpeta= cadena.substring(7,cadena.length());
                    cadena = "/mkdir";
                }
            }
            switch (cadena){

                case "/exit":
                        /*areaT.setText("La ventana se cerrara en 3 segundos...");
                        areaC.clear();
                        areaC.setText("La ventana se cerrará en 3 segundos...");
                        //areaT.appendText("La ventana se cerrara en 3 segundos...");
                        Thread.sleep(3000);
                        try {
                            Thread.sleep(3000);//No funciona bien
                            //stage.wait(3000);Peta
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }*/
                    stage.close();
                    break;

                case "/apagar":
                    destructor("/s");
                    break;

                case "/reiniciar":
                    destructor("/r");
                    break;

                case "/calc":
                    aplicacion("calc");
                    break;

                case "/cmd":
                    cmd();
                    break;

                case "/paint":
                    aplicacion("mspaint");
                    break;

                case "/help":
                    areaT.appendText( "Los comandos disponibles son:\n- /cmd: Abre el cmd\n- /clear: Limpia la terminal" +
                            "\n- /vol: Muestra la etiqueta del volumen y el número de serie del disco." +
                            "\n- /tree: Muestra la arquitectura del fichero\n- /ping: Dice a cuanto lag vas a jugar al lol\n- /ip: Sale la ip del dispositivo" +
                            "\n- /mkdir: Crea un directorio en el Disco C\n- /mkdir + nombre: Crea un directorio en el Disco C con ese nombre\n- /calc: Abre la calculadora" +
                            "\n- /paint: Abre el paint\n- /exit: po´ saleh" +
                            "\n- /apagar: Final del mundo\n- /reiniciar: Reinicia el equipo\n- /no se me ha ocurrido aun\n");
                    break;
                case "/mkdir":
                    if(nombreCarpeta != ""){
                        mkdir(areaT,nombreCarpeta);
                    }else{
                        mkdir(areaT);
                    }

                    break;

                case "/clear":
                    areaT.clear();
                    break;

                case "/ip":
                    comandosT(areaT,"ip.txt","ipconfig");
                    break;

                case "/ping":
                    comandosT(areaT,"ping.txt","ping");
                    break;

                case "/tree":
                    comandosT(areaT,"tree.txt","tree");
                    break;

                case "/vol":
                    comandosT(areaT,"vol.txt","vol");
                    break;
                default:
                    areaT.appendText("EL COMANDO INTRODUCIDO ES ERRONEO, puedes ayudarte con /help" + "\n");
            }
        }

    }
    public static void fichero(TextArea areaT){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Consola.txt", true));
            String contenido = "";
            contenido += areaT.getText();
            bw.write(contenido);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void enviarConEnter(KeyEvent event, Button eje){
        if(event.getCode() == KeyCode.ENTER){
            eje.fire();
        }
    }
}
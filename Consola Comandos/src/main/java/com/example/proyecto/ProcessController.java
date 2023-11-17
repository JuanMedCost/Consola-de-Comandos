package com.example.proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProcessController{

    @FXML
    private Pane ventana;


    @FXML
    private TextArea areaT;


    @FXML
    private TextField areaC;

    @FXML
    private Button eje;

    @FXML
    private Button calculadora;

    @FXML
    private Button cmd;

    @FXML
    private Button paint;


    @FXML
    private ProcessBuilder escena;
    private int num;

    @FXML
    protected void onCacl() {
        ProcesosT.aplicacion("calc");
    }

    @FXML
    public void onCmd() {
        ProcesosT.cmd();
    }

    @FXML
    public void onPaint() {
        ProcesosT.aplicacion("mspaint");
    }


    @FXML
    protected void onClickEje(){
        String cadena = areaC.getText();
        if(!cadena.isEmpty()){
            LocalTime tiempo = LocalTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
            String hora = formato.format(tiempo);
            areaT.appendText( "[" + hora + "]: " + cadena + "\n");
            areaC.clear();
            ProcesosT.elegirComand(cadena, ventana, areaT);
            ProcesosT.fichero(areaT);
        }

    }
    @FXML
    public void setOnKeyPressed(KeyEvent event) {
        ProcesosT.enviarConEnter(event,eje);
    }
}
package Main.controller;

import Main.model.InformacionDeConexion;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class registroUserController {
    String nom ="";

    @FXML
    private TextField nombre;


    @FXML
    void iniciar(ActionEvent event) {

        if (nombre.getText().isEmpty()) {
            System.out.println("Error no sw ingreso un nombre");
        }else {
            nom = nombre.getText();
            System.out.println(nom);
            InformacionDeConexion datos = new InformacionDeConexion();
            datos.setUser(nom);
            datos.setIp("localhost");
            datos.setPuerto(3001);
        }
        Main.Main.setFXML("Chat","WhatsUpp");
    }

}

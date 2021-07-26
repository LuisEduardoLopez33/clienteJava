package Main.controller;


import Main.model.InformacionDeConexion;
import Main.model.ThreadClient;
import Main.model.contactos;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ChatController implements  Observer, Initializable {
    private Socket socket;
    private DataOutputStream bufferDeSalida = null;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    contactos selec;




    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> usuarios;
    @FXML
    private Label conexion;

    @FXML
    private TextField txtEnviar;

    @FXML
    private Button btnEnviar;


    @FXML
    private TableView<contactos> tablaContactos;

    @FXML
    private TableColumn<contactos,String> chatsRecientes;

    @FXML
    private Label notificacion;






    ObservableList<String> mensajes = FXCollections.observableArrayList();



    @FXML
    void conectar() {
        //usuarios.setItems(conectados);
        chatsRecientes.setCellValueFactory(new PropertyValueFactory<contactos,String>("nombreContacto"));
        InformacionDeConexion datos = new InformacionDeConexion();
        try {
            socket = new Socket(datos.getIp(), datos.getPuerto());
            //log.setText( "Creado");
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.writeUTF(datos.getUser());
            bufferDeSalida.flush();

            System.out.println(datos.getUser()+"  "+ datos.getIp());

            //solicitarConexion();
            ThreadClient cliente = new ThreadClient(socket, conexion, textArea, usuarios,chatsRecientes,tablaContactos);
            cliente.addObserver( this);
            new Thread(cliente).start();
            //log.getText();
            //items.add(log);
            //listView.setItems(items);
            //escuchar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void btnEnviarOnMouseClicked() {
        selec= new contactos();
        selec= tablaContactos.getSelectionModel().selectedItemProperty().getValue();
        InformacionDeConexion datos = new InformacionDeConexion();
        if (selec == null){
            System.out.println("Seleccione a un destinatario");
        }else{
            try {

                bufferDeSalida.writeUTF(datos.getUser()+":"+selec.getNombreContacto()+":"+txtEnviar.getText());
                //bufferDeSalida.writeUTF("30");
                bufferDeSalida.flush();
                txtEnviar.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conectar();
    }


    @Override
    public void update(Observable o, Object arg) {
        String noti = (String) arg;
       if(noti.equals("2")){
           notificacion.setVisible(true);
       }
    }

}

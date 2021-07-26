package Main.model;

import Main.model.InformacionDeConexion;
import Main.model.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Observable;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadClient extends Observable implements Runnable {
    private Socket socket;
    private DataInputStream bufferDeEntrada = null;
    private Label log;
    private TextArea textArea;
    private ComboBox<String> contactos;
    boolean bandera = true;
    private TableView<contactos> tablaContactos;
    private TableColumn<contactos,String> chatsRecientes;
    contactos contac;
    Mensajes mensajes = new Mensajes();
    ObservableList<String> obd = FXCollections.observableArrayList();
    ObservableList<Mensajes> list = FXCollections.observableArrayList();
    ObservableList<contactos> chats = FXCollections.observableArrayList();
    String noti ="";

    public ThreadClient(Socket socket, Label log, TextArea textArea, ComboBox<String> contactos, TableColumn<contactos,String> chatsRecientes,TableView<contactos> tablaContactos) {
        this.socket = socket;
        this.log = log;
        this.textArea = textArea;
        this.contactos = contactos;
        this.chatsRecientes=chatsRecientes;
        this.tablaContactos= tablaContactos;
    }

    public void run(){
        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());

            String st = "";
            do {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000L)+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    st = bufferDeEntrada.readUTF();

                    //System.out.println("Recibiendo los mensajes del Servidor --> "+st);
                    String[] array = st.split(":");

                    if (array[0].equals("Conectado")){
                        InformacionDeConexion datos = new InformacionDeConexion();
                        if (array[2].equals(datos.getUser())){
                            //log.setText(st);
                            //ObservableList<String> name = FXCollections.observableArrayList();
                            System.out.println(array[1]);
                            mensajes.setUser(datos.getUser());
                            mensajes.setTexto(array[1]);
                            list.add(mensajes);
                            System.out.println(list);
                            textArea.setText(list.get(0).getTexto());
                            //name.clear();
                            obd.clear();
                            chats.clear();
                            for (int i =4; i < Arrays.stream(array).count(); i++){
                                if (datos.getUser().equals(array[i])){
                                }else{
                                    obd.add(array[i]);
                                    contactos.setItems(obd);
                                    contac = new contactos();
                                    contac.setNombreContacto(array[i]);
                                    chats.add(contac);
                                    tablaContactos.setItems(chats);
                                }
                            }


                        }else {
                            //ObservableList<String> name = FXCollections.observableArrayList();
                            System.out.println(array[2]);
                            String sms = list.get(0).getTexto();
                            mensajes.setUser(datos.getUser());
                            mensajes.setTexto(sms+"\n"+"se unio: "+array[2]);
                            list.add(mensajes);
                            textArea.setText(list.get(0).getTexto());
                            obd.clear();
                            chats.clear();
                            for (int i =4; i < Arrays.stream(array).count(); i++){
                                if (datos.getUser().equals(array[i])){
                                }else{
                                    obd.add(array[i]);
                                    contactos.setItems(obd);
                                    contac = new contactos();
                                    contac.setNombreContacto(array[i]);
                                    chats.add(contac);
                                    tablaContactos.setItems(chats);
                                }
                            }
                            //name.clear();
                        }
                    }else {



                        for (int i=0; i < list.size(); i++) {
                            if (list.get(i).getUser().equals(array[0])) {
                                String sms = list.get(i).getTexto()+"\n"+array[0]+": "+array[2];
                                mensajes.setUser(array[0]);
                                mensajes.setTexto(sms);
                                noti= "2";
                                textArea.setText(list.get(i).getTexto());
                                bandera = true;
                                i = list.size();
                            }else {
                                if (i == list.size()-1 || bandera ){
                                    bandera = false;
                                }
                            }

                            if (bandera == false){
                                mensajes.setTexto(array[0]);
                                mensajes.setTexto(array[0]+": "+array[2]);
                                list.add(mensajes);
                                noti= "2";
                                textArea.setText(list.get(i).getTexto());
                                bandera = true;
                                i = list.size();
                            }
                        }
                    }
                    this.setChanged();
                    this.notifyObservers(noti);

                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }while (!st.equals("FIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

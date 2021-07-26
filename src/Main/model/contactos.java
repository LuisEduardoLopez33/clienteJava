package Main.model;

import java.io.Serializable;

public class contactos implements Serializable {
    private int id;
    private  String nombreContacto;

    public contactos (){

    }
    public contactos(int id,String nombreContacto) {
        this.id= id;
        this.nombreContacto = nombreContacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
}

package Main.model;

public class Mensajes {
    private String user;
    private String texto;

    public Mensajes(String user, String texto){
        this.user= user;
        this.texto= texto;
    }

    public Mensajes() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

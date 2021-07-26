package Main.model;

public class InformacionDeConexion {

        static private int puerto;
        static private String ip;
        static private String user;

        public InformacionDeConexion (){}

        public InformacionDeConexion(int puerto, String ip, String user) {
            this.puerto= puerto;
            this.ip= ip;
            this.user= user;
        }

    public static int getPuerto() {
        return puerto;
    }

    public static void setPuerto(int puerto) {
        InformacionDeConexion.puerto = puerto;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        InformacionDeConexion.ip = ip;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        InformacionDeConexion.user = user;
    }
}



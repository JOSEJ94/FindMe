package com.moviles.rojas.findme;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Hector on 10/04/2017.
 */

public class ServerConnection implements Runnable {
    //Atributos
    private final static String host = "192.168.1.2";
    //final static String host = "127.0.0.1";
    private final static int puerto = 5055;
    private static Socket sc;
    private static DataOutputStream mensaje;
    private static DataInputStream entrada;
    private double longitud;
    private double latitud;
    private Usuario user;
    private String accion;

    //Constructores:
<<<<<<< HEAD
    public ServerConnection(double latitud, double longitud,Usuario user){
=======
    public ServerConnection(double latitud, double longitud, Usuario user, String accion){
>>>>>>> refs/remotes/origin/Login-
        this.latitud = latitud;
        this.longitud = longitud;
        this.user = user;
        this.accion = accion;
    }

    //Metodos de trabajo:
    @Override
    public void run() {
        try{
            System.out.println("Conectando a "+host+":"+puerto);
            //InetAddress serverAddr = InetAddress.getByName(host);
            sc = new Socket(host, puerto); // Conecta a el server
            System.out.println("Conectado");
            //Crear el stream de salida
            mensaje = new DataOutputStream(sc.getOutputStream());
            //Enviar el mensaje:
            mensaje.writeUTF("FindMe");
            mensaje.writeUTF(accion);
            switch(accion){
                case "coordenada":
                    guardaCoordenadas();
                    break;
                default:
                    break;

            }
            //cerrar conexion...
            mensaje.flush();
            mensaje.close();
            sc.close();
        }catch(Exception e){
            System.out.println("Error de conexion");
        }
    }

<<<<<<< HEAD
    public static String getHost() {
        return host;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }
=======
    public void guardaCoordenadas(){
        try{
            mensaje.writeUTF(this.user.getUsername());
            mensaje.writeUTF(this.user.getPassword());
            mensaje.writeDouble(this.latitud);
            mensaje.writeDouble(this.longitud);
        }catch(Exception e){
            //No hacer nada por ahora :v
        }
    }

>>>>>>> refs/remotes/origin/Login-
}

package com.moviles.rojas.findme;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Hector on 10/04/2017.
 */

public class ServerConnection implements Runnable {
    //Atributos
    private final static String host = "192.168.1.15";
    //final static String host = "127.0.0.1";
    private final static int puerto = 5055;
    private static Socket sc;
    private static DataOutputStream mensaje;
    private static DataInputStream entrada;
    private double longitud;
    private double latitud;
    private Usuario user;

    //Constructores:
    public ServerConnection(double latitud, double longitud, Usuario user){
        this.latitud = latitud;
        this.longitud = longitud;
        this.user = user;
    }

    //Metodos de trabajo:
    @Override
    public void run() {
        try{
            //InetAddress serverAddr = InetAddress.getByName(host);
            sc = new Socket(host, puerto); // Conecta a el server
            //Crear el stream de salida
            mensaje = new DataOutputStream(sc.getOutputStream());
            //Enviar el mensaje:
            mensaje.writeUTF("FindMe");
            mensaje.writeUTF(this.user.getUsername());
            mensaje.writeUTF(this.user.getPassword());
            mensaje.writeDouble(this.latitud);
            mensaje.writeDouble(this.longitud);
            //cerrar conexion...
            mensaje.flush();
            mensaje.close();
            sc.close();
        }catch(Exception e){
            //NO hacer nada por ahoraXD
        }
    }

}

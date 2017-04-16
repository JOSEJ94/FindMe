package com.moviles.rojas.findme;

import android.app.Application;
import android.content.Intent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

import static android.R.attr.password;

/**
 * Created by Hector on 10/04/2017.
 */

public class Conexion implements Callable<String> {
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
    public Conexion(double latitud, double longitud, Usuario user, String accion){
        this.latitud = latitud;
        this.longitud = longitud;
        this.user = user;
        this.accion = accion;
    }

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

    public String autenticar(){
        try{
            mensaje.writeUTF(this.user.getUsername());
            mensaje.writeUTF(this.user.getPassword());
            int res = entrada.readInt();
            if(res==200) {
                return "Ok";
            }
        }catch(Exception e){
            //No hacer nada por ahora :v
        }
        return "Incorrect";
    }

    @Override
    public String call() throws Exception {
        try{
            String resultado = "Done";
            System.out.println("Conectando a "+host+":"+puerto);
            //InetAddress serverAddr = InetAddress.getByName(host);
            sc = new Socket(host, puerto); // Conecta a el server
            System.out.println("Conectado");
            //Crear el stream de salida
            mensaje = new DataOutputStream(sc.getOutputStream());
            entrada = new DataInputStream(sc.getInputStream());
            //Enviar el mensaje:
            mensaje.writeUTF("FindMe");
            mensaje.writeUTF(accion);
            switch(accion){
                case Constants.NET_ACC_COORDENADA:
                    guardaCoordenadas();
                    break;
                case Constants.NET_ACC_AUTENTICAR:
                    resultado= autenticar();
                default:
                    break;
            }
            //cerrar conexion...
            mensaje.flush();
            mensaje.close();
            sc.close();
            return resultado;
        }catch(Exception e){
            System.out.println("Error de conexion");
        }
        return null;
    }
}

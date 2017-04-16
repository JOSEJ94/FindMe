/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Hector
 */
public class ClientConnection implements Runnable {

    private Socket cliente;
    //private BufferedReader entrada;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String mensajeRecibido;

    public ClientConnection(Socket cliente) {
        this.cliente = cliente;
        this.entrada = null;
        this.salida = null;
        this.mensajeRecibido = "";
    }

    @Override
    public void run() {
        try {
            System.out.println("Un cliente se ha conectado.");
            //Canales de entrada y salida de datos...
            entrada = new DataInputStream(cliente.getInputStream());
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new DataOutputStream(cliente.getOutputStream());
            System.out.println("Confrimando conexion al cliente....");
            salida.writeUTF("Conexion exitosa.. n envia un menasaje :D");

            //Recepcion de mensaje:
            //mensajeRecibido = entrada.readLine();
            mensajeRecibido = entrada.readUTF();
            System.out.println(mensajeRecibido);
            switch(mensajeRecibido){
                case "FindMe":
                    findMeConnection();
            }
            entrada.close();
            salida.flush();
            salida.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//FinDelMetodo...
    
    public void findMeConnection(){
        try{
        String opcion = entrada.readUTF();
        switch(opcion){
            case "coordenada":
                guardaCoordenada();
            break;
            case "autenticar":
                salida.writeBoolean(autenticar());
            break;
            default:
                System.out.println("Opcion indefinida.");
            break;
        }
        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
        }
    }
    
    public boolean autenticar(){
        try{
            String username = entrada.readUTF();
            String password = entrada.readUTF();
            
            for(Usuario u : Server.listaUsuarios){
                if(u.getUserName().equals(username) && u.getPassword().equals(password))
                    return true;
            }
            
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }
        return false;
    }
    
    public Coordenada guardaCoordenada(){
        Coordenada cord = new Coordenada();
        try{
        //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        cord.getUsuario().setName( entrada.readUTF());
        System.out.println("El usuario es: ");
        System.out.println(cord.getUsuario().getName());
        cord.getUsuario().setPassword(entrada.readUTF());
        System.out.println("La contraseña es: ");
        System.out.println(cord.getUsuario().getPassword());
        cord.setLatitud(entrada.readDouble());
        System.out.println("La latitud es: ");
        System.out.println(cord.getLatitud());
        cord.setLongitud(entrada.readDouble());
        System.out.println("La longitud es: ");
        System.out.println(cord.getLongitud());
        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
        }
        Server.lista.add(cord);
        return cord;
    }//FinDelMetodo...
    
}//FinDeLaClase...

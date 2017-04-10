/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.*;
import java.io.*;
/**
 *
 * @author Hector
 */
public class Server {
    //Atributos de conexion...
    final int puerto = 5055;
    ServerSocket sc;
    Socket so;
    DataOutputStream salida;
    String mensajeRecibido;
    /**
     * @param args the command line arguments
     */
    
    public void initServer(){
        BufferedReader entrada;
        
        try{
            sc = new ServerSocket(puerto);
            so = new Socket();
            
            System.out.println("Esperando una conexion");
            
            so = sc.accept();
            
            //Inicia el socket, ahora esta esperando una conexion por parte del cliente...
            
            System.out.println("Un cliente se ha conectado.");
            
            //Canales de entrada y salida de datos...
            
            entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
            salida = new DataOutputStream(so.getOutputStream());
            System.out.println("Confrimando conexion al cliente....");
            salida.writeUTF("Conexion exitosa.. n envia un menasaje :D");
            
            //Recepcion de mensaje:
            
            mensajeRecibido = entrada.readLine();
            
            System.out.println(mensajeRecibido);
            
            salida.writeUTF("Se recibio el mensaje. Terminando conexion....");
            
            salida.writeUTF("Gracias por conectarte, adios!");
            System.out.println("Cerrando conexion...");
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Server serv = new Server();
        serv.initServer();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Hector
 */
public class Conexion {
    private final int puerto = 123;
    private final String host = "localhost";
    protected String mensajeServidor;//Recibe mensajes de clientes...
    protected ServerSocket ss;//Socket del server
    protected Socket cs; //Socket del cliente...
    protected DataOutputStream salidaServidor, salidaCliente;//Streams de salida...
    
    
    public Conexion(){
        ss = new ServerSocket(puerto);
        cs = new Socket(); 
    }
}

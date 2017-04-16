/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hector
 */
public class Server {

    //Atributos de conexion...
    private final int puerto = 5055;
    private ServerSocket sc;
    private Socket so;
    public static List<Coordenada> lista;
    public static List<Usuario> listaUsuarios;

    /**
     * @param args the command line arguments
     */

    public Server() {
        Server.lista = new ArrayList<Coordenada>();
        Server.listaUsuarios = new ArrayList<Usuario>();
        Server.listaUsuarios.add(new Usuario("Alejandro", "Alejandro","123"));
        Server.listaUsuarios.add(new Usuario("Hector", "Hector","123"));
        Server.listaUsuarios.add(new Usuario("Jose","Jose","123"));
    }

    public void initServer() {
        try {
            sc = new ServerSocket(puerto);
            int i = 0;
            while (true) {
                so = new Socket();
                System.out.println("Esperando una conexion");
                so = sc.accept();
                new Thread(new ClientConnection(so)).start();
                System.out.println("Numero de llamadas hasta ahora: " + ++i + ".");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server().initServer();
    }

}

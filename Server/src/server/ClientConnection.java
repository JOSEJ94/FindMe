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
            //salida.writeUTF("Conexion exitosa.. n envia un menasaje :D");

            //Recepcion de mensaje:
            //mensajeRecibido = entrada.readLine();
            mensajeRecibido = entrada.readUTF();
            System.out.println(mensajeRecibido);
            switch (mensajeRecibido) {
                case "FindMe":
                    findMeConnection();
                    break;
                case "FindYou":
                    findYouConnection();
                    break;
                default:
                    System.out.print("Funcion indefinida.");
                    break;
            }
            entrada.close();
            salida.flush();
            salida.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//FinDelMetodo...

    public void findMeConnection() {
        try {
            String opcion = entrada.readUTF();
            switch (opcion) {
                case "coordenada":
                    guardaCoordenada();
                    break;
                case "autenticar":
                    findMeLogin();
                    break;
                case "registrarFindMe":
                    registrarFindMe();
                    break;
                default:
                    System.out.println("Opcion indefinida.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void findYouConnection() {
        try {
            String opcion = entrada.readUTF();

            switch (opcion) {
                case "autenticar":
                    findYouLogin();
                    break;
                case "getcoords":
                    cargaCoordenada();
                    break;
                default:
                    System.out.println("Opcion no encontrada.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
    }

    public Coordenada guardaCoordenada() {
        Coordenada cord = new Coordenada();
        try {
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            cord.getUsuario().setUserName(entrada.readUTF());
            System.out.println("El usuario es: ");
            System.out.println(cord.getUsuario().getUserName());
            cord.getUsuario().setPassword(entrada.readUTF());
            System.out.println("La contraseña es: ");
            System.out.println(cord.getUsuario().getPassword());
            cord.setLatitud(entrada.readDouble());
            System.out.println("La latitud es: ");
            System.out.println(cord.getLatitud());
            cord.setLongitud(entrada.readDouble());
            System.out.println("La longitud es: ");
            System.out.println(cord.getLongitud());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        Server.lista.add(cord);
        return cord;
    }//FinDelMetodo...

    public void findMeLogin() {
        try {
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String username = entrada.readUTF();
            String password = entrada.readUTF();
            System.out.println("Comprobando usuario");
            boolean found = false;
            for (Usuario u : Server.listaUsuarios) {
                if (u.getUserName().equals(username) && u.getPassword().equals(password) && u instanceof Hijo) {
                    found = true;
                }
            }
            salida.writeInt(found ? 200 : 300);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }//FinDelMetodo...

    public void findYouLogin() {
        try {
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String username = entrada.readUTF();
            String password = entrada.readUTF();
            System.out.println("Comprobando usuario");
            boolean found = false;
            for (Usuario u : Server.listaUsuarios) {
                if (u.getUserName().equals(username) && u.getPassword().equals(password) && u instanceof Padre) {
                    found = true;
                }
            }
            salida.writeInt(found ? 200 : 300);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }//FinDelMetodo...

    public void registrarFindMe() {
        try {
            boolean found = false;
            Hijo usr = new Hijo();
            usr.setUserName(entrada.readUTF());
            usr.setPassword(entrada.readUTF());
            for (Usuario u : Server.listaUsuarios) {
                if (u.getUserName().equals(usr.getUserName())) {
                    found = true;
                }
            }
            if (!found)
                Server.listaUsuarios.add(usr);     
            salida.writeInt(!found ? 200 : 0);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void registrarFindYou(){
    try {
            boolean found = false;
            Padre usr = new Padre();
            usr.setUserName(entrada.readUTF());
            usr.setPassword(entrada.readUTF());
            for (Usuario u : Server.listaUsuarios) {
                if (u.getUserName().equals(usr.getUserName())) {
                    found = true;
                }
            }
            if (!found)
                Server.listaUsuarios.add(usr);
            salida.writeInt(!found ? 200 : 300);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    
    }
    
    public void cargaCoordenada() {
        try {
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                Coordenada respuesta = null;
                if (Server.lista.size() > 0) {
                    respuesta = Server.lista.get(Server.lista.size() - 1);
                }
                String usuario = entrada.readUTF();
                String pass = entrada.readUTF();
                System.out.println("Usuario " + usuario + " con contraseña " + pass + " pide coordenadas");
                if (respuesta == null) {
                    salida.writeInt(100);
                } else {
                    salida.writeInt(200);
                    salida.writeDouble(respuesta.getLatitud());
                    salida.writeDouble(respuesta.getLongitud());
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }//FinDelMetodo...

}//FinDeLaClase...

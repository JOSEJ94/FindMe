/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Hector
 */
public class Coordenada {
    //Atributos:
    private double longitud;
    private double latitud;
    private String usuario;

    //Constructores:
    public Coordenada(double longitud, double latitud, String usuario) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.usuario = usuario;
    }
    
    public Coordenada(){
        this.longitud = 0;
        this.latitud = 0;
        this.usuario = "";
    }
    
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}

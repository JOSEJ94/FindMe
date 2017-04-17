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
public class Alerta {
    //Atributos:
    
    private Coordenada ubicacion;
    private boolean activa;
    
    //Constructores:
    
    public Alerta(){
        this.ubicacion = null;
        this.activa = false;
    }
    
    public Alerta(Coordenada ubicacion, boolean activa) {
        this.ubicacion = ubicacion;
        this.activa = activa;
    }
    
    //Seters y geters:

    public Coordenada getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Coordenada ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}

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
public class Hijo extends Usuario{
    //atributos:
    private Alerta alerta;
    
    //Constructores:

    public Hijo(String userName, String password) {
        super(userName, password);
        this.alerta = new Alerta();
    }

    public Hijo() {
        super();
        this.alerta = new Alerta();
    }
    
    //Setters y getters:

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }
    
    public void activaAlerta(Coordenada cord){
        this.alerta.setUbicacion(cord);
        this.alerta.setActiva(true);
    }
    
    public void desactivaAlerta(){
        this.alerta.setActiva(false);
    }
}

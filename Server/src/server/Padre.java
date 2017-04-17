/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hector
 */
public class Padre extends Usuario {
    //Atributos:
    private List<Hijo> lista;
    
    //Constructores:

    public Padre(String userName, String password) {
        super(userName, password);
        this.lista = new ArrayList<Hijo>();
    }

    public Padre() {
        super();
        this.lista = new ArrayList<Hijo>();
    }
    
    //Setters y getters:

    public List<Hijo> getLista() {
        return lista;
    }

    public void setLista(List<Hijo> lista) {
        this.lista = lista;
    }
    
    //Metodos de trabajo:
    
    public boolean agregaHijo(Hijo h){
        for(Hijo h2 : this.lista){
            if(h2.getUserName().equals(h.getUserName()))
                return false;
        }
        return this.lista.add(h);
    }
    
}

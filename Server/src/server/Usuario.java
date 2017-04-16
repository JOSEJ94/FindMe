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
public class Usuario {
    //Atributos:
    private String name;
    private String userName;
    private String password;
 
    //Constructores:
    public Usuario(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }
   
    public Usuario(){
        this.name = "";
        this.userName = "";
        this.password = "";
    }
    //Seters y geters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

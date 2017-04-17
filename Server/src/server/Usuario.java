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
    protected String userName;
    protected String password;
 
    //Constructores:
    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
   
    public Usuario(){
        this.userName = "";
        this.password = "";
    }
    //Seters y geters:

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

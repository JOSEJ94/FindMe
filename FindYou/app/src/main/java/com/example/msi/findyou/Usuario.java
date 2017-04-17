package com.example.msi.findyou;

public class Usuario {

    private String username;
    private String name;
    private String password;

    public Usuario() {
        this.username = "Undefined";
        this.name = "Mr. Undefined";
        this.password = "0000";
    }

    public Usuario(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getNameOfUser() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}

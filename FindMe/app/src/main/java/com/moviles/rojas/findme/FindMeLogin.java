package com.moviles.rojas.findme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.R.attr.password;


public class FindMeLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_me_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText username = (EditText) findViewById(R.id.txtUsername);
                EditText password = (EditText) findViewById(R.id.txtPassword);
                if (validacion()) {
                    ExecutorService executor = Executors.newFixedThreadPool(10);
                    Callable<String> callable = new Conexion(0,0,new Usuario(username.getText().toString(),username.getText().toString(),password.getText().toString()),Constants.NET_ACC_AUTENTICAR);
                    Future<String> future = executor.submit(callable);
                    try {
                        if(future.get()=="Ok") {
                            Intent nueva = new Intent(getApplicationContext(), FindMe01.class);
                            nueva.putExtra("username", username.getText().toString());
                            nueva.putExtra("name", username.getText().toString());
                            nueva.putExtra("password", password.getText().toString());
                            startActivity(nueva);
                        }
                        else
                            Mensaje(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        TextView txtViewCrearCuenta = (TextView) findViewById(R.id.txtViewCrearCuenta);
        txtViewCrearCuenta.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent nueva = new Intent(getApplicationContext(), FindMeCrearUsuario.class);
                startActivity(nueva);
            }
        });
    }

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};


    private boolean validacion() {
        EditText username = (EditText) findViewById(R.id.txtUsername);
        EditText password = (EditText) findViewById(R.id.txtPassword);
        if (username.getText().toString().length() == 0) {
            username.setError("Está vacío");
            return false;
        }
        if (password.getText().toString().length() == 0) {
            password.setError("Está vacío");
            return false;
        }
        return true;
    }


}

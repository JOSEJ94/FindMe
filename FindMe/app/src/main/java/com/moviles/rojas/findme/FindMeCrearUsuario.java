package com.moviles.rojas.findme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.R.attr.password;

public class FindMeCrearUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_me_crear_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnCrearUsuario = (Button) findViewById(R.id.btnCrear);
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText username = (EditText) findViewById(R.id.txtNewUsername);
                EditText password = (EditText) findViewById(R.id.txtNewPassword);
                if(validacion())
                {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    Callable<String> callable = new Conexion(0, 0, new Usuario(username.getText().toString(), username.getText().toString(), password.getText().toString()), Constants.NET_ACC_REGISTRAR_FINDME);
                    Future<String> future = executor.submit(callable);
                    try {
                        Mensaje(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }


    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    private boolean validacion() {
        EditText username = (EditText) findViewById(R.id.txtNewUsername);
        EditText password = (EditText) findViewById(R.id.txtNewPassword);
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

package com.example.msi.findyou;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindYouLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_you_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText username = (EditText) findViewById(R.id.txtUsername);
                EditText password = (EditText) findViewById(R.id.txtPassword);
                //if (validacion()) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    //Callable<String> callable = new Conexion(0, 0, new Usuario(username.getText().toString(), username.getText().toString(), password.getText().toString()), "autenticar");
                    Callable<String> callable = new Conexion(0, 0, new Usuario("Alejandro", "Alejandro", "123"), Constants.NET_ACC_GETCOORDS);
                    Future<String> future = executor.submit(callable);
                    try {
                        Mensaje(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    /*try {
                        if (future.get() == "Ok") {
                            Intent nueva = new Intent(getApplicationContext(), FindYouMap.class);
                            nueva.putExtra("username", username.getText().toString());
                            nueva.putExtra("name", username.getText().toString());
                            nueva.putExtra("password", password.getText().toString());
                            startActivity(nueva);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }*/
                //}
            }
        });
    }

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

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}

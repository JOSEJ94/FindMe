package com.moviles.rojas.findme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                    Intent nuevaActivity = new Intent(getApplicationContext(), FindMe01.class);
                    nuevaActivity.putExtra("username", username.getText().toString());
                    nuevaActivity.putExtra("name", username.getText().toString());
                    nuevaActivity.putExtra("password", password.getText().toString());
                    startActivity(nuevaActivity);
                }
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


}

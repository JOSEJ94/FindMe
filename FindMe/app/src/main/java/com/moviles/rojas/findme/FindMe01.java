package com.moviles.rojas.findme;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class FindMe01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_me01);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] permisos = {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, permisos, PackageManager.PERMISSION_GRANTED);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region Notificacion de barra (creación e invocación)
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(FindMe01.this)
                                .setSmallIcon(android.R.drawable.stat_sys_warning)
                                .setLargeIcon((((BitmapDrawable) getResources()
                                        .getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                                .setContentTitle("Alerta de Usuario")
                                .setContentText("Un dispositivo solicita su ayuda")
                                .setTicker("Alerta!");
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());
                //endregion
            }
        });
        Button btnMap = (Button) findViewById(R.id.btnMapa);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intento = new Intent(getApplicationContext(), FindMeMap.class);
                startActivity(intento);

            }
        });
        Button btnStartService = (Button) findViewById(R.id.btnIniciar);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startService(new Intent(getApplicationContext(), GPSTracker.class));
                Mensaje("Activado");
            }
        });
        Button btnStopService = (Button) findViewById(R.id.btnDetener);
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stopService(new Intent(getApplicationContext(), GPSTracker.class));
                Mensaje("Desactivado");
            }
        });
    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_me01, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

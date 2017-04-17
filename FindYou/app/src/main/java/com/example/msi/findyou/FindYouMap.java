package com.example.msi.findyou;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindYouMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_you_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //agregarMarcador(9.95426803, -84.03540552);
    }

    public void agregarMarcador(double latitud, double longitud) {
        LatLng posicion = new LatLng(latitud, longitud);
        CameraUpdate camara = CameraUpdateFactory.newLatLngZoom(posicion, 17);
        mMap.addMarker(new MarkerOptions().position(posicion).title("Marcador"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion));
        mMap.animateCamera(camara);
    }

    public void alerta(String title, String text, String ticker) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(FindYouMap.this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setLargeIcon((((BitmapDrawable) getResources()
                        .getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                .setContentTitle(title)
                .setContentText(text)
                .setTicker(ticker);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

    private class ResponseReceiver extends BroadcastReceiver {

        // Sin instancias
        private ResponseReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Constants.ACTION_RUN_SERVICE:
                    break;
                case Constants.ACTION_UPDATE:
                    Mensaje("Actualizando Posición");
                    System.out.println("Coordenadas: " + intent.getDoubleExtra(Constants.UPDATE_LATITUDE, 0) + " : " + intent.getDoubleExtra(Constants.UPDATE_LONGITUDE, 0));
                    agregarMarcador(intent.getDoubleExtra(Constants.UPDATE_LATITUDE, 0), intent.getDoubleExtra(Constants.UPDATE_LONGITUDE, 0));
                    break;
                case Constants.ACTION_STOP_SERVICE:
                    break;
                case Constants.ACTION_RUN_ISERVICE:
                    break;
            }
        }
    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}

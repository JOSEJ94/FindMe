package com.moviles.rojas.findme;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import static android.R.attr.name;
import static android.R.attr.y;
import static android.content.ContentValues.TAG;

public class GPSTracker extends Service {
    double latitud = -33.8688197;              //Coordenada de latitud
    double longitud = 151.20929550000005;      //Coordenada de longitud

    public GPSTracker() {
        super();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Iniciando Servicio", Toast.LENGTH_LONG).show();
        miUbicacion();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio Detenido", Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    LocationListener listenerGPS = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {  //Cada vez que se cambia de posición.
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void actualizarUbicacion(Location locacion) { //actualiza las coordenadas y llama a agregar el marcador
        if (locacion != null) {
            latitud = locacion.getLatitude();
            longitud = locacion.getLongitude();
            broadcast();
        }
    }

    private void broadcast()
    {
        Intent localIntent = new Intent(Constants.ACTION_UPDATE)
                .putExtra(Constants.UPDATE_LATITUDE,latitud)
                .putExtra(Constants.UPDATE_LONGITUDE,longitud);
        LocalBroadcastManager
                .getInstance(GPSTracker.this)
                .sendBroadcast(localIntent);

    }

    private void miUbicacion() { //detecta la ubicación actual del dispositivo.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location locacion = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0, listenerGPS);
    }
}
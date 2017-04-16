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

import static android.R.attr.host;
import static android.R.attr.name;
import static android.R.attr.y;
import static android.content.ContentValues.TAG;
import static android.webkit.WebViewDatabase.getInstance;

public class GPSTracker extends Service {
    double latitud = -33.8688197;              //Coordenada de latitud
    double longitud = 151.20929550000005;      //Coordenada de longitud
    Usuario usuario = null;
    boolean flag = false;

    public GPSTracker(Usuario user)
    {
        super();
        usuario = user;
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
        liberarGPS();
        Toast.makeText(this, "Servicio Detenido", Toast.LENGTH_LONG).show();
        liberarGPS();
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

    private void liberarGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(listenerGPS);
    }

    private void actualizarUbicacion(Location locacion) { //actualiza las coordenadas y llama a agregar el marcador
        if (locacion != null) {
            latitud = locacion.getLatitude();
            longitud = locacion.getLongitude();
            broadcast();
        }
    }

    private void broadcast() {
        Intent localIntent = new Intent(Constants.ACTION_UPDATE)
                .putExtra(Constants.UPDATE_LATITUDE, latitud)
                .putExtra(Constants.UPDATE_LONGITUDE, longitud);
        LocalBroadcastManager
                .getInstance(GPSTracker.this)
                .sendBroadcast(localIntent);
<<<<<<< HEAD
        new Thread(new ServerConnection(this.latitud, this.longitud, this.usuario)).start();
=======
        new Thread(new ServerConnection(this.latitud, this.longitud, FindMe01.usuario, "Coordenadas")).start();
>>>>>>> refs/remotes/origin/Login-
    }


    private void miUbicacion() { //detecta la ubicación actual del dispositivo.
        boolean flag =  false;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location locacion = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (locacion == null) {
            locacion = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            flag = true;
        }

        actualizarUbicacion(locacion);
        locationManager.requestLocationUpdates(!flag ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER, 10000, 0, listenerGPS);

        locationManager.requestLocationUpdates(!flag ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER,10000,0, listenerGPS);

    }
}

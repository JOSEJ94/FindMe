package com.moviles.rojas.findme;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.LocationListener;


public class FindMeMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;     //El mapa
    private Marker posicion;    //Marcador de Google Maps de posicion
    double latitud = 0.0;       //Coordenada de latitud
    double longitud = 0.0;      //Coordenada de longitud

    LocationListener listenerGPS = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {  //Cada vez que se cambia de posición.
            Mensaje("Actualizando posición");
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_me_map);
        // Obtain the SupportMapFragdment an get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void Mensaje(String msg){Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    private void agregarMarcador(double lat, double lon) {  //Agregar un marcador en un punto y generar una transicion de camara a esa posición
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate camara = CameraUpdateFactory.newLatLngZoom(coordenadas, 16); //16 es el nivel de zoom
        if (posicion != null) posicion.remove(); //Quita el marcador anterior
        Mensaje("Se agrego marcador");
        posicion = mMap.addMarker(new MarkerOptions().position(coordenadas)
                                                     .title("Posicion marcada"));
        mMap.animateCamera(camara);             //Anima la camara con la actualizacion de camara definida antes.
    }

    private void actualizarUbicacion(Location locacion) { //actualiza las coordenadas y llama a agregar el marcador
        if (locacion != null) {
            latitud = locacion.getLatitude();
            longitud = locacion.getLongitude();
            agregarMarcador(latitud, longitud);
        }
    }

    private void miUbicacion() { //detecta la ubicación actual del dispositivo.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Mensaje("No hay permiso");
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Mensaje("Linea 1");
        Location locacion = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
<<<<<<< HEAD
        Mensaje("Linea 2");
        actualizarUbicacion(locacion);
        Mensaje("Linea 3");
=======
>>>>>>> refs/remotes/origin/TrabajoJose
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0, listenerGPS);
        Mensaje("Linea 4");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();

    }
}

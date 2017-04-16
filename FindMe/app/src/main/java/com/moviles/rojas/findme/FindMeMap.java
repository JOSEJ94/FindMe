package com.moviles.rojas.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class FindMeMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;     //El mapa
    private Marker posicion;    //Marcador de Google Maps de posicion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_me_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
<<<<<<< HEAD

        IntentFilter filter = new IntentFilter(Constants.ACTION_RUN_ISERVICE);
=======
        IntentFilter filter = new IntentFilter(
                Constants.ACTION_RUN_ISERVICE);
>>>>>>> refs/remotes/origin/Login-
        filter.addAction(Constants.ACTION_RUN_SERVICE);
        filter.addAction(Constants.ACTION_STOP_SERVICE);
        filter.addAction(Constants.ACTION_UPDATE);

        ResponseReceiver receiver =
                new ResponseReceiver();
        // Registrar el receiver y su filtro
        LocalBroadcastManager.getInstance(this).registerReceiver(
                receiver,
                filter);

    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void agregarMarcador(double lat, double lon) {  //Agregar un marcador en un punto y generar una transicion de camara a esa posición
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate camara = CameraUpdateFactory.newLatLngZoom(coordenadas, 16); //16 es el nivel de zoom
        if (posicion != null) posicion.remove(); //Quita el marcador anterior
        posicion = mMap.addMarker(new MarkerOptions().position(coordenadas)
                .title("Posicion marcada"));
        mMap.animateCamera(camara);             //Anima la camara con la actualizacion de camara definida antes.
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

}

package com.project.tcc;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapLojasActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView textViewLoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_lojas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        textViewLoja = (TextView) findViewById(R.id.textViewLoja);
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

        // Add a marker in Sydney and move the camera
        LatLng loja = new LatLng(-23.557070, -46.659645);
        mMap.addMarker(new MarkerOptions().position(loja).title("C&A").snippet("R. Augusta, 1600 - Consolação, São Paulo - SP"));

        loja = new LatLng(-23.568278, -46.647452);
        mMap.addMarker(new MarkerOptions().position(loja).title("Marisa").snippet("Av. Paulista, 412/424 - Bela Vista, São Paulo - SP"));

        loja = new LatLng(-23.565633, -46.650823);
        mMap.addMarker(new MarkerOptions().position(loja).title("Forever 21").snippet("Top Center - Av. Paulista, 854 - Bela Vista, São Paulo - SP"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loja, 15));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(loja));

        loja = new LatLng(-23.564339, -46.652557);
        mMap.addMarker(new MarkerOptions().position(loja).title("Renner").snippet("Av. Paulista, 1106 - Bela Vista, São Paulo - SP"));

//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.shopping_128))
//                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
//                .position(loja).title("Renner"));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                setTextLoja(textViewLoja, marker.getSnippet());
            }
        });

    }

    private void setTextLoja(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
}

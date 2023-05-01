package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    double latitude;
    double longitude;
    int zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // You can add your java code to get latitude, longitude, and zoom level here
        Bundle extras = getIntent().getExtras();
        latitude = extras.getDouble("latitude");
        longitude = extras.getDouble("longitude");
        zoom = extras.getInt("zoom");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add Java code for a marker in Calvary and move the camera

        LatLng calvaryHospital = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .title("Calvary Hospital")
                .position(calvaryHospital)
                .draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(calvaryHospital, zoom));

        // Answer ends here

    }
}
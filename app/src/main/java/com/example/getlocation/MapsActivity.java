package com.example.getlocation;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double latitude;
    private Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        latitude = getIntent().getDoubleExtra("LAT",1);
        longitude = getIntent().getDoubleExtra("LON",1);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        //PolyLine
        LatLng HR = new LatLng(22.862569, 91.090780);
        LatLng Noakhali = new LatLng(latitude,longitude);
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(HR);
        polylineOptions.add(Noakhali);
        polylineOptions.color(Color.GREEN);
        polylineOptions.jointType(JointType.BEVEL);
        polylineOptions.width(20);

        mMap.addPolyline(polylineOptions);

        // Add a marker in Sydney and move the camera
       // LatLng HR = new LatLng(22.862569, 91.090780);
        mMap.addMarker(new MarkerOptions().position(HR).title("Marker in Noakhali").icon(BitmapDescriptorFactory.fromResource(R.drawable.resizeimage)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Noakhali));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(HR).zoom(16).build(); //for zooming
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //to animate


        //LatLng Noakhali = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(Noakhali).title("Marker in Noakhali").icon(BitmapDescriptorFactory.fromResource(R.drawable.resizeimage)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Noakhali));
        CameraPosition cameraPosition2 = new CameraPosition.Builder().target(Noakhali).zoom(16).build(); //for zooming
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition2)); //to animate
    }
}

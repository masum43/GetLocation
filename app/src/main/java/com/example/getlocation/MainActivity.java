package com.example.getlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Double latitude,longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationCallback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) { //ei method er maddhomei amra location ta pabo
                super.onLocationResult(locationResult);

                if(locationResult!=null)
                {

                    for(Location location:locationResult.getLocations())
                    {

                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        Toast.makeText(MainActivity.this, latitude+"  "+longitude, Toast.LENGTH_SHORT).show();
                    }
                }


            }
        };

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        createLocationRequest();


    }

    private void createLocationRequest() {

        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000); //1000 means 1 sec
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},101);

            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);


    }
}

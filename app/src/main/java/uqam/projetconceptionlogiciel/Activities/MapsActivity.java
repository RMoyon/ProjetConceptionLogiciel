package uqam.projetconceptionlogiciel.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uqam.projetconceptionlogiciel.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnCameraIdleListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        mMap.setOnCameraIdleListener(this);

        // Add a marker in Montreal and move the camera
        LatLng initialPosition = new LatLng(45.509252, -73.568441);
        mMap.addMarker(new MarkerOptions().position(initialPosition).title("UQAM - Salle de cours MGL7361").snippet("Contenu de l'event"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initialPosition,15));

        /*mMap.setLocationSource(this);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        Location loc = mMap.getMyLocation();
        if(loc != null){
            LatLng point = new LatLng(loc.getLatitude() , loc.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(point,15));
        }*/

    }

    @Override
    public void onCameraIdle() {
        Toast.makeText(this, "The camera has stopped moving.",
                Toast.LENGTH_SHORT).show();
    }

    /*public void getPlaces(){
        private IPlacesDAL placesDAL = new PlacesDAL();
        LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        placesDAL.closestGreatDeals(bounds.northeast.latitude, bounds.southwest.latitude, bounds.southwest.longitude,bounds.northeast.longitude, 15)
                .subscribe(new Consumer<Response<Object []>>() {
                    @Override
                    public void accept(Response<Object []> greatDeals) {
                        for (int i = 0 ; i < greatDeals.length() ; i++ ) {
                            //TODO En remplissant les Pins avec : (int) greatDeals[0].body().getId()
                        }
                    }
                });
    }*/
}

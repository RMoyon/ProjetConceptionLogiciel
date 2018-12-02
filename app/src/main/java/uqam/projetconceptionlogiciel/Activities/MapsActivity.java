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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IPlaceDAL;
import uqam.projetconceptionlogiciel.Model.Place;
import uqam.projetconceptionlogiciel.R;
import uqam.projetconceptionlogiciel.Retrofit.DAL.PlaceDAL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnCameraIdleListener {

    private GoogleMap mMap;
    private List<Place> listPlaces = new ArrayList<>();

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
        /*Toast.makeText(this, "The camera has stopped moving.",
                Toast.LENGTH_SHORT).show();*/
        getPlaces();
    }

    public void getPlaces() {
        IPlaceDAL placeDAL = new PlaceDAL();
        LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        final CountDownLatch latch = new CountDownLatch(1);
        //List<Place> test = new ArrayList<>();

        /*placeDAL.getClosestPlaces(bounds.northeast.latitude, bounds.southwest.latitude, bounds.southwest.longitude,bounds.northeast.longitude, 15)
                .subscribe(new Consumer<Response<List<Place>>>() {

                    @Override
                    public void accept(Response<List<Place>> response) {
                        //System.out.println(response.body().size());
                        //listPlaces = response.body();
                        latch.countDown();
                    }
                });

        try {
            latch.await();*

        }catch(InterruptedException e){
            System.out.println("Erreur InterruptedException");
        }*/

        placeDAL.getClosestPlaces(	45.515812, 45.503181, -73.575429, -73.562812, 5)
                .subscribe(new Consumer<Response<List<Place>>>() {
                    @Override
                    public void accept(Response<List<Place>> response) {
                        //Assert.assertEquals(1, response.body().size());
                        latch.countDown();
                    }
                });

        if (listPlaces.isEmpty()) {
            Toast.makeText(this, "Pins Updated",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pins not updated",
                    Toast.LENGTH_SHORT).show();
        }

    }
}

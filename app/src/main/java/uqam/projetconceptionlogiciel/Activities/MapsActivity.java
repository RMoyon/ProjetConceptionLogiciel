package uqam.projetconceptionlogiciel.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IPlaceDAL;
import uqam.projetconceptionlogiciel.InfoWindow.InfoWindowCustom;
import uqam.projetconceptionlogiciel.InfoWindow.InfoWindowData;
import uqam.projetconceptionlogiciel.Model.Place;
import uqam.projetconceptionlogiciel.ObjectPool.MarkerPool;
import uqam.projetconceptionlogiciel.R;
import uqam.projetconceptionlogiciel.Retrofit.DAL.PlaceDAL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnCameraIdleListener {

    public GoogleMap mMap;
    private Map<Marker, Place> listPlaces = new HashMap<>();
    private IPlaceDAL placeDAL = new PlaceDAL();
    private MarkerPool markerPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);
        this.markerPool = new MarkerPool(15, mMap);

        // Move the camera to Montreal
        LatLng initialPosition = new LatLng(45.497785, -73.580473);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initialPosition,15));

        InfoWindowCustom customInfoWindow = new InfoWindowCustom(this);
        mMap.setInfoWindowAdapter(customInfoWindow);

    }

    @Override
    public void onCameraIdle() {
        try {
            this.getPlaces();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getPlaces() throws InterruptedException {

        final LatLngBounds curScreen = mMap.getProjection().getVisibleRegion().latLngBounds;

        //Recuperation des places comprises dans les bordures de notre vue
        placeDAL.getClosestPlaces(curScreen.northeast.latitude, curScreen.southwest.latitude, curScreen.southwest.longitude, curScreen.northeast.longitude, 15)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Response<List<Place>>>() {
            @Override
            public void accept(final Response<List<Place>> places) {
                returnMarkersToPool();
                //Marker tempMarker;

                if (places.body() != null){
                    System.out.println("Taille liste lieux : "+ places.body().size());
                    for(int i =0; i<places.body().size(); i++) {
                        Marker tempMarker = markerPool.acquireReusable();
                        listPlaces.put(tempMarker, places.body().get(i));
                        setMarker(tempMarker);
                        System.out.println("NB enregistré dans la map : "+ listPlaces.size() + " // boucle n°"+ i);
                        System.out.println("\nType lieux " +places.body().get(i)+" // Type Marker : "+tempMarker);
                    }

                } else {
                    System.out.println("places null");
                }
            }
        });
    }

    public void setMarker(Marker tempMarker){

            Place tempPlace = listPlaces.get(tempMarker);

            tempMarker.setPosition(new LatLng(tempPlace.getLatitude(), tempPlace.getLongitude()));
            setMarkerText(tempMarker);
            setMarkerColor(tempMarker);
            tempMarker.setVisible(true);
            System.out.println("Nom marker "+tempPlace.getName());

            //Activation de l'infobulle
            mMap.setOnMarkerClickListener(
                    new GoogleMap.OnMarkerClickListener() {
                        boolean doNotMoveCameraToCenterMarker = true;
                        public boolean onMarkerClick(Marker marker) {
                            marker.showInfoWindow();
                            return doNotMoveCameraToCenterMarker;
                        }
                    });

        }

    public void returnMarkersToPool(){
        //Retourner l'ensemble des markers à la piscine
        for(Iterator<Map.Entry<Marker, Place>> it = listPlaces.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Marker, Place> entry = it.next();
            markerPool.returnReusable(entry.getKey());
            it.remove();
        }
    }

    public void setMarkerText(Marker marker){
        Place tempPlace = listPlaces.get(marker);
        InfoWindowData info = new InfoWindowData(tempPlace.getName(),tempPlace.getGreatDeals().get(0).getName(),tempPlace.getGreatDeals().get(0).getDescription());
        marker.setTag(info);
    }

    public void setMarkerColor(Marker marker){
        switch(listPlaces.get(marker).getGreatDeals().get(0).getType()){
            case "Réduction" :
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                break;
            case "Festival" :
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                break;
            case "Evenement" :
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            default :
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                break;
        }
    }

}

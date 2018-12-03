package uqam.projetconceptionlogiciel.ObjectPool;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.List;

public class MarkerPool {

    private static HashSet<Marker> available = new HashSet<Marker>();
    private static HashSet<Marker> inUse = new HashSet<Marker>();
    private static final LatLng defaultPosition = new LatLng(0.0,0.0);
    private GoogleMap mMap;

    private int capacity;

    public MarkerPool(int capacity, GoogleMap mMap) {
        this.available = new HashSet<Marker>();
        this.inUse = new HashSet<Marker>();
        this.capacity = capacity;
        this.mMap = mMap;
    }

    public Marker acquireReusable(){

        //Ne rien faire si tous les marqueurs sont pris et que la capacité de la Pool est pleine
        if (available.isEmpty() && inUse.size() == capacity)
            return null;
        //Si tous les marqueurs sont pris, en créer un
        else if (available.isEmpty()){
            Marker newMarker = mMap.addMarker(new MarkerOptions()
                    .position(defaultPosition)
                    .draggable(false).visible(false));
            available.add(newMarker);
            return newMarker;
        }
        //Donner un marqueur disponible
        else{
            Marker returnMarker = available.iterator().next();
            available.remove(returnMarker);
            inUse.add(returnMarker);
            return returnMarker;
        }
    }

    public void returnReusable(Marker marker){

        if (marker != null){
            marker.setPosition(defaultPosition);
            marker.setSnippet("");
            marker.setTitle("");
            marker.setVisible(false);
            inUse.remove(marker);
            available.add(marker);
        }

    }

    public void returnReusables(List<Marker> markers){
        for(Marker m : markers) returnReusable(m);
    }
}

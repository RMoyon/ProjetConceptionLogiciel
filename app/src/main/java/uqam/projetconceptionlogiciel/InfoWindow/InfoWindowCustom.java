package uqam.projetconceptionlogiciel.InfoWindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import uqam.projetconceptionlogiciel.R;

public class InfoWindowCustom implements GoogleMap.InfoWindowAdapter {
    Context context;
    LayoutInflater inflater;
    public InfoWindowCustom(Context context) {
        this.context = context;
    }
    @Override
    public View getInfoContents(Marker marker) {
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.echo_info_window, null);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView subtitle = (TextView) v.findViewById(R.id.description);

        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        name.setText(infoWindowData.getName());
        title.setText(infoWindowData.getTitle());
        subtitle.setText(infoWindowData.getDescription());
        return v;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
}

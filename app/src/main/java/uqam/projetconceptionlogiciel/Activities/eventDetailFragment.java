package uqam.projetconceptionlogiciel.Activities;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.R;
import uqam.projetconceptionlogiciel.dummy.DummyContent;

/**
 * A fragment representing a single event detail screen.
 * This fragment is either contained in a {@link eventListActivity}
 * in two-pane mode (on tablets) or a {@link eventDetailActivity}
 * on handsets.
 */
public class eventDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private GreatDeal mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public eventDetailFragment() {
    }

    public static eventDetailFragment newInstance(GreatDeal greatDeal) {
        eventDetailFragment myFragment = new eventDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_ID, greatDeal);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = (GreatDeal) getArguments().getSerializable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }

            if (mItem != null) {
                ((TextView) getView().findViewById(R.id.event_detail)).setText(mItem.getDescription());
            }
        }
    }
}

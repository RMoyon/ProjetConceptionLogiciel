package uqam.projetconceptionlogiciel.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IGreatDealDAL;
import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.R;
import uqam.projetconceptionlogiciel.Retrofit.DAL.GreatDealDAL;
import uqam.projetconceptionlogiciel.adapter.GreatDealAdapter;
import uqam.projetconceptionlogiciel.adapter.GreatDealItemListener;


/**
 * An activity representing a list of events. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link eventDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class eventListActivity extends AppCompatActivity implements GreatDealItemListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private IGreatDealDAL greatDealDAL = new GreatDealDAL();
    private List<GreatDeal> greatDealList = new ArrayList<>();
    private GreatDealAdapter adapter = null;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bouton qui permettra d'ajouter un évènement", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.event_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = (RecyclerView) findViewById(R.id.event_list);
        setupRecyclerView();

        try {
            this.GetClosestGreatDeals();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void GetClosestGreatDeals() throws InterruptedException {
        greatDealDAL.getClosestGreatDeals(-73.569120, 45.509496, 10)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Response<List<GreatDeal>>>() {
            @Override
            public void accept(final Response<List<GreatDeal>> greatDeals) {
                if (greatDeals.body() != null) {
                    setGreatDealList(greatDeals.body());
                } else {
                }
            }
        });

    }

    public void setGreatDealList(List<GreatDeal> greatDealList) {
        this.greatDealList = greatDealList;
        if (this.greatDealList != null) {
            this.adapter = new GreatDealAdapter(this.greatDealList, this);
            this.recyclerView.setAdapter(this.adapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calendar:
                Intent intent_calendar = new Intent(this, CalendarActivity.class);
                startActivity(intent_calendar);
                return true;
            case R.id.map:
                Intent intent_map = new Intent(this, MapsActivity.class);
                startActivity(intent_map);
                return true;
            case R.id.action_login:
                Intent intent_login = new Intent(this, LoginActivity.class);
                startActivity(intent_login);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setupRecyclerView() {
        this.adapter = new GreatDealAdapter(new ArrayList<GreatDeal>(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void goToGreatDealDetails(GreatDeal greatDeal) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(eventDetailFragment.ARG_ITEM_ID, greatDeal);
            eventDetailFragment fragment = new eventDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.event_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, eventDetailActivity.class);
            intent.putExtra(eventDetailFragment.ARG_ITEM_ID, (Serializable) greatDeal);
            startActivity(intent);
        }
    }
}

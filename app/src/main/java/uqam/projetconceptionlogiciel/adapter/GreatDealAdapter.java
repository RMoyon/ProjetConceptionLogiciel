package uqam.projetconceptionlogiciel.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.R;

public class GreatDealAdapter extends RecyclerView.Adapter<GreatDealAdapter.GreatDealViewHolder> {

    @NonNull
    private List<GreatDeal> items;
    @Nullable
    private GreatDealItemListener listener;

    public GreatDealAdapter(List<GreatDeal> items, GreatDealItemListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public GreatDealAdapter.GreatDealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_content, parent, false);

        return new GreatDealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GreatDealAdapter.GreatDealViewHolder holder, int position) {
        final GreatDeal item = items.get(position);
        holder.eventName.setText(item.getName());
        if (listener != null) {
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item != null){
                        listener.goToGreatDealDetails(item);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class GreatDealViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;
        public View root;

        public GreatDealViewHolder(View view) {
            super(view);
            root = view;
            eventName = (TextView) view.findViewById(R.id.content);
        }
    }

}

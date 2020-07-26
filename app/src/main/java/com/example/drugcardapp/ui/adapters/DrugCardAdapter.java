package com.example.drugcardapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugcardapp.R;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DrugCardAdapter extends RecyclerView.Adapter<DrugCardAdapter.ViewHolder>  implements Filterable {
private List<DrugCardEntity> mcards;
    private List<DrugCardEntity> cardsFull;
private final Context mcontext;

public NavController mNavController;
private SharedViewModel model;

public DrugCardAdapter(List<DrugCardEntity> mcards, SharedViewModel model, Context mcontext, NavController navController){
    this.mcards = mcards;
    this.mcontext = mcontext;
    this.model = model;
    mNavController = navController;
    cardsFull = new ArrayList<>(mcards);
}
public void replaceList(List<DrugCardEntity> cards){
    mcards = cards;
}

@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.card_list_item, parent, false);
    return new ViewHolder(view);
}
@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
    final DrugCardEntity card = mcards.get(position);
    holder.nameTextView.setText(card.getGenericName());
    holder.classTextView.setText(card.getDrugClass());
    holder.systemTextView.setText(card.getDrugSystem());
    holder.editFab.setOnClickListener(v -> {
        model.select(card);
        mNavController.navigate(R.id.action_library_to_card);
        });
}
@Override
    public int getItemCount(){
    return mcards.size();
}

    @Override
    public Filter getFilter() {
        return drugCardFilter;
    }
    private Filter drugCardFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DrugCardEntity> filterCards = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterCards.addAll(cardsFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for(DrugCardEntity card : cardsFull){
                    if(card.getGenericName().toLowerCase().contains(filter) ||
                    card.getTradeName().toLowerCase().contains(filter) ||
                    card.getDrugClass().toLowerCase().contains(filter) ||
                    card.getDrugSystem().toLowerCase().contains(filter)) {
                        filterCards.add(card);
                    }}
                }
            FilterResults results = new FilterResults();
            results.values = filterCards;
            return results;
            }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mcards.clear();
            mcards.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView nameTextView;
    TextView classTextView;
    TextView systemTextView;
    FloatingActionButton editFab;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.name_text);
        classTextView = itemView.findViewById(R.id.class_text);
        systemTextView = itemView.findViewById(R.id.system_text);
        editFab = itemView.findViewById(R.id.fab_edit);
    }
}
}

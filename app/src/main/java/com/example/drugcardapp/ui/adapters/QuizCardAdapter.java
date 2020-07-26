package com.example.drugcardapp.ui.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugcardapp.R;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;


public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.ViewHolder>  implements Filterable {
    public static List<DrugCardEntity> mcards;
    private List<DrugCardEntity> cardsFull;
    public List<DrugCardEntity> checkedCards;
    private final Context mcontext;

    public NavController mNavController;
    private SharedViewModel model;

    static SparseBooleanArray itemStateArray;

    public QuizCardAdapter(List<DrugCardEntity> mcards, SharedViewModel model, Context mcontext, NavController navController) {
        this.mcards = mcards;
        this.mcontext = mcontext;
        this.model = model;
        mNavController = navController;
        cardsFull = new ArrayList<>(mcards);
        checkedCards = new ArrayList<>();
        itemStateArray = new SparseBooleanArray();
    }

    public void replaceList(List<DrugCardEntity> cards) {
        mcards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_card_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DrugCardEntity card = mcards.get(position);
        holder.nameTextView.setText(card.getGenericName());
        holder.classTextView.setText(card.getDrugClass());
        holder.systemTextView.setText(card.getDrugSystem());
        holder.bind(position);
        if (card.isSelected()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
       holder.checkBox.setOnClickListener(v -> {
            CheckBox checkBox = (CheckBox) v;
            DrugCardEntity currentCard = mcards.get(position);
            if (checkBox.isChecked()) {
                currentCard.setSelected(true);
                checkedCards.add(currentCard);
            } else {
                currentCard.setSelected(false);
                checkedCards.remove(currentCard);
            }
        });
    }

    @Override
    public int getItemCount() {
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

            if (constraint == null || constraint.length() == 0) {
                filterCards.addAll(cardsFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (DrugCardEntity card : cardsFull) {
                    if (card.getGenericName().toLowerCase().contains(filter) ||
                            card.getTradeName().toLowerCase().contains(filter) ||
                            card.getDrugClass().toLowerCase().contains(filter) ||
                            card.getDrugSystem().toLowerCase().contains(filter)) {
                        filterCards.add(card);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterCards;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mcards.clear();
            mcards.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView classTextView;
        TextView systemTextView;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text);
            classTextView = itemView.findViewById(R.id.class_text);
            systemTextView = itemView.findViewById(R.id.system_text);
            checkBox = itemView.findViewById(R.id.chk_box);

            checkBox.setOnClickListener(this);
        }

        void bind(int position) {
            if (!itemStateArray.get(position, false)) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                checkBox.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            } else {
                checkBox.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }
        }
    }
}

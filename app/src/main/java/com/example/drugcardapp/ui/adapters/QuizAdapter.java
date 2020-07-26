package com.example.drugcardapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugcardapp.R;
import com.example.drugcardapp.database.QuizEntity;
import com.example.drugcardapp.ui.quiz.QuizViewModel;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>  implements Filterable {
    private List<QuizEntity> mquizzes;
    private List<QuizEntity> quizzesFull;
    private final Context mcontext;

    public NavController mNavController;
    private SharedViewModel model;
    private QuizViewModel quizModel;

    public QuizAdapter(List<QuizEntity> mquizzes, SharedViewModel model, QuizViewModel quizModel, Context mcontext, NavController navController){
        this.mquizzes = mquizzes;
        this.mcontext = mcontext;
        this.model = model;
        this.quizModel = quizModel;
        mNavController = navController;
        quizzesFull = new ArrayList<>(mquizzes);
    }
    public void replaceList(List<QuizEntity> quizzes){
        mquizzes = quizzes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        final QuizEntity quiz = mquizzes.get(position);
        holder.nameTextView.setText(quiz.getQuizName());
        holder.deleteFab.setOnClickListener(v -> {
            quizModel.deleteQuiz(quiz);
        });
        holder.editFab.setOnClickListener(v -> {
            model.selectQuiz(quiz);
            mNavController.navigate(R.id.action_quiz_to_edit);
        });
        holder.playFab.setOnClickListener(v -> {
            if(quiz.getQuizCards().size() > 0) {
                model.selectQuiz(quiz);
                mNavController.navigate(R.id.action_quiz_to_start);
            }
            else {
                Toast.makeText(v.getContext(),"This quiz is empty! You must first add cards to this quiz.", LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount(){
        return mquizzes.size();
    }

    @Override
    public Filter getFilter() {
        return quizFilter;
    }
    private Filter quizFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<QuizEntity> filterQuiz = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterQuiz.addAll(quizzesFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for(QuizEntity quiz : quizzesFull){
                    if(quiz.getQuizName().toLowerCase().contains(filter)) {
                        filterQuiz.add(quiz);
                    }}
            }
            FilterResults results = new FilterResults();
            results.values = filterQuiz;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mquizzes.clear();
            mquizzes.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        FloatingActionButton editFab;
        FloatingActionButton playFab;
        FloatingActionButton deleteFab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.quiz_name_text);
            editFab = itemView.findViewById(R.id.fab_edit_quiz);
            playFab = itemView.findViewById(R.id.fab_play_quiz);
            deleteFab = itemView.findViewById(R.id.fab_delete_quiz);
        }
    }
}
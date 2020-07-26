package com.example.drugcardapp.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.drugcardapp.R;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CardFrontFragment extends Fragment{

    private CardFrontViewModel quizViewModel;
    private SharedViewModel model;
    private ArrayList<DrugCardEntity> cards = new ArrayList<>();
    private int quizID;
    private TextView cardName;

    public View root;
    public NavController mNavController;
    public int i;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(CardFrontViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_card_front, container, false);
        }
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(false);

        initViewModel();
        cardName = view.findViewById(R.id.generic_text);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        Button flipButton = view.findViewById(R.id.flip_card_btn);
        flipButton.setOnClickListener(v -> {
            model.setIndex(i);
            mNavController.navigate(R.id.action_front_to_back);
        });
    }

    private void initViewModel() {
        quizViewModel = new ViewModelProvider(this)
                .get(CardFrontViewModel.class);
        cards.clear();
        i = 0;
        model.getSelectedQuiz().observe(getViewLifecycleOwner(), QuizEntity -> {
            if (QuizEntity != null) {
                i = model.getIndex();
                quizID = QuizEntity.getQuizID();
                cards = QuizEntity.getQuizCards();
                if(cards.size() > 0) {
                    cardName.setText(cards.get(i).getGenericName());
                }
            }
            quizViewModel.loadData(quizID);
        });
    }

}

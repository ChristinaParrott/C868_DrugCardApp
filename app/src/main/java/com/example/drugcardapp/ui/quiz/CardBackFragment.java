package com.example.drugcardapp.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class CardBackFragment extends Fragment{

    private CardBackViewModel quizViewModel;
    private SharedViewModel model;
    private ArrayList<DrugCardEntity> cards = new ArrayList<>();
    private ArrayList<CharSequence> chips = new ArrayList<>();
    private TextView cardInfoTxt;

    public View root;
    public NavController mNavController;
    public int quizID, i;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(CardBackViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_card_back, container, false);
        }
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(false);

        cardInfoTxt = getActivity().findViewById(R.id.card_info_text);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        Button flipButton = view.findViewById(R.id.flip_card_btn);

        flipButton.setOnClickListener(v -> {
            model.setIndex(i);
            mNavController.navigate(R.id.action_back_to_front);
        });
        Button nextButton = view.findViewById(R.id.next_card_btn);
        nextButton.setOnClickListener(v -> {
            if(i < (cards.size() - 1)) {
                i++;
                model.setIndex(i);
                mNavController.navigate(R.id.action_back_to_front);
            }
            else {
                i = 0;
                chips.clear();
                model.setIndex(i);
                model.selectChips(null);
                model.selectQuiz(null);
                mNavController.navigate(R.id.action_back_to_quiz);
                Toast.makeText(getContext(), "Quiz complete!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViewModel() {
        i = 0;
        model.getSelectedQuiz().observe(getViewLifecycleOwner(), QuizEntity -> {
            if (QuizEntity != null) {
                i = model.getIndex();
                chips.clear();
                chips.addAll(model.getSelectedChips());
                cards = QuizEntity.getQuizCards();
                quizID = QuizEntity.getQuizID();
                if (cards.size() > 0) {
                    StringBuilder cardInfo = new StringBuilder();
                    if (chips.contains("Trade Name")) {
                        cardInfo.append("Trade Name: ");
                        cardInfo.append(cards.get(i).getTradeName());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Drug Class")) {
                        cardInfo.append("Drug Class: ");
                        cardInfo.append(cards.get(i).getDrugClass());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Drug System")) {
                        cardInfo.append("Drug System: ");
                        cardInfo.append(cards.get(i).getDrugSystem());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Action Mechanism")) {
                        cardInfo.append("Action Mechanism: ");
                        cardInfo.append(cards.get(i).getActionMechanism());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Side Effects")) {
                        cardInfo.append("Side Effects: ");
                        cardInfo.append(cards.get(i).getSideEffects());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Adverse Reactions")) {
                        cardInfo.append("Adverse Reactions: ");
                        cardInfo.append(cards.get(i).getAdverseReactions());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Interactions")) {
                        cardInfo.append("Interactions: ");
                        cardInfo.append(cards.get(i).getInteractions());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Nursing Implications")) {
                        cardInfo.append("Nursing Implications: ");
                        cardInfo.append(cards.get(i).getImplications());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    if (chips.contains("Other")) {
                        cardInfo.append("Other: ");
                        cardInfo.append(cards.get(i).getOther());
                        cardInfo.append(System.getProperty("line.separator"));
                        cardInfo.append(System.getProperty("line.separator"));
                    }
                    cardInfoTxt.setText(cardInfo.toString());
                }
                quizViewModel.loadData(quizID);
            }
        });
    }
    }

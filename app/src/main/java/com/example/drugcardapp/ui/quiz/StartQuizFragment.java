package com.example.drugcardapp.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.drugcardapp.R;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StartQuizFragment extends Fragment {
    private StartQuizViewModel quizViewModel;
    private SharedViewModel model;

    private boolean editing;
    private int quizID;
    private EditText quizName;

    public View root;
    public NavController mNavController;
    ArrayList<Integer> selChips = new ArrayList<Integer>();
    ArrayList<CharSequence> saveChips = new ArrayList<CharSequence>();


    public StartQuizFragment() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(StartQuizViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setHasOptionsMenu(true);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_start_quiz, container, false);
        }
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizName = getActivity().findViewById(R.id.quizName);
        if(savedInstanceState != null) {
            editing = savedInstanceState.getBoolean(String.valueOf(R.string.EDIT_KEY));
            selChips.clear();
            selChips.addAll(savedInstanceState.getIntegerArrayList(String.valueOf(R.string.CHIP_KEY)));
        }
        initViewModel();
        Button startQuizBtn = getActivity().findViewById(R.id.start_quiz);
        ChipGroup chipGroup = getActivity().findViewById(R.id.settings_chip_grp);

        chipGroup.setOnCheckedChangeListener((ChipGroup thisChipGroup, int id)->{
            Chip chip = thisChipGroup.findViewById(id);
            if(chip != null && chip.isChecked()){
                selChips.add(chip.getId());
            }
            else if(chip != null && !chip.isChecked()){
                selChips.remove(chip.getId());
            }
        });

        startQuizBtn.setOnClickListener(v -> {
            saveChips.clear();
            for (int i=0; i<chipGroup.getChildCount();i++) {
                Chip chip = (Chip) chipGroup.getChildAt(i);
                if (chip.isChecked()) {
                    saveChips.add(chip.getText());
                }
            }
            model.selectChips(saveChips);
            mNavController.navigate(R.id.action_start_to_front);
        });

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
    }

    private void initViewModel() {
        model.getSelectedQuiz().observe(getViewLifecycleOwner(), QuizEntity -> {
            if (QuizEntity != null && !editing) {
                selChips.clear();
                quizID = QuizEntity.getQuizID();
            }
        });
        ChipGroup chipGroup = getActivity().findViewById(R.id.settings_chip_grp);
        if(editing){
            for (int i=0; i<chipGroup.getChildCount();i++) {
                Chip chip = (Chip) chipGroup.getChildAt(i);
                if (selChips.contains(chip)) {
                    chip.setChecked(true);
                }
                else{
                    chip.setChecked(false);
                }
            }
            editing = false;
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(String.valueOf(R.string.EDIT_KEY), true);
        outState.putIntegerArrayList(String.valueOf(R.string.CHIP_KEY), selChips);
        super.onSaveInstanceState(outState);
    }
}
package com.example.drugcardapp.ui.quiz;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugcardapp.R;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.ui.adapters.QuizCardAdapter;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EditQuizFragment extends Fragment {

    private EditQuizViewModel quizViewModel;
    private SharedViewModel model;
    private List<DrugCardEntity> cards = new ArrayList<>();
    private ArrayList<DrugCardEntity> addCards = new ArrayList<>();
    private ArrayList<DrugCardEntity> quizCards = new ArrayList<>();
    private QuizCardAdapter cardAdapter;

    private RecyclerView recyclerView;
    private boolean editing;
    private int quizID;
    private EditText quizName;

    public View root;
    public NavController mNavController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(EditQuizViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setHasOptionsMenu(true);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_edit_quiz, container, false);
        }
        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizName = getActivity().findViewById(R.id.quizName);
        if(savedInstanceState != null) {
            editing = savedInstanceState.getBoolean(String.valueOf(R.string.EDIT_KEY));
            quizName.setText(savedInstanceState.getString(String.valueOf(R.string.QUIZ_NAME)));
        }
        initRecyclerView(view);
        initViewModel();

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_save);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
            for (int i = 0; i < cardAdapter.mcards.size(); i++) {
                DrugCardEntity drugCard = cardAdapter.mcards.get(i);
                if(drugCard.isSelected()) {
                    addCards.add(drugCard);
                    drugCard.setSelected(false);
                }
            }
            if((addCards.size() > 0) && (!TextUtils.isEmpty(quizName.getText().toString()))) {
                quizViewModel.addQuiz(quizName.getText().toString().trim(), addCards);
                mNavController.navigate(R.id.action_edit_to_quiz);
            }
            editing = false;
        });

    }

    private void setData(QuizCardAdapter cardAdapter) {
        model.getSelectedQuiz().observe(getActivity(), QuizEntity -> {
            if (QuizEntity != null && !editing) {
                quizID = QuizEntity.getQuizID();
                quizName.setText(QuizEntity.getQuizName());
                quizCards = QuizEntity.getQuizCards();

                for(int i=0;i<cards.size();i++){
                    for(int j=0;j<quizCards.size();j++){
                        if(String.valueOf(cards.get(i).getGenericName())
                                .equals(String.valueOf(quizCards.get(j).getGenericName()))) {
                            cards.get(i).setSelected(true);
                            cardAdapter.checkedCards.add(cards.get(i));
                        }
                    }
                }
                quizViewModel.loadData(quizID);
            }
        });
        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initViewModel() {
        final Observer<List<DrugCardEntity>> observer = cardEntities -> {
            cards.clear();
            cards.addAll(cardEntities);
            if(!editing) {
                cards.forEach(drugCardEntity -> {
                    drugCardEntity.setSelected(false);
                });
            }

            if (cardAdapter == null) {
                cardAdapter = new QuizCardAdapter(cards, model, getActivity(), mNavController);
                recyclerView.setAdapter(cardAdapter);
            } else {
                cardAdapter.replaceList(cards);
                cardAdapter.notifyDataSetChanged();
            }
            setData(cardAdapter);

        };
            quizViewModel.mcards.observe(getActivity(), observer);
    }
    private void initRecyclerView(View view) {
        recyclerView = getActivity().findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.library_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.library_menu);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cardAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(String.valueOf(R.string.EDIT_KEY), true);
        outState.putString(String.valueOf(R.string.QUIZ_NAME), quizName.getText().toString().trim());
        super.onSaveInstanceState(outState);
    }
}

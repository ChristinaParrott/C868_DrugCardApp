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
import android.widget.Toast;

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

public class NewQuizFragment extends Fragment {

    private NewQuizViewModel quizViewModel;
    private SharedViewModel model;
    private List<DrugCardEntity> cards = new ArrayList<>();
    private ArrayList<DrugCardEntity> addCards = new ArrayList<>();
    private QuizCardAdapter cardAdapter;

    private RecyclerView recyclerView;

    public View root;
    public NavController mNavController;
    private boolean editing;
    private EditText quizName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(NewQuizViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setHasOptionsMenu(true);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_new_quiz, container, false);
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
        if(quizName.getText().toString().trim() != null &&
                !TextUtils.isEmpty(quizName.getText().toString())){
            if (cardAdapter.checkedCards.size() > 0) {
                int i = 0;
                do {
                    DrugCardEntity drugCard = cardAdapter.checkedCards.get(i);
                    addCards.add(drugCard);
                    drugCard.setSelected(false);
                    i++;

                } while (i < cardAdapter.checkedCards.size());
                if ((addCards.size() > 0) && (!TextUtils.isEmpty(quizName.getText().toString()))) {
                    quizViewModel.addQuiz(quizName.getText().toString().trim(), addCards);
                    mNavController.navigate(R.id.action_new_to_quiz);
                }
                editing = false;
            } else {
                Toast.makeText(v.getContext(), "Error: can't add a quiz with no cards.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(v.getContext(), "Error: missing quiz name.", Toast.LENGTH_LONG).show();
        }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initViewModel(){ final Observer<List<DrugCardEntity>> observer = cardEntities -> {
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
    };
    quizViewModel.mcards.observe(getActivity(), observer);
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.card_recycler_view);

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

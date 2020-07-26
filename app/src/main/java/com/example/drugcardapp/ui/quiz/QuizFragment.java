package com.example.drugcardapp.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
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
import com.example.drugcardapp.database.QuizEntity;
import com.example.drugcardapp.ui.adapters.QuizAdapter;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {

    private QuizViewModel quizViewModel;
    private SharedViewModel quizModel;
    private List<QuizEntity> quizzes = new ArrayList<>();
    private QuizAdapter quizAdapter;

    private RecyclerView recyclerView;

    public View root;
    public NavController mNavController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(QuizViewModel.class);
        quizModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setHasOptionsMenu(true);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_quizzes, container, false);
        }
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
        initViewModel();

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_menu_add);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
           mNavController.navigate(R.id.action_quiz_to_new);
        });
    }
    private void initViewModel() {
        quizViewModel = new ViewModelProvider(this)
                .get(QuizViewModel.class);
        quizModel.selectChips(null);
        quizModel.select(null);
        quizModel.selectQuiz(null);

        final Observer<List<QuizEntity>> observer = quizEntities -> {
            quizzes.clear();
            quizzes.addAll(quizEntities);

            if (quizAdapter == null) {
                quizAdapter = new QuizAdapter(quizzes, quizModel, quizViewModel, getActivity(), mNavController);
                recyclerView.setAdapter(quizAdapter);
            } else {
                quizAdapter.replaceList(quizzes);
                quizAdapter.notifyDataSetChanged();
            }
        };
        quizViewModel.mquizzes.observe(getActivity(), observer);
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.quiz_recycler_view);

        recyclerView = getActivity().findViewById(R.id.quiz_recycler_view);
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
                quizAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

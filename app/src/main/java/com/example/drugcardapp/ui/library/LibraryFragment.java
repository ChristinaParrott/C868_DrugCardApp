package com.example.drugcardapp.ui.library;

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
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.ui.adapters.DrugCardAdapter;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    private LibraryViewModel libraryViewModel;
    private SharedViewModel model;
    private List<DrugCardEntity> cards = new ArrayList<>();
    private DrugCardAdapter cardAdapter;

    private RecyclerView recyclerView;

    public View root;
    public NavController mNavController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        libraryViewModel =
                ViewModelProviders.of(this).get(LibraryViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setHasOptionsMenu(true);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if (root == null){
            root = inflater.inflate(R.layout.fragment_drug_card_library, container, false);
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
            mNavController.navigate(R.id.action_library_to_card);
        });
    }
    @Override
    public void onResume(){
       initRecyclerView(getView());
        initViewModel();
        super.onResume();
    }
    private void initViewModel() {
        final Observer<List<DrugCardEntity>> observer = cardEntities -> {
            cards.clear();
            cards.addAll(cardEntities);

            if (cardAdapter == null) {
                cardAdapter = new DrugCardAdapter(cards, model, getActivity(), mNavController);
                recyclerView.setAdapter(cardAdapter);
            } else {
                cardAdapter.replaceList(cards);
                cardAdapter.notifyDataSetChanged();
            }
        };
        libraryViewModel = new ViewModelProvider(this)
                .get(LibraryViewModel.class);
        libraryViewModel.mcards.observe(getActivity(), observer);
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView = getActivity().findViewById(R.id.recycler_view);
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
}
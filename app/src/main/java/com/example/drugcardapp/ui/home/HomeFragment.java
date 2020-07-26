package com.example.drugcardapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.drugcardapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private View root;
    private ImageView quizBtn;
    private ImageView drugCardBtn;
    public NavController mNavController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if(root == null) {
            root = inflater.inflate(R.layout.fragment_home, container, false);
        }
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        quizBtn = view.findViewById(R.id.quizzes_btn);
        drugCardBtn = view.findViewById(R.id.drug_card_btn);
        quizBtn.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_home_to_quiz);
        });
        drugCardBtn.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_home_to_lib);
        });
    }

    private void initViewModel() {
        return;
    }
}
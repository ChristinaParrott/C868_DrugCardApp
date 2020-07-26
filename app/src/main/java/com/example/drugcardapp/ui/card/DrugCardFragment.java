package com.example.drugcardapp.ui.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.drugcardapp.R;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DrugCardFragment extends Fragment {

    private DrugCardViewModel drugCardViewModel;
    private SharedViewModel model;

    private View root;
    private Spinner classSpinner;
    private Spinner systemSpinner;
    private TextView genericName, tradeName, actionMech, sideEffects, adverseReactions, interactions,
            implications, other;
    private ArrayAdapter<CharSequence> systemAdapter, classAdapter;
    private int cardID;

    private boolean editing;
    public NavController mNavController;


    public DrugCardFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        drugCardViewModel = new ViewModelProvider(this).get(DrugCardViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setHasOptionsMenu(true);
        mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        if(root == null) {
            root = inflater.inflate(R.layout.fragment_drug_card, container, false);
        }
        if(savedInstanceState != null){
            editing = savedInstanceState.getBoolean(getString(R.string.EDIT_KEY));
        }
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classSpinner = view.findViewById(R.id.drugClassSpinner);
        systemSpinner = view.findViewById(R.id.drugSystemSpinner);
        initSpinners(view);
        initViewModel();

        genericName = view.findViewById(R.id.genericNameTxt);
        tradeName = view.findViewById(R.id.tradeNameTxt);
        actionMech = view.findViewById(R.id.actionTxt);
        sideEffects = view.findViewById(R.id.sideEffectsTxt);
        adverseReactions = view.findViewById(R.id.adverseTxt);
        interactions = view.findViewById(R.id.interactionsTxt);
        implications = view.findViewById(R.id.implicationsTxt);
        other = view.findViewById(R.id.otherTxt);

        if(savedInstanceState != null) {
            editing = savedInstanceState.getBoolean(String.valueOf(R.string.EDIT_KEY));
            if(!editing) {

                genericName.setText(savedInstanceState.getString(String.valueOf(R.string.GENERIC_KEY)));
                tradeName.setText(savedInstanceState.getString(String.valueOf(R.string.TRADE_KEY)));
                actionMech.setText(savedInstanceState.getString(String.valueOf(R.string.ACTION_KEY)));
                sideEffects.setText(savedInstanceState.getString(String.valueOf(R.string.EFFECTS_KEY)));
                adverseReactions.setText(savedInstanceState.getString(String.valueOf(R.string.ADVERSE_KEY)));
                interactions.setText(savedInstanceState.getString(String.valueOf(R.string.INTERACT_KEY)));
                implications.setText(savedInstanceState.getString(String.valueOf(R.string.IMPLIC_KEY)));
                other.setText(savedInstanceState.getString(String.valueOf(R.string.OTHER_KEY)));

                int systemPos = systemAdapter.getPosition(savedInstanceState.getString(String.valueOf(R.string.SYSTEM_KEY)));
                int classPos = classAdapter.getPosition(savedInstanceState.getString(String.valueOf(R.string.CLASS_KEY)));
                systemSpinner.setSelection(systemPos);
                classSpinner.setSelection(classPos);
            }
        }
        setData();

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_save);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
            saveAndReturn();
        });
    }

    private void initViewModel() {
    }

    private void setData() {
        model.getSelected().observe(getViewLifecycleOwner(), drugCardEntity -> {
            if(drugCardEntity != null && !editing) {
                cardID = drugCardEntity.getCardID();
                genericName.setText(drugCardEntity.getGenericName());
                tradeName.setText(drugCardEntity.getTradeName());
                actionMech.setText(drugCardEntity.getActionMechanism());
                sideEffects.setText(drugCardEntity.getSideEffects());
                adverseReactions.setText(drugCardEntity.getAdverseReactions());
                interactions.setText(drugCardEntity.getInteractions());
                implications.setText(drugCardEntity.getImplications());
                other.setText(drugCardEntity.getOther());

                int systemPos = systemAdapter.getPosition(drugCardEntity.getDrugSystem());
                int classPos = classAdapter.getPosition(drugCardEntity.getDrugClass());
                systemSpinner.setSelection(systemPos);
                classSpinner.setSelection(classPos);

                drugCardViewModel.loadData(cardID);
                model.select(null);
            }
    });
    }

    private void saveAndReturn() {
        String genericStr = genericName.getText().toString().trim();
        String tradeStr = tradeName.getText().toString().trim();
        String actionStr = actionMech.getText().toString().trim();
        String sideEffectStr = sideEffects.getText().toString().trim();
        String adverseStr = adverseReactions.getText().toString().trim();
        String interactionsStr = interactions.getText().toString().trim();
        String implicationsStr = implications.getText().toString().trim();
        String otherStr = other.getText().toString().trim();

        String systemSelected = (String) systemSpinner.getSelectedItem();
        String classSelected = (String) classSpinner.getSelectedItem();

        if (TextUtils.isEmpty(genericStr) || classSelected.contains("None")
                || systemSelected.contains("None")) {
            Toast.makeText(getContext(), "ERROR: Generic Name, Drug Class, and Body System are required.",
                    Toast.LENGTH_LONG).show();
        }else {
            drugCardViewModel.saveCard(genericStr, tradeStr, classSelected, systemSelected, actionStr, sideEffectStr,
                    adverseStr, interactionsStr, implicationsStr, otherStr);
            Toast.makeText(getContext(), "Drug card saved.",
                    Toast.LENGTH_SHORT).show();
            editing = false;
            mNavController.navigate(R.id.nav_card_to_lib);
        }
    }

    private void initSpinners(View view) {
        classAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.drug_class_array,android.R.layout.simple_spinner_item);
        systemAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.body_systems_array,android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        systemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        systemSpinner.setAdapter(systemAdapter);
        classSpinner.setSelection(0);
        systemSpinner.setSelection(0);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(String.valueOf(R.string.EDIT_KEY), true);
        outState.putString(String.valueOf(R.string.GENERIC_KEY), genericName.getText().toString());
        outState.putString(String.valueOf(R.string.TRADE_KEY), tradeName.getText().toString());
        outState.putString(String.valueOf(R.string.CLASS_KEY), (String) classSpinner.getSelectedItem());
        outState.putString(String.valueOf(R.string.SYSTEM_KEY), (String) systemSpinner.getSelectedItem());
        outState.putString(String.valueOf(R.string.ACTION_KEY), actionMech.getText().toString());
        outState.putString(String.valueOf(R.string.EFFECTS_KEY), sideEffects.getText().toString());
        outState.putString(String.valueOf(R.string.ADVERSE_KEY), adverseReactions.getText().toString());
        outState.putString(String.valueOf(R.string.INTERACT_KEY), interactions.getText().toString());
        outState.putString(String.valueOf(R.string.IMPLIC_KEY), implications.getText().toString());
        outState.putString(String.valueOf(R.string.OTHER_KEY), other.getText().toString());
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.card_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
                drugCardViewModel.deleteCard();
                Toast.makeText(getContext(),"Card deleted.", Toast.LENGTH_SHORT).show();
                editing = false;
                mNavController.navigate(R.id.nav_card_to_lib);
            }
        if(item.getItemId() == R.id.action_email){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            StringBuilder cardInfo = new StringBuilder();
                cardInfo.append("Trade Name: ");
                cardInfo.append(tradeName.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Drug Class: ");
                cardInfo.append((String) classSpinner.getSelectedItem());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Drug System: ");
                cardInfo.append((String) systemSpinner.getSelectedItem());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Action Mechanism: ");
                cardInfo.append(actionMech.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Side Effects: ");
                cardInfo.append(sideEffects.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Adverse Reactions: ");
                cardInfo.append(adverseReactions.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Interactions: ");
                cardInfo.append(interactions.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Nursing Implications: ");
                cardInfo.append(implications.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append("Other: ");
                cardInfo.append(other.getText().toString());
                cardInfo.append(System.getProperty("line.separator"));
                cardInfo.append(System.getProperty("line.separator"));
            sendIntent.putExtra(Intent.EXTRA_TEXT, cardInfo.toString());
            sendIntent.putExtra(Intent.EXTRA_TITLE, "Card Name: "
                    + String.valueOf(genericName.getText().toString()));
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
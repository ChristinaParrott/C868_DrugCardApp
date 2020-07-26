package com.example.drugcardapp.ui.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugcardapp.R;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReportCardAdapter extends RecyclerView.Adapter<ReportCardAdapter.ViewHolder>  implements Filterable {
private List<DrugCardEntity> mcards;
    private List<DrugCardEntity> cardsFull;
private final Context mcontext;

public NavController mNavController;
private SharedViewModel model;

public ReportCardAdapter(List<DrugCardEntity> mcards, SharedViewModel model, Context mcontext, NavController navController){
    this.mcards = mcards;
    this.mcontext = mcontext;
    this.model = model;
    mNavController = navController;
    cardsFull = new ArrayList<>(mcards);
}
public void replaceList(List<DrugCardEntity> cards){
    mcards = cards;
}

@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.report_list_item, parent, false);
    return new ViewHolder(view);
}
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
    final DrugCardEntity card = mcards.get(position);
    holder.nameTextView.setText(card.getGenericName());
    holder.classTextView.setText(card.getDrugClass());
    holder.systemTextView.setText(card.getDrugSystem());
    holder.printFab.setOnClickListener(v -> {

        model.select(card);
        createPDF(card);
        });
}

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPDF(DrugCardEntity card) {
        Date date = new Date();
        PdfDocument drugCardPDF = new PdfDocument();
        Paint myPaint = new Paint();
        Paint titlePaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(900, 1500, 1).create();
        PdfDocument.Page myPage = drugCardPDF.startPage(myPageInfo);
        Canvas canvas = myPage.getCanvas();

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(40);
        canvas.drawText(card.getGenericName(), 450, 50, titlePaint);
        myPaint.setTextSize(25f);
        myPaint.setTextAlign(Paint.Align.LEFT);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:");

        canvas.drawText("Trade Name: " + card.getTradeName(), 10, 100, myPaint);
        canvas.drawText("Drug Class: " + card.getDrugClass(), 10, 150, myPaint);
        canvas.drawText("Drug System: " + card.getDrugSystem(), 10, 200, myPaint);
        canvas.drawText("Action Mechanism: " + card.getActionMechanism(), 10, 250, myPaint);
        canvas.drawText("Side Effects: " + card.getSideEffects(), 10, 350, myPaint);
        canvas.drawText("Adverse Reactions: " + card.getAdverseReactions(), 10, 450, myPaint);
        canvas.drawText("Interactions: " + card.getInteractions(), 10, 550, myPaint);
        canvas.drawText("Implications: " + card.getImplications(), 10, 650, myPaint);
        canvas.drawText("Other: " + card.getOther(), 10, 750, myPaint);
        canvas.drawText("Date Generated: " + dateFormat.format(date), 10, 850, myPaint);
        canvas.drawText("Time Generated: " + timeFormat.format(date), 10, 900, myPaint);
        drugCardPDF.finishPage(myPage);

        String directory_path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+ "/" + card.getGenericName() + ".pdf";
        File filePath = new File(targetPdf);

        try{
            drugCardPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(mcontext,"PDF card created.", Toast.LENGTH_SHORT).show();
            drugCardPDF.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(mcontext,"Error: file not created.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(mcontext,"Error: file not created.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        drugCardPDF.close();
    }

    @Override
    public int getItemCount(){
    return mcards.size();
}

    @Override
    public Filter getFilter() {
        return drugCardFilter;
    }
    private Filter drugCardFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DrugCardEntity> filterCards = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterCards.addAll(cardsFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for(DrugCardEntity card : cardsFull){
                    if(card.getGenericName().toLowerCase().contains(filter) ||
                    card.getTradeName().toLowerCase().contains(filter) ||
                    card.getDrugClass().toLowerCase().contains(filter) ||
                    card.getDrugSystem().toLowerCase().contains(filter)) {
                        filterCards.add(card);
                    }}
                }
            FilterResults results = new FilterResults();
            results.values = filterCards;
            return results;
            }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mcards.clear();
            mcards.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView nameTextView;
    TextView classTextView;
    TextView systemTextView;
    FloatingActionButton printFab;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.name_text);
        classTextView = itemView.findViewById(R.id.class_text);
        systemTextView = itemView.findViewById(R.id.system_text);
        printFab = itemView.findViewById(R.id.fab_print);
    }
}
}

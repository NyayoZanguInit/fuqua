package com.nyayozangu.labs.fuqua.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nyayozangu.labs.fuqua.R;
import com.nyayozangu.labs.fuqua.common.Common;
import com.nyayozangu.labs.fuqua.model.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class DataEntryActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    // TODO: 9/8/18 check for incomplete entry
    // TODO: 9/8/18 check if there are entries today

    private static final int RAW_SUPPLY = 1;
    private static final int  BOILER_SUPPLY= 2;
    private static final int CONDENSATE_RETURN= 3;
    private static final int SAND_FILTER= 4;   //Backwash
    private static final int UF = 5;          //Backwash
    private static final int BOILER_MAKEUP= 6;
    private static final int AGROFIRED_BOILER= 7;
    private static final int SCRUBBER= 8;
    private static final int CONDENSER = 9;


    private String propertiesTitles[] = {
            "Raw Supply[Lake]",
            "Boiler Supply",
            "Condensate Return",
            "Backwash Sand Filter",
            "Backwash UF",
            "Boiler MakeUp",
            "Agro-Fired Boiler",
            "Scrubber",
            "Condenser"
    };


    private Common common = new Common();

    private static final String TAG = "DataEntryActivity";

    private Button submitButton;
    private View dataInputView;

    private int rawSupply, boilerSupply, condensateReturn, backwashSandFilter, backwashUf, boilerMakeup, agroFiredboiler, scrubber, condenser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //initiate items
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        handleSimpleAdapter();
        checkForIncompleteEntry();
    }

    private void handleSimpleAdapter() {


        // maybe not needed
        List<HashMap<String, String>> propertyList = new ArrayList<>();

        for (String propertiesTitle : propertiesTitles) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("property_title", propertiesTitle);
            propertyList.add(hm);
        }

        String[] from = {/*"listView_image",*/ "property_title"};
        int[] to = {/*R.id.catGridItemImageView,*/ R.id.entryItemButton};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), propertyList, R.layout.data_entry_item, from, to);
        final GridView propertiesGridView = findViewById(R.id.propertiesGridView);
        propertiesGridView.setAdapter(simpleAdapter);
        propertiesGridView.setOnItemClickListener(this);
    }

    private void checkForIncompleteEntry() {
        // TODO: 9/8/18 check for entries today
        // TODO: 9/8/18 check for incomplete entry

        String todayString = getToday();
        common.database.collection("Entries").document(todayString).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            //entry already exists
                            showEntryExistsDialog();
                            Entry entry = documentSnapshot.toObject(Entry.class);
                            populateFields(entry);
                        }else{
                            //entry does not exits
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: failed to get document" + e.getMessage() , e);
                    }
                });
    }

    private void populateFields(Entry entry) {
        // TODO: 9/8/18 populate the fields for the entry
        // check if the entry
    }

    private void showEntryExistsDialog() {
        AlertDialog.Builder entryExistsDialogBuilder = new AlertDialog.Builder(this);
        entryExistsDialogBuilder.setTitle("Entry exists")
                .setMessage("There is already an entry made for today.\n Only one entry is allowed per day")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 9/8/18 override entry made
                        // for the first version, go to home page
                        // set read only to true
                        dialog.dismiss();

                    }
                })
                .show();
    }

    private void goToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private String getToday() {
        Calendar date = GregorianCalendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = String.valueOf(date.get(Calendar.MONTH + 1));
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        return year + month + day;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitButton:
                handleSubmit();
                break;
            default:
                Log.d(TAG, "onClick: " + v.getId() + " has been clicked");
        }
    }

    private void handleSubmit() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case RAW_SUPPLY:
                Toast.makeText(this, "position " + position +"has been clicked", Toast.LENGTH_SHORT).show();
                // TODO: 9/23/18 replace toast with show input dialog
                showDataInputDialog(propertiesTitles[RAW_SUPPLY], RAW_SUPPLY);
                break;
            default:
                Log.d(TAG, "onItemClick: position " + position + " has been clicked");
        }
    }

    private void showDataInputDialog(String propertyTitle, final int position) {
        // TODO: 9/23/18 get user input
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        dataInputView = inflater.inflate(R.layout.data_input_layout, null);

        AlertDialog.Builder dataInputBuilder = new AlertDialog.Builder(this);
        dataInputBuilder.setTitle(propertyTitle);
         dataInputBuilder.setIcon(R.drawable.ic_alert_black_24dp)
        .setView(dataInputView)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get user input
                        EditText userInputEditText = dataInputView.findViewById(R.id.dataInputEditText);
                        String userInput = userInputEditText.getText().toString().trim();
                        if (!userInput.isEmpty()){
                            updateDataValues(userInput, position);

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
        .setCancelable(false)
        .show();


    }

    private void updateDataValues(String userInput, int position) {
        switch (position){
            case RAW_SUPPLY:
                rawSupply=Integer.valueOf(userInput);
                break;
            case BOILER_SUPPLY:
                boilerSupply = Integer.valueOf(userInput);
                break;
            case CONDENSATE_RETURN:
                condensateReturn=Integer.valueOf(userInput);
                break;
            case SAND_FILTER:
                backwashSandFilter=Integer.valueOf(userInput);
                break;
            case UF:
                backwashUf=Integer.valueOf(userInput);
                break;
            case BOILER_MAKEUP:
                boilerMakeup=Integer.valueOf(userInput);
                break;
            case AGROFIRED_BOILER:
                agroFiredboiler=Integer.valueOf(userInput);
                break;
            case SCRUBBER:
                scrubber=Integer.valueOf(userInput);
                break;
            case CONDENSER:
                condenser=Integer.valueOf(userInput);
                break;
            default:
                Log.d(TAG, "no value has been clicked");

        }
    }
}

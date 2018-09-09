package com.nyayozangu.labs.fuqua.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nyayozangu.labs.fuqua.R;
import com.nyayozangu.labs.fuqua.model.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class DataEntryActivity extends AppCompatActivity {

    // TODO: 9/8/18 check for incomplete entry
    // TODO: 9/8/18 check if there are entries today

    private static final String TAG = "DataEntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        //initiate items
        String propertiesTitles[] = {
                "Lake", "uf", "sand", "supply", "makeup", "scrubber", "condensate", "water temp"
        };

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

        checkForIncompleteEntry(database);
    }

    private void checkForIncompleteEntry(FirebaseFirestore database) {
        // TODO: 9/8/18 check for entries today
        // TODO: 9/8/18 check for incomplete entry

        String todayString = getToday();
        database.collection("Entries").document(todayString).get()
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
}

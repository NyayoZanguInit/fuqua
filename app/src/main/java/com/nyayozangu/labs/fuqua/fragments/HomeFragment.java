package com.nyayozangu.labs.fuqua.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.nyayozangu.labs.fuqua.R;
import com.nyayozangu.labs.fuqua.activities.DataEntryActivity;
import com.nyayozangu.labs.fuqua.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    // TODO: 9/8/18

    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        //initiate the button and the warning
        TextView notificationTextView = view.findViewById(R.id.notificationsTextView);
        Button addDataButton = view.findViewById(R.id.addDataButton);

        //add click listeners
        notificationTextView.setOnClickListener(this);
        addDataButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addDataButton:
                goToDataEntry();
                break;
            case R.id.notificationsTextView:
                // TODO: 9/8/18 go to finish data page
                Toast.makeText(getActivity(), "notification text view has been clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d(TAG, "onClick: the clicked view is not in the listener");
        }
    }

    private void goToDataEntry() {
        startActivity(new Intent(getActivity(), DataEntryActivity.class));
    }

}

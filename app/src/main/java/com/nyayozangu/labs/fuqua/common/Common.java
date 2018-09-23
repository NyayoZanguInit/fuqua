package com.nyayozangu.labs.fuqua.common;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Common {


    public FirebaseFirestore database = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public boolean isConnected(){
        return this.auth.getCurrentUser() != null;
    }



}

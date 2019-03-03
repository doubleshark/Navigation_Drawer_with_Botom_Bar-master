package com.example.srk.navigationdrawer;

import com.google.firebase.database.FirebaseDatabase;

public class PersistenceClass extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

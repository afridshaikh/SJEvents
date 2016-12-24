package com.mishrafamily.sjbook;

import com.firebase.client.Firebase;

/**
 * Created by MishraFamily on 01/10/2016.
 */
public class firebase  extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}

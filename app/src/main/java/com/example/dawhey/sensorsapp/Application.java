package com.example.dawhey.sensorsapp;

/**
 * Created by dawhey on 29.10.16.
 */

public class Application extends android.app.Application {

    private int fragmentId = -1;


    public int getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(int fragmentId) {
        this.fragmentId = fragmentId;
    }

}

package com.jompon.facilepermission.sample;

import android.Manifest;
import android.app.Application;

import com.jompon.facilepermission.FacilePermission;

import java.util.Arrays;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacilePermission.PERMISSION_LIST = Arrays.asList(
//                Manifest.permission.ACCESS_COARSE_LOCATION,     //permission only for NETWORK_PROVIDER
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
    }
}

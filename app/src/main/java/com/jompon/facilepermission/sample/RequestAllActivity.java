package com.jompon.facilepermission.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jompon.facilepermission.FacilePermission;

import java.util.Arrays;
import java.util.Collections;

public class RequestAllActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAll;
    private Button btnLocation;
    private Button btnCall;
    private Button btnStorage;
    private Button btnDrawOverlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_all);

        bindingView();
        bindingData();
    }

    private void bindingView( )
    {
        btnAll = (Button) findViewById(R.id.btn_all);
        btnLocation = (Button) findViewById(R.id.btn_location);
        btnCall = (Button) findViewById(R.id.btn_call);
        btnStorage = (Button) findViewById(R.id.btn_storage);
        btnDrawOverlay = (Button) findViewById(R.id.btn_draw_overlay);
    }

    private void bindingData( )
    {
        setTitle(getIntent().getStringExtra(Constant.KEY_TITLE));
        btnAll.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnStorage.setOnClickListener(this);
        btnDrawOverlay.setOnClickListener(this);
    }

    private void checkDrawOverlay( )
    {
        if( FacilePermission.isDrawOverlayPermission(this) ){
            FacilePermission.requestDrawOverlays(this, getPackageName());
        }else{
            Toast.makeText(this, R.string.alert_user_allowed_draw_overlay, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        if( v == btnAll ){
            FacilePermission.checkSelfPermission(this, FacilePermission.PERMISSION_LIST);
        }
        if( v == btnLocation ){
            FacilePermission.checkSelfPermission(this, Collections.singletonList(Manifest.permission.ACCESS_FINE_LOCATION));
        }
        if( v == btnCall ){
            FacilePermission.checkSelfPermission(this, Collections.singletonList(Manifest.permission.CALL_PHONE));
        }
        if( v == btnStorage ){
            FacilePermission.checkSelfPermission(this, Collections.singletonList(Manifest.permission.WRITE_EXTERNAL_STORAGE));
        }
        if( v == btnDrawOverlay ){
            checkDrawOverlay();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case FacilePermission.REQUEST_PERMISSION:

                if( !FacilePermission.isPermissionGranted(grantResults) ) {
                    if( FacilePermission.shouldShowRequestPermissionRationale(this, permissions[0]) ) {
                        FacilePermission.checkSelfPermission(this, Arrays.asList(permissions));
                    }
                }

                break;
        }
    }
}

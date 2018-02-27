package com.jompon.facilepermission.sample;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jompon.facilepermission.FacilePermission;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NeedPermissionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAll;
    private Button btnLocation;
    private Button btnCall;
    private Button btnStorage;
    private List<String> permissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_permission);

        bindingView();
        bindingData();
    }

    private void bindingView( )
    {
        btnAll = (Button) findViewById(R.id.btn_all);
        btnLocation = (Button) findViewById(R.id.btn_location);
        btnCall = (Button) findViewById(R.id.btn_call);
        btnStorage = (Button) findViewById(R.id.btn_storage);
    }

    private void bindingData( )
    {
        setTitle(getIntent().getStringExtra(Constant.KEY_TITLE));
        btnAll.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnStorage.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == FacilePermission.REQUEST_PERMISSION ){
            FacilePermission.checkSelfPermissionWrapper(this, permissions);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case FacilePermission.REQUEST_PERMISSION:

                if( !FacilePermission.isPermissionGranted(grantResults) ) {

                    this.permissions = Arrays.asList(permissions);
                    FacilePermission.checkSelfPermissionWrapper(this, Arrays.asList(permissions));
                }

                break;
        }
    }
}

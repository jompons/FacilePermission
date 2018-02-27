package com.jompon.facilepermission.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAll;
    private Button btnPermission;
    private Button btnDrawOverlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();
        bindingData();
    }

    private void bindingView( )
    {
        btnAll = (Button) findViewById(R.id.btn_request_all);
        btnPermission = (Button) findViewById(R.id.btn_need_permission);
        btnDrawOverlay = (Button) findViewById(R.id.btn_need_draw_overlay);
    }

    private void bindingData( )
    {
        btnAll.setOnClickListener(this);
        btnPermission.setOnClickListener(this);
        btnDrawOverlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if( v == btnAll ){
            Intent intent = new Intent(this, RequestAllActivity.class);
            intent.putExtra(Constant.KEY_TITLE, btnAll.getText().toString());
            startActivity(intent);
        }
        if( v == btnPermission ){
            Intent intent = new Intent(this, NeedPermissionActivity.class);
            intent.putExtra(Constant.KEY_TITLE, btnPermission.getText().toString());
            startActivity(intent);
        }
        if( v == btnDrawOverlay ){
            Intent intent = new Intent(this, NeedDrawOverlayActivity.class);
            intent.putExtra(Constant.KEY_TITLE, btnDrawOverlay.getText().toString());
            startActivity(intent);
        }
    }
}

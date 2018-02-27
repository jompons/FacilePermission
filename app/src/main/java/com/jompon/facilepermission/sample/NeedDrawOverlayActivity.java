/*
 * Copyright (C) 2018 jompons.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jompon.facilepermission.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jompon.facilepermission.FacilePermission;

public class NeedDrawOverlayActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnDrawOverlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_draw_overlay);

        bindingView();
        bindingData();
    }

    private void bindingView( )
    {
        btnDrawOverlay = (Button) findViewById(R.id.btn_draw_overlay);
    }

    private void bindingData( )
    {
        setTitle(getIntent().getStringExtra(Constant.KEY_TITLE));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FacilePermission.REQUEST_DRAW_OVERLAY_PERMISSION && resultCode != RESULT_OK ){
            checkDrawOverlay();
        }
    }

    @Override
    public void onClick(View v) {

        if( v == btnDrawOverlay ){
            checkDrawOverlay();
        }
    }
}

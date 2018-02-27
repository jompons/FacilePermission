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

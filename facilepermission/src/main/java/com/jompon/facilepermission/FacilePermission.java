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

package com.jompon.facilepermission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class FacilePermission {

    public static final int REQUEST_PERMISSION = 1;
    public static final int REQUEST_DRAW_OVERLAY_PERMISSION = 2;
    public static List<String> PERMISSION_LIST = new ArrayList<>();

    /**
     * Request permission grant
     * @param activity of class
     * @param permissions which want request
     */
    public static void checkSelfPermission(Activity activity, List<String> permissions)
    {
        List<String> requestPermissions = getRequestPermissions(activity, permissions);

        if (requestPermissions.size() > 0) {

            ActivityCompat.requestPermissions(activity, requestPermissions.toArray(new String[requestPermissions.size()]), REQUEST_PERMISSION);
        }
    }

    /**
     * Request permission grant
     * @param fragment of class
     * @param permissions which want request
     */
    public static void checkSelfPermission(Fragment fragment, List<String> permissions)
    {
        List<String> requestPermissions = getRequestPermissions(fragment, permissions);

        if (requestPermissions.size() > 0) {

            fragment.requestPermissions(requestPermissions.toArray(new String[requestPermissions.size()]), REQUEST_PERMISSION);
        }
    }

    /**
     * Check allow permission
     * @param context of class
     * @param permissions which want check permission grant
     * @return true if all permission are allows otherwise false
     */
    public static boolean hasPermission(Context context, List<String> permissions)
    {
        if( context == null || permissions == null )   return false;
        for(String permission: permissions){
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check permission grant
     * @param grantResults which want check
     * @return true if all grantResult are permission granted otherwise false
     */
    public static boolean isPermissionGranted(int[] grantResults) {

        for (int grantResult:grantResults){
            if( grantResult != PackageManager.PERMISSION_GRANTED )
                return false;
        }
        return true;
    }

    /**
     * Check should show request permission or not
     * @param activity class
     * @param permissions which want check
     * @return true if Any time user clicks Deny permissions (including the very first time).
     *         false if User selects “never asks again".
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permissions)
    {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions);
    }

    /**
     * Check should show request permission or not
     * @param fragment class
     * @param permissions which want check
     * @return true if Any time user clicks Deny permissions (including the very first time).
     *         false if User selects “never asks again".
     */
    public static boolean shouldShowRequestPermissionRationale(Fragment fragment, String permissions)
    {
        return fragment.shouldShowRequestPermissionRationale(permissions);
    }

    /**
     * Request permission if can otherwise open settings.
     * @param activity of class
     * @param permissions which want check permission grant
     */
    public static void checkSelfPermissionWrapper(Activity activity, List<String> permissions)
    {
        if (!hasPermission(activity, permissions)) {
            List<String> requestPermissions = getRequestPermissions(activity, permissions);
            if( requestPermissions.size() > 0 ){
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermissions.get(0))) {

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                    intent.setData(uri);
                    activity.startActivityForResult(intent, REQUEST_PERMISSION);
                }else{
                    checkSelfPermission(activity, permissions);
                }
            }
        }
    }

    /**
     * Request permission if can otherwise open settings.
     * @param fragment of class
     * @param permissions which want check permission grant
     */
    public static void checkSelfPermissionWrapper(Fragment fragment, List<String> permissions)
    {
        if (!hasPermission(fragment.getContext(), permissions)) {
            List<String> requestPermissions = getRequestPermissions(fragment, permissions);
            if( requestPermissions.size() > 0 ){
                if (!fragment.shouldShowRequestPermissionRationale(requestPermissions.get(0))) {

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", fragment.getContext().getPackageName(), null);
                    intent.setData(uri);
                    fragment.startActivity(intent);
                }else{
                    checkSelfPermission(fragment, permissions);
                }
            }
        }
    }

    /**
     * Get request permission list that not permission granted yet.
     * @param activity of class
     * @param permissions which want request permission grant
     * @return list of request permission
     */
    private static List<String> getRequestPermissions(Activity activity, List<String> permissions)
    {
        if( activity == null || permissions == null )   return new ArrayList<>();
        List<String> requestPermissions = new ArrayList<>();

        for(String permission:permissions){
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions.add(permission);
            }
        }

        return requestPermissions;
    }

    /**
     * Get request permission list that not permission granted yet.
     * @param fragment of class
     * @param permissions which want request permission grant
     * @return list of request permission
     */
    private static List<String> getRequestPermissions(Fragment fragment, List<String> permissions)
    {
        if( fragment == null || permissions == null )   return new ArrayList<>();
        List<String> requestPermissions = new ArrayList<>();

        for(String permission:permissions){
            if (ContextCompat.checkSelfPermission(fragment.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions.add(permission);
            }
        }

        return requestPermissions;
    }

    /**
     * Open draw overlay permission settings page
     * @param activity source
     * @param packageName app
     */
    @TargetApi(23)
    public static void requestDrawOverlays(Activity activity, String packageName)
    {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName));
        activity.startActivityForResult(intent, REQUEST_DRAW_OVERLAY_PERMISSION);
    }

    /**
     * Open draw overlay permission settings page
     * @param fragment source
     * @param packageName app
     */
    @TargetApi(23)
    public static void requestDrawOverlays(Fragment fragment, String packageName)
    {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName));
        fragment.startActivityForResult(intent, REQUEST_DRAW_OVERLAY_PERMISSION);
    }

    /**
     * Check draw overlay permission
     * @param context source
     * @return true if user allowed permission otherwise false
     */
    public static boolean isDrawOverlayPermission(Context context){
        if( Build.VERSION.SDK_INT == Build.VERSION_CODES.O ){
            try {
                WindowManager mgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                if (mgr == null) return false; //getSystemService might return null
                View viewToAdd = new View(context);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(0, 0, android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
                viewToAdd.setLayoutParams(params);
                mgr.addView(viewToAdd, params);
                mgr.removeView(viewToAdd);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context);
    }
}

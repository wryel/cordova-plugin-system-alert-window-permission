package com.wryel.android.cordova.plugin.systemalertwindowpermission;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

public class SystemAlertWindowPermissionPlugin extends CordovaPlugin {

    private CallbackContext callbackContext = null;

    /* methods */

    public static final String ACTION_HAS_PERMISSION = "hasPermission";

    public static final String ACTION_REQUEST_PERMISSION = "requestPermission";

    /* return values */

    public static final int TRUE = 1;

    public static final int FALSE = 0;

    /* other */

    public static final int ANDROID_VERSION_MARSHMALLOW = 23;

    public static final int INVALID_ACTION = -1;

    public static final int REQUEST_SYSTEM_ALERT_WINDOW = 1;

    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {

        boolean success = true;

        if (ACTION_HAS_PERMISSION.equals(action)) {

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    callbackContext.success(hasPermission());
                }
            });

        } else if (ACTION_REQUEST_PERMISSION.equals(action)) {

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    SystemAlertWindowPermissionPlugin.this.callbackContext = callbackContext;
                    requestPermission();
                }
            });

        } else {

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    callbackContext.error(INVALID_ACTION);
                }
            });

            success = false;

        }

        return success;
    }

    protected int hasPermission() {
        if (Build.VERSION.SDK_INT < ANDROID_VERSION_MARSHMALLOW) {
            return TRUE;
        } else {
            return Settings.canDrawOverlays(cordova.getActivity()) ? TRUE : FALSE;
        }
    }

    protected void requestPermission() {
        if (Build.VERSION.SDK_INT >= ANDROID_VERSION_MARSHMALLOW) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + cordova.getActivity().getPackageName()));
            cordova.startActivityForResult((CordovaPlugin) this, intent, REQUEST_SYSTEM_ALERT_WINDOW);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (this.callbackContext == null) {
            return;
        }

        if (requestCode == REQUEST_SYSTEM_ALERT_WINDOW) {
            this.callbackContext.success(hasPermission());
            this.callbackContext = null;
        }
    }
}
package com.example.thesisdraft;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanSettings;
import android.content.pm.PackageManager;
import android.util.Log;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.xsens.dot.android.sdk.XsensDotSdk;
import com.xsens.dot.android.sdk.utils.XsensDotScanner;

import com.xsens.dot.android.sdk.interfaces.XsensDotScannerCallback;
import com.xsens.dot.android.sdk.utils.XsensDotScanner;
import com.xsens.dot.android.sdk.models.XsensDotDevice;

public class XSensDotApplication extends AppCompatActivity {
    private XsensDotScanner mXsDotScanner;
    //XsensDotDevice xsDevice = new XsensDotDevice(this, , this);


    private static final String TAG = XSensDotApplication.class.getSimpleName();


    //Setup for Xsens DOT SDK.

    public void initXsensDotSdk() {

        mXsDotScanner.startScan();
        //xsDevice.connect();


        // Get the version name of SDK.
        String version = XsensDotSdk.getSdkVersion();
        Log.i(TAG, "initXsensDotSdk() - version: " + version);
        //System.out.println(version);


        // Enable this feature to monitor logs from SDK.
        XsensDotSdk.setDebugEnabled(true);
        // Enable this feature then SDK will start reconnection when the connection is lost.
        XsensDotSdk.setReconnectEnabled(true);
    }

    public String sdk() {
        String version = XsensDotSdk.getSdkVersion();
        return version;
    }

    private void initXsDotScanner() {

        if (mXsDotScanner == null) {

            //mXsDotScanner = new XsensDotScanner(getContext(), this);
            mXsDotScanner.setScanMode(ScanSettings.SCAN_MODE_BALANCED);
        }
    }

    public void onXsensDotScanned(BluetoothDevice device) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String name = device.getName();
        String address = device.getAddress();
    }

}





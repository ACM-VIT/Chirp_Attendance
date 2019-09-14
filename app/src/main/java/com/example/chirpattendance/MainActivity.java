package com.example.chirpattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "API";
    SharedPreferences sharedPreferences;
    String uid = "123fsdf321";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("User Details", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        String uid = sharedPreferences.getString("uid", null);

        if (email == null && uid != null) {
            UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, userDetailsFragment);
            fragmentTransaction.commit();
        } else {
            AuthFragment authFragment = new AuthFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, authFragment);
            transaction.commit();
        }


        String imei = getImei();
        sendImei(imei);
    }

    /*private String fetchImei() {
        int MyDeviceAPI = Build.VERSION.SDK_INT;
        Log.e("API", MyDeviceAPI + "");
        String imei;
        if (MyDeviceAPI >= 26) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                Log.e("IMEI", "" + telephonyManager.getImei());
                return telephonyManager.getImei();
            }
            imei = telephonyManager.getImei();
            Log.e("IMEI###", "" + imei);
        } else {
            TelephonyManager tm = (TelephonyManager)
                    getSystemService(TELEPHONY_SERVICE);

            imei = tm.getDeviceId();
            Log.e("IMEI DEGRADE", "" + imei);
        }
        return imei;
    }*/

    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = "";
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return "";
            }
            imei = telephonyManager.getImei();
        }
        else
        {
            imei=telephonyManager.getDeviceId();
        }
    return imei;
    }

    private void sendImei(String imei) {
        Bundle bundle = new Bundle();
        bundle.putString("imei", imei);

        // set Fragmentclass Arguments

        UserDetailsFragment fragobj = new UserDetailsFragment();
        fragobj.setArguments(bundle);
    }


}

package com.example.chirpattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "API";
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new UserDetailsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        });

  /*      String imei = getImei();
        sendImei(imei);
*/

    }

  /*  private String getImei() {
        int MyDeviceAPI = Build.VERSION.SDK_INT;
        String imei = "";
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
                return telephonyManager.getImei();
            }
            imei = telephonyManager.getImei();
        } else {
            TelephonyManager tm = (TelephonyManager)
                    getSystemService(this.TELEPHONY_SERVICE);

            //imei = tm.getDeviceId();
        }
        return imei;
    }
*/
    private void sendImei(String imei) {
        Bundle bundle = new Bundle();
        bundle.putString("imei", imei);

        // set Fragmentclass Arguments

        UserDetailsFragment fragobj = new UserDetailsFragment();
        fragobj.setArguments(bundle);
    }


}

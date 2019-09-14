package com.example.chirpattendance;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.FirebaseDatabase;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.models.ChirpConnectState;
import io.chirp.connect.models.ChirpError;
import io.chirp.connect.interfaces.ConnectEventListener;


public class ChirpFragment extends Fragment {

    private ChirpConnect chirpConnect;
    private static final int RESULT_REQUEST_RECORD_AUDIO = 1;
    private LottieAnimationView animationView;
    String TAG = "CHIRP";
    String TAG1 = "Update";

    ImageView circle, mic;

    Context context;

    String CHIRP_APP_KEY = "c345Ab86Ad8F2cB9Cb368359F";
    String CHIRP_APP_SECRET = "3fC73BD19a2eC7a4adDb2CcAef59eBA7BB4fdEaECcc668da51";
    String CHIRP_APP_CONFIG = "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";

    Boolean startStopSdkBtnPressed = false;

    public ChirpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chirp, container, false);

        context = view.getContext();

        animationView = view.findViewById(R.id.animationView);
        mic = view.findViewById(R.id.mic);
        circle = view.findViewById(R.id.circle);
        circle.setImageAlpha(1);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                circle.setVisibility(View.VISIBLE);
                chirpConfig();

            }
        });


        return view;
    }

    private void chirpConfig() {

        chirpConnect = new ChirpConnect(context, CHIRP_APP_KEY, CHIRP_APP_SECRET);


        ChirpError setConfigError = chirpConnect.setConfig(CHIRP_APP_CONFIG);
        if (setConfigError.getCode() > 0) {
            Log.v("ChirpSDK: ", "Configured ChirpSDK");
        } else {
            mic.setAlpha(1f);
            mic.setClickable(true);

        }

        chirpConnect.setListener(connectEventListener);
    }

    ConnectEventListener connectEventListener = new ConnectEventListener() {
        @Override
        public void onSent(byte[] bytes, int i) {

        }

        @Override
        public void onSending(byte[] bytes, int i) {

        }

        @Override
        public void onReceived(byte[] bytes, int i) {
            /**
             * onReceived is called when a receive event has completed.
             * If the payload was decoded successfully, it is passed in data.
             * Otherwise, data is null.
             */
            animationView.pauseAnimation();

            String data = null;
            if (bytes != null) {
                //DECODING THE FORM
                FirebaseDatabase.getInstance().getReference().child("").child("");
                FirebaseDatabase.getInstance().getReference("https://chirpattendance.firebaseio.com/").child("");

            }

        }

        @Override
        public void onReceiving(int i) {

            /**
             * onReceiving is called when a receive event begins.
             * No data has yet been received.
             */

            animationView.setAnimation("animation1.json");
            animationView.playAnimation();
            animationView.setSpeed(10);
        }

        @Override
        public void onStateChanged(int oldState, int newState) {
            /**
             * onStateChanged is called when the SDK changes state.
             */
            Log.v(TAG, "ConnectCallback: onStateChanged " + oldState + " -> " + newState);
            if (newState == ChirpConnectState.CHIRP_CONNECT_STATE_NOT_CREATED.getCode()) {
                Log.e(TAG1, "Not Created");
            } else if (newState == ChirpConnectState.CHIRP_CONNECT_STATE_STOPPED.getCode()) {
                Log.e(TAG1, "Stopped");
            } else if (newState == ChirpConnectState.CHIRP_CONNECT_STATE_PAUSED.getCode()) {
                Log.e(TAG1, "Paused");
            } else if (newState == ChirpConnectState.CHIRP_CONNECT_STATE_RUNNING.getCode()) {
                Log.e(TAG1, "Running");
            } else if (newState == ChirpConnectState.CHIRP_CONNECT_STATE_SENDING.getCode()) {
                Log.e(TAG1, "Sending");
            } else if (newState == ChirpConnectState.CHIRP_CONNECT_STATE_RECEIVING.getCode()) {
                Log.e(TAG1, "Receiving");
            } else {
                Log.i(TAG1, newState + "");
            }
        }

        @Override
        public void onSystemVolumeChanged(float v, float v1) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        } else {

            if (startStopSdkBtnPressed) {
                startSdk();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (startStopSdkBtnPressed) stopSdk();
                }
                return;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        chirpConnect.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            chirpConnect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopSdk() {
        ChirpError error = chirpConnect.stop();
        if (error.getCode() > 0) {
            Log.e(TAG, error.getMessage());
            return;
        }
        mic.setAlpha(1f);
        mic.setClickable(true);
    }

    public void startSdk() {
        ChirpError error = chirpConnect.start();
        if (error.getCode() > 0) {
            Log.e(TAG, error.getMessage());
            return;
        }
       mic.setAlpha(1f);
        mic.setClickable(false);
    }

    public void startStopSdk(View view) {
        /**
         * Start or stop the SDK.
         * Audio is only processed when the SDK is running.
         */
        startStopSdkBtnPressed = true;
        if (chirpConnect.getState() == ChirpConnectState.CHIRP_CONNECT_STATE_STOPPED) {
            startSdk();
        } else {
            stopSdk();
        }

    }




    }





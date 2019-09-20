package com.example.chirpattendance;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;


public class AuthFragment extends Fragment {

    // Google Sign In
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // Google signInClient
    private GoogleSignInClient mGoogleSignInClient;

    // Progress bar
    Dialog dialog;

    public AuthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth,container,false);

        //PROGRESS DIALOG BOX
        AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
        builder.setView(R.layout.progress);
        dialog = builder.create();

        SignInButton signInButton = view.findViewById(R.id.sign_in_button);


        // SET THE DIMENSIONS OF THE SIGN IN BUTTON
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        //***************btn =view.findViewById(R.id.btn);

        // CONFIGURE GOOGLE SIGN IN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END CONFIG OF SIGN IN]

        mGoogleSignInClient = GoogleSignIn.getClient(container.getContext(), gso);


        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSwitch();

            }
        });*/
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setDialog(true);
                signIn();
                //fragmentSwitch();
            }
        });


    return  view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void fragmentSwitch() {
        Log.e("FRAGMENT", "Fragment Switch");
        Fragment fragment = new UserDetailsFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            Log.d(TAG, "handleSignInResult: Success");
            int b=9;

            setDialog(true);
            /*Intent myIntent = new Intent(MainActivity.this, Student_Profile.class);
            startActivity(myIntent);
            finish();*/
//            fragmentSwitch();
            setDialog(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setDialog(false);
        int a=9;
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            fragmentSwitch();
        }

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    //PROGRESS DIALOG
    private void setDialog(boolean show){
        if (show) {
            dialog.show();
        }
        else
            dialog.dismiss();
    }

}

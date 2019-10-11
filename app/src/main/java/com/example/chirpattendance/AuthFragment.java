package com.example.chirpattendance;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class AuthFragment extends Fragment {

    // Google Sign In
    private static final String TAG = "GoogleActivity";
    private static final int GOOGLE_SIGN_IN = 123;

    // Google signInClient
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    // Progress bar
    Dialog dialog;
    ProgressBar progressBar;

    public AuthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth, container, false);

        //PROGRESS DIALOG BOX
        /*AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
        builder.setView(R.layout.);
        dialog = builder.create();


*/
        progressBar = view.findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        SignInButton signInButton = view.findViewById(R.id.sign_in_button);


        // SET THE DIMENSIONS OF THE SIGN IN BUTTON
        signInButton.setSize(SignInButton.SIZE_STANDARD);


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
//                setDialog(true);
                signIn();
                //fragmentSwitch();
               //fragmentSwitch();
            }
        });


        return view;
    }




/*
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            Log.d(TAG, "handleSignInResult: Success");
            int b = 9;
            Toast.makeText(getContext(), "SIGN IN SUCCESSFULL", Toast.LENGTH_LONG);

            //        setDialog(true);



            *//*Intent myIntent = new Intent(MainActivity.this, Student_Profile.class);
            startActivity(myIntent);
            finish();*//*
//            fragmentSwitch();

            //      setDialog(false);
//            setDialog(false);
        }
    }*/

    private void signIn() {
        progressBar.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
                Log.e(TAG, "Google Sign In successful with Account Id" + account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }

        }
    }
    // [END onactivityresult]

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            fragmentSwitch();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
    // [END auth_with_google]

    private void fragmentSwitch() {
        Log.e("FRAGMENT", "Fragment Switch");
        Fragment fragment = new UserDetailsFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}



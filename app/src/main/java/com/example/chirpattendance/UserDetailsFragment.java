package com.example.chirpattendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class UserDetailsFragment extends Fragment {

    private EditText edtEmail;
    private EditText edtName;
    private EditText edtUserId;
    private TextView tvName;
    private TextView tvUserId;
    private TextView tvEmail;

    private ImageButton btnSubmit;

    SharedPreferences sharedPreferences;
    String imei;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtEmail.setEnabled(false);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        edtName = view.findViewById(R.id.edtName);
        edtUserId = view.findViewById(R.id.edtUserId);



        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


        //FETCHING THE DETAILS OF THE CURRENT USER

        if (currentUser != null) {
            String name = currentUser.getDisplayName();
            if (name == null || name.length() == 0) {
                edtName.setText(edtName.getHint());
            } else {
                edtName.setText(name);
            }

            String email = currentUser.getEmail();
            edtEmail.setText(email);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = edtEmail.getText().toString();
                String name = edtName.getText().toString();
                String uid = currentUser.getUid();
                String user_id = edtUserId.getText().toString().trim();
/*
                String email = "devaansh51@gmail.com";
                String name = "DEVANSH";
                String uid = "123466eg";
                String user_id = "213648as";
*/

                DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("users");
                Map<String, User> users = new HashMap<>();
                users.put(uid, new User(email, name, user_id));
                myref.setValue(users);

                try {
                    sharedPreferences = getActivity().getSharedPreferences("User Details", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("uid", uid);
                    editor.apply();
                } catch (NullPointerException e) {
                    Log.e("NULL", "NULL SHARED PREFERENCES" + e.getMessage());
                }

                fragmentSwitch();
            }
        });


        return view;

    }

    private void fragmentSwitch() {
        Log.e("FRAGMENT", "NEW FRAGMENT");
        Fragment fragment = new ChirpFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();


    }
   /* private Boolean duplicationVerify(){

        String imei = getArguments().getString("imei");

     DatabaseReference myRef=FirebaseDatabase.getInstance().getReference();

     if (myRef)
     myRef.child("rooms").child("attendees").setValue(imei);




    }*/



}

package com.example.chirpattendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class UserDetailsFragment extends Fragment {

    private TextInputEditText edt1;
    private TextInputEditText edt2;
    private TextInputEditText edt3;

    private Button btnSubmit;

    SharedPreferences sharedPreferences;
    String Imei;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);

        edt1 = view.findViewById(R.id.edt1);
        edt1.setEnabled(false);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        edt2 = view.findViewById(R.id.edt2);
        edt3 = view.findViewById(R.id.edt3);

        try {
            String imei = getArguments().getString("imei");
        } catch (NullPointerException e) {
            Log.e(".....", "" + e.getMessage());
        }

//        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


        //FETCHING THE DETAILS OF THE CURRENT USER

       /* if (currentUser != null) {
            String name = currentUser.getDisplayName();
            if (name == null || name.length() == 0) {
                edt3.setText(edt3.getHint());
            } else {
                edt3.setText(name);
            }

            String email = currentUser.getEmail();
            edt1.setText(email);
        }*/

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* String email = edt1.getText().toString();
                String name = edt3.getText().toString();
                String uid = currentUser.getUid();
                String user_id = edt2.getText().toString().trim();
*/

                String email = "devaansh51@gmail.com";
                String name = "DEVANSH";
                String uid = "123466eg";
                String user_id = "213648as";

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


}

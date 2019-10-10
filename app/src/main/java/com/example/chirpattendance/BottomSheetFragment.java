package com.example.chirpattendance;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private TextView roomName;
    private TextView roomId;
    private TextView userId;
    private TextView roomDate;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    public BottomSheetFragment(Context context) {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        roomName = view.findViewById(R.id.roomNme);
        roomId = view.findViewById(R.id.roomId);
        roomDate = view.findViewById(R.id.roomDate);
        userId = view.findViewById(R.id.userId);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);

        Bundle bundle = getArguments();

       /* roomName.setText(roomName);
        roomId.setText();
        roomDate.setText();
        userId.setText();*/



        return view;
    }

}




package com.example.androidhomework6;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionsFragment extends Fragment {

    static final String ARG_INDEX = "index";
    private int index;
    public DescriptionsFragment() {
        // Required empty public constructor
    }


    public static DescriptionsFragment newInstance(int index) {
        DescriptionsFragment f = new DescriptionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_descriptions, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTitle = view.findViewById(R.id.titel);
        TypedArray typedArrayTitle = getResources().obtainTypedArray(R.array.notesTitle);
        tvTitle.setText(typedArrayTitle.getText(index));
        tvTitle.setTextSize(40);

        TextView tvDescription = view.findViewById(R.id.description);
        TypedArray typedArrayDescription = getResources().obtainTypedArray(R.array.notesDescription);
        tvDescription.setText(typedArrayDescription.getText(index));
        tvDescription.setTextSize(30);

        TextView tvDate = view.findViewById(R.id.date);
        TypedArray typedArrayDate = getResources().obtainTypedArray(R.array.notesDate);
        tvDate.setText(typedArrayDate.getText(index));
        tvDate.setTextSize(30);
    }
}
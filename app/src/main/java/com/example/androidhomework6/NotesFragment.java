package com.example.androidhomework6;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NotesFragment extends Fragment {
    private boolean isLandscape;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if(isLandscape){
           showDescriptionNotes(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
    }

    private void initList(View view) {

        LinearLayout layoutView = (LinearLayout) view;
        String[] notesTitle = getResources().getStringArray(R.array.notesTitle);
        String[] notesDate = getResources().getStringArray(R.array.notesDate);

        for (int i = 0; i < notesTitle.length; i++) {
            Context context = getContext();
            if (context != null) {
                String note = notesTitle[i];
                String date = notesDate[i];
                TextView tvNote = new TextView(getContext());
                TextView tvDate = new TextView(getContext());
                tvNote.setText(note);
                tvDate.setText(date);
                tvNote.setTextSize(25);
                tvDate.setTextSize(25);
                layoutView.addView(tvNote);
                layoutView.addView(tvDate);
                final int fi = i;
                tvNote.setOnClickListener(v -> showDescriptionNotes(fi));
            }

        }
    }

    private void showDescriptionNotes(int index) {
        if (isLandscape) {
            showLandNotes(index);
        } else {
            showPortNotes(index);
        }
    }

    private void showLandNotes(int index) {
        DescriptionsFragment detail = DescriptionsFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.descriptionLand, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    private void showPortNotes(int index) {
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, NotesActivity.class);
            intent.putExtra(DescriptionsFragment.ARG_INDEX, index);
            startActivity(intent);
        }
    }
}
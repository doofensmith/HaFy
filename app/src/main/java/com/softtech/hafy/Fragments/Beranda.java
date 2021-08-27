package com.softtech.hafy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.softtech.hafy.Edukasi;
import com.softtech.hafy.Konsultasi;
import com.softtech.hafy.Pembayaran;
import com.softtech.hafy.R;


public class Beranda extends Fragment {

    //declare view
    MaterialCardView menukonsultasi;
    MaterialButton btnchat;
    MaterialCardView menuedukasi;

    public Beranda() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        //init view
        menukonsultasi = rootView.findViewById(R.id.item_beranda_1_menukonsultasi);
        btnchat = rootView.findViewById(R.id.item_beranda_3_btnchat);
        menuedukasi = rootView.findViewById(R.id.item_beranda_1_menuedukasi);

        //menukonsultasi
        menukonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Konsultasi.class);
                startActivity(intent);
            }
        });

        //btnchat
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pembayaran.class);
                startActivity(intent);
            }
        });

        //menu edukasi
        menuedukasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Edukasi.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
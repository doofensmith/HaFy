package com.softtech.hafy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.softtech.hafy.BuatArtikel;
import com.softtech.hafy.R;


public class Artikel extends Fragment {

    public Artikel() {
        // Required empty public constructor
    }

    //declare view
    MaterialToolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_artikel, container, false);

        //init view
        toolbar = rootView.findViewById(R.id.fragment_artikel_toolbar);

        //action menu toolbar
        toolbar.setOnMenuItemClickListener(menuItemClickListener());


        return rootView;
    }

    //action menu toolbar function
    Toolbar.OnMenuItemClickListener menuItemClickListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_toolbar_artikel_buat:
                        Intent intent = new Intent(getContext(), BuatArtikel.class);
                        startActivity(intent);
                        return true;
                }

                return true;
            }
        };
    }
}
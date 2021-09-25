package com.softtech.hafy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softtech.hafy.BuatArtikel;
import com.softtech.hafy.R;
import com.softtech.hafy.adapter.AArticle;
import com.softtech.hafy.model.MArtikel;
import com.softtech.hafy.viewholder.VHAccount;
import com.softtech.hafy.viewholder.VHArticle;


public class Artikel extends Fragment {

    public Artikel() {
        // Required empty public constructor
    }

    //declare view
    MaterialToolbar toolbar;

    //firebase
    FirebaseAuth auth;
    FirestoreRecyclerOptions<MArtikel> options;
    FirestoreRecyclerAdapter<MArtikel, VHArticle> adapter;
    RecyclerView recyclerView;
    Query query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_artikel, container, false);

        //init view
        toolbar = rootView.findViewById(R.id.fragment_artikel_toolbar);

        //action menu toolbar
        toolbar.setOnMenuItemClickListener(menuItemClickListener());

        //INFLATE ARTICLE
        //auth
        auth = FirebaseAuth.getInstance();
        query = FirebaseFirestore.getInstance().collection("articles");
        //option
        options = new FirestoreRecyclerOptions.Builder<MArtikel>()
                .setLifecycleOwner(this)
                .setQuery(query,MArtikel.class).build();
        //adapter
        adapter = new AArticle(options);
        //recycler view
        recyclerView = rootView.findViewById(R.id.fragment_artikel_recyclerview);
        recyclerView.setAdapter(adapter);
        //

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
package com.softlaboratory.hafy.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softlaboratory.hafy.BuatArtikel;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.adapter.AArticleContainer;
import com.softlaboratory.hafy.model.MArticleTag;
import com.softlaboratory.hafy.viewholder.VHArticleContainer;


public class Artikel extends Fragment {

    public Artikel() {
        // Required empty public constructor
    }

    //declare view
    MaterialToolbar toolbar;

    //firebase
    FirebaseAuth auth;
    FirestoreRecyclerOptions<MArticleTag> options;
    FirestoreRecyclerAdapter<MArticleTag, VHArticleContainer> adapter;
    RecyclerView recyclerView;
    Query query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_artikel, container, false);

        //warna status bar
        //warna status bar
        if (Build.VERSION.SDK_INT>=21) {
            Window window = Artikel.this.getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Artikel.this.getResources().getColor(R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //init view
        toolbar = rootView.findViewById(R.id.fragment_artikel_toolbar);

        //action menu toolbar
        toolbar.setOnMenuItemClickListener(menuItemClickListener());

        //INFLATE ARTICLE
        //auth
        auth = FirebaseAuth.getInstance();
        query = FirebaseFirestore.getInstance().collection("article_tag");
        //option
        options = new FirestoreRecyclerOptions.Builder<MArticleTag>()
                .setLifecycleOwner(Artikel.this)
                .setQuery(query, MArticleTag.class).build();
        //adapter
        adapter = new AArticleContainer(options);
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
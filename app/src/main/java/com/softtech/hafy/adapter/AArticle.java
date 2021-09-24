package com.softtech.hafy.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.softtech.hafy.model.MArtikel;
import com.softtech.hafy.viewholder.VHArticle;

public class AArticle extends FirebaseRecyclerAdapter<MArtikel, VHArticle> {

    
    public AArticle(@NonNull FirebaseRecyclerOptions<MArtikel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHArticle holder, int position, @NonNull MArtikel model) {

    }

    @NonNull
    @Override
    public VHArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
}

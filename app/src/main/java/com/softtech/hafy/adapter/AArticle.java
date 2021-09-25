package com.softtech.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.softtech.hafy.R;
import com.softtech.hafy.model.MArtikel;
import com.softtech.hafy.viewholder.VHArticle;

public class AArticle extends FirestoreRecyclerAdapter<MArtikel, VHArticle> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AArticle(@NonNull FirestoreRecyclerOptions<MArtikel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHArticle holder, int position, @NonNull MArtikel model) {
        holder.articleTitle.setText(model.getArticleTitle());
    }

    @NonNull
    @Override
    public VHArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //view to inflate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel, parent, false);
        return new VHArticle(view);
    }
}

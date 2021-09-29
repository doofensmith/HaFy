package com.softtech.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.softtech.hafy.R;
import com.softtech.hafy.model.MArticle;
import com.softtech.hafy.viewholder.VHHomeArticle;

public class AHomeArticle extends FirestoreRecyclerAdapter<MArticle, VHHomeArticle> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AHomeArticle(@NonNull FirestoreRecyclerOptions<MArticle> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHHomeArticle holder, int position, @NonNull MArticle model) {
        holder.title.setText(model.getArticleTitle());
    }

    @NonNull
    @Override
    public VHHomeArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beranda_article_item,parent,false);
        return new VHHomeArticle(view);
    }
}

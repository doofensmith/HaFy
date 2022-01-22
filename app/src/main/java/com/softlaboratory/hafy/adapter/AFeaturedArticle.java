package com.softlaboratory.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MArticle;
import com.softlaboratory.hafy.viewholder.VHFeaturedArticle;

public class AFeaturedArticle extends FirestoreRecyclerAdapter<MArticle, VHFeaturedArticle> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AFeaturedArticle(@NonNull FirestoreRecyclerOptions<MArticle> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHFeaturedArticle holder, int position, @NonNull MArticle model) {

        holder.articleTitle.setText(model.getArticleTitle());

    }

    @NonNull
    @Override
    public VHFeaturedArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beranda_featuredarticle_item,parent,false);
        return new VHFeaturedArticle(view);
    }
}

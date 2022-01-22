package com.softlaboratory.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MArticle;
import com.softlaboratory.hafy.viewholder.VHArticle;

public class AArticle extends FirestoreRecyclerAdapter<MArticle, VHArticle> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AArticle(@NonNull FirestoreRecyclerOptions<MArticle> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHArticle holder, int position, @NonNull MArticle model) {
        holder.articleTitle.setText(model.getArticleTitle());
        holder.articleWriterAndDate.setText(model.getArticleWriter()+" · "+model.getDatePublished());
    }

    @NonNull
    @Override
    public VHArticle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //view to inflate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel, parent, false);
        return new VHArticle(view);
    }
}

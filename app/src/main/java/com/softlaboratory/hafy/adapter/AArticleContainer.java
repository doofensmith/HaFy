package com.softlaboratory.hafy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MArticle;
import com.softlaboratory.hafy.model.MArticleTag;
import com.softlaboratory.hafy.viewholder.VHArticle;
import com.softlaboratory.hafy.viewholder.VHArticleContainer;

public class AArticleContainer extends FirestoreRecyclerAdapter<MArticleTag, VHArticleContainer> {

    Context context;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirestoreRecyclerOptions<MArticle> options;
    FirestoreRecyclerAdapter<MArticle, VHArticle> adapter;
    Query query;

    public AArticleContainer(@NonNull FirestoreRecyclerOptions<MArticleTag> options) {
        super(options);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onBindViewHolder(@NonNull VHArticleContainer holder, int position, @NonNull MArticleTag model) {

        query = firestore.collection("articles").whereEqualTo("articleTag",model.getArticleTag()).limit(3);
        holder.articleTag.setText(model.getArticleTag());
        options = new FirestoreRecyclerOptions.Builder<MArticle>()
                .setQuery(query, MArticle.class).build();
        adapter = new AArticle(options);
        holder.itemArticle.setAdapter(adapter);
        adapter.startListening();

    }

    @NonNull
    @Override
    public VHArticleContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel_container,parent,false);
        return new VHArticleContainer(view);
    }
}

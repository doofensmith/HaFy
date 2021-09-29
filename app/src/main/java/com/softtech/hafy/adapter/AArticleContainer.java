package com.softtech.hafy.adapter;

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
import com.softtech.hafy.R;
import com.softtech.hafy.fragments.Artikel;
import com.softtech.hafy.model.MArtikel;
import com.softtech.hafy.model.MArtikelTag;
import com.softtech.hafy.viewholder.VHArticle;
import com.softtech.hafy.viewholder.VHArticleContainer;

public class AArticleContainer extends FirestoreRecyclerAdapter<MArtikelTag, VHArticleContainer> {

    Context context;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirestoreRecyclerOptions<MArtikel> options;
    FirestoreRecyclerAdapter<MArtikel, VHArticle> adapter;
    Query query;

    public AArticleContainer(@NonNull FirestoreRecyclerOptions<MArtikelTag> options) {
        super(options);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onBindViewHolder(@NonNull VHArticleContainer holder, int position, @NonNull MArtikelTag model) {

        query = firestore.collection("articles").whereEqualTo("articleTag",model.getArticleTag()).limit(3);
        holder.articleTag.setText(model.getArticleTag());
        options = new FirestoreRecyclerOptions.Builder<MArtikel>()
                .setQuery(query,MArtikel.class).build();
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
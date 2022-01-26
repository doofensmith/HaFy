package com.softlaboratory.hafy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MAccount;
import com.softlaboratory.hafy.model.MArticle;
import com.softlaboratory.hafy.model.MEducationCategory;
import com.softlaboratory.hafy.model.MHomeContainer;
import com.softlaboratory.hafy.viewholder.VHArticle;
import com.softlaboratory.hafy.viewholder.VHEducationCategory;
import com.softlaboratory.hafy.viewholder.VHHomeContainer;

import java.util.Arrays;

public class AHomeContainer extends FirestoreRecyclerAdapter<MHomeContainer, VHHomeContainer> {

    Context context;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    public AHomeContainer(@NonNull FirestoreRecyclerOptions<MHomeContainer> options, Context context, FirebaseAuth auth, FirebaseFirestore firestore) {
        super(options);
        this.context = context;
        this.auth = auth;
        this.firestore = firestore;
    }

    @Override
    protected void onBindViewHolder(@NonNull VHHomeContainer holder, int position, @NonNull MHomeContainer model) {

        if (position == 0) {
            holder.title.setText(model.getTitle());
            Query query = firestore.collection("account")
                .whereNotIn("keyAccount", Arrays.asList(auth.getUid()))
                .whereEqualTo("professional",true)
                .limit(5);
            FirestoreRecyclerOptions<MAccount> options = new FirestoreRecyclerOptions.Builder<MAccount>()
                    .setQuery(query,MAccount.class)
                    .setLifecycleOwner((LifecycleOwner) context)
                    .build();
            FirestoreRecyclerAdapter adapter = new AProfessionalAccount(options,context);
            holder.recyclerView.setAdapter(adapter);
            adapter.startListening();

        }else if (position == 1) {
            holder.title.setText(model.getTitle());
            Query query = firestore.collection("education_category");
            FirestoreRecyclerOptions<MEducationCategory> options = new FirestoreRecyclerOptions.Builder<MEducationCategory>()
                    .setLifecycleOwner((LifecycleOwner) context)
                    .setQuery(query,MEducationCategory.class)
                    .build();
            FirestoreRecyclerAdapter<MEducationCategory, VHEducationCategory> adapter = new AEducationCategory(options,context);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            holder.recyclerView.setLayoutManager(manager);
            holder.recyclerView.setAdapter(adapter);
        }else if (position == 2) {
            holder.title.setText(model.getTitle());
            Query query = firestore.collection("articles").limit(10);
            FirestoreRecyclerOptions<MArticle> options = new FirestoreRecyclerOptions.Builder<MArticle>()
                    .setLifecycleOwner((LifecycleOwner) context)
                    .setQuery(query,MArticle.class)
                    .build();
            FirestoreRecyclerAdapter<MArticle,VHArticle> adapter = new AArticle(options,context);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            holder.recyclerView.setLayoutManager(manager);
            holder.recyclerView.setAdapter(adapter);
        }else {

        }

    }

    @NonNull
    @Override
    public VHHomeContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_container,parent,false);
        return new VHHomeContainer(view);
    }
}

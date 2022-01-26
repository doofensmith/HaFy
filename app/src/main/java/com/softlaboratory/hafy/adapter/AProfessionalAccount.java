package com.softlaboratory.hafy.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.softlaboratory.hafy.ChatPerson;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MAccount;
import com.softlaboratory.hafy.viewholder.VHProfessionalAccount;

public class AProfessionalAccount extends FirestoreRecyclerAdapter<MAccount, VHProfessionalAccount> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    //param
    Context context;
//    FirebaseAuth auth;
//    FirebaseFirestore firestore;


    public AProfessionalAccount(@NonNull FirestoreRecyclerOptions<MAccount> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VHProfessionalAccount holder, int position, @NonNull MAccount model) {
        holder.accountName.setText(model.getUserName());
        holder.job.setText(model.getJob());
        holder.rating.setText(model.getRating());
        holder.workExperience.setText("1");
        holder.onTime.setText(model.getLastOnline());
        holder.price.setText("Rp. "+model.getPrice());
        Glide.with(context).load(model.getProfilePic()).into(holder.profilePic);


        //button chat
        holder.buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bundle
                Bundle bundle = new Bundle();
                bundle.putString("keyAccount",model.getKeyAccount());
                //intent
                Intent intent = new Intent(context, ChatPerson.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public VHProfessionalAccount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beranda_proaccount_item,parent,false);
        return new VHProfessionalAccount(view);
    }
}

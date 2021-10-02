package com.softtech.hafy.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.softtech.hafy.ChatPerson;
import com.softtech.hafy.R;
import com.softtech.hafy.model.MAccount;
import com.softtech.hafy.viewholder.VHProfessionalAccount;

public class AProfessionalAccount extends FirestoreRecyclerAdapter<MAccount, VHProfessionalAccount> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    //param
    Context context;

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
        holder.onTime.setText("");
        holder.price.setText("Rp. ");

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

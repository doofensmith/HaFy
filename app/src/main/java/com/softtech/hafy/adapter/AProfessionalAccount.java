package com.softtech.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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
    public AProfessionalAccount(@NonNull FirestoreRecyclerOptions<MAccount> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHProfessionalAccount holder, int position, @NonNull MAccount model) {
        holder.accountName.setText(model.getUserName());
        holder.job.setText(model.getJob());
        holder.rating.setText(model.getRating());
        holder.workExperience.setText("1");
        holder.onTime.setText("");
        holder.price.setText("Rp. ");
    }

    @NonNull
    @Override
    public VHProfessionalAccount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beranda_proaccount_item,parent,false);
        return new VHProfessionalAccount(view);
    }
}

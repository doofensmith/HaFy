package com.softlaboratory.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MEducationCategory;
import com.softlaboratory.hafy.viewholder.VHEducationCategory;

public class AEducationCategory extends FirestoreRecyclerAdapter<MEducationCategory, VHEducationCategory> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AEducationCategory(@NonNull FirestoreRecyclerOptions<MEducationCategory> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VHEducationCategory holder, int position, @NonNull MEducationCategory model) {
        holder.category.setText(model.getCategory());
    }

    @NonNull
    @Override
    public VHEducationCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beranda_edukasi_item,parent,false);
        return new VHEducationCategory(view);
    }
}

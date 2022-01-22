package com.softlaboratory.hafy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.softlaboratory.hafy.R;
import com.softlaboratory.hafy.model.MChatPerson;
import com.softlaboratory.hafy.viewholder.VHChatPerson;

public class AChatPerson extends FirestoreRecyclerAdapter<MChatPerson, VHChatPerson> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public static final int RIGHT_CHAT = 0;
    public static final int LEFT_CHAT = 1;

    FirebaseAuth auth;

    public AChatPerson(@NonNull FirestoreRecyclerOptions<MChatPerson> options) {
        super(options);

        auth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onBindViewHolder(@NonNull VHChatPerson holder, int position, @NonNull MChatPerson model) {
        holder.message.setText(model.getMessage());
    }

    @NonNull
    @Override
    public VHChatPerson onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==RIGHT_CHAT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_kanan, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_kiri, parent, false);
        }
        return new VHChatPerson(view);
    }

    @Override
    public int getItemViewType(int position) {

        if (auth.getUid().equals(getItem(position).getSender())) {
            return RIGHT_CHAT;
        }
        else {
            return LEFT_CHAT;
        }

    }
}

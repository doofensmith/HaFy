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
import com.softtech.hafy.model.MChatRoom;
import com.softtech.hafy.viewholder.VHChatRoom;

public class AChatRoom extends FirestoreRecyclerAdapter<MChatRoom, VHChatRoom> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;

    public AChatRoom(@NonNull FirestoreRecyclerOptions<MChatRoom> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VHChatRoom holder, int position, @NonNull MChatRoom model) {
        holder.name.setText(model.getTargetName());
        holder.lastChat.setText(model.getLastChat());

        //on click
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bundle
                Bundle bundle = new Bundle();
                bundle.putString("keyAccount",model.getTargetKey());
                //intent
                Intent intent = new Intent(context, ChatPerson.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public VHChatRoom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room,parent,false);
        return new VHChatRoom(view);
    }
}

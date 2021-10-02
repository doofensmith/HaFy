package com.softtech.hafy.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softtech.hafy.R;

public class VHChatPerson extends RecyclerView.ViewHolder {

    public TextView message;

    public VHChatPerson(@NonNull View itemView) {
        super(itemView);

        message = itemView.findViewById(R.id.item_chat_message);

    }
}

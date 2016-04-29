package ru.mgutupenza.mgutuinformer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.model.server.chat.ChatUser;

public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.ChatViewHolder>{

    List<ChatUser> users;

    public ChatRVAdapter(List<ChatUser> users) {
        this.users = users;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getName());
        holder.lastMessage.setText(users.get(position).getLastMessage());
        holder.time.setText(users.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView userName, lastMessage, time;

        public ChatViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.icon_user_chat);
            userName = (TextView)itemView.findViewById(R.id.user_name_chat);
            lastMessage = (TextView)itemView.findViewById(R.id.last_message_chat);
            time = (TextView)itemView.findViewById(R.id.time_message_chat);
        }
    }

}

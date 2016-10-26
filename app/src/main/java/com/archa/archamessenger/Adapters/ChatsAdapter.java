package com.archa.archamessenger.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archa.archamessenger.Models.Chats;
import com.archa.archamessenger.Models.Contacts;
import com.archa.archamessenger.R;

import java.util.List;

/**
 * Created by Boo on 2016-10-13.
 */
public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.RecyclerViewHolders>{

    private List<Chats> itemList;
    private Context mContext;
    public View rootView;

    public ChatsAdapter(Context context, List<Chats> itemList){
        this.mContext = context;
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chats, null);
        RecyclerViewHolders viewHolders = new RecyclerViewHolders(rootView);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.image_circle.setImageResource(itemList.get(position).getProfile());
        holder.tv_chat_title.setText(itemList.get(position).getTitle());
        holder.tv_last_chat.setText(itemList.get(position).getLast_chat());
        holder.tv_date.setText(itemList.get(position).getDate());
        if(itemList.get(position).is_read())            holder.image_read.setVisibility(View.VISIBLE);
        else if(!itemList.get(position).is_read())      holder.image_read.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        public ImageView image_circle;
        public TextView tv_chat_title;
        public TextView tv_last_chat;
        public TextView tv_date;
        public ImageView image_read;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            View layout_profile = itemView.findViewById(R.id.include_profile);
            image_circle = (ImageView)layout_profile.findViewById(R.id.image_profile);
            tv_chat_title = (TextView)itemView.findViewById(R.id.tv_chat_title);
            tv_last_chat = (TextView)itemView.findViewById(R.id.tv_last_chat);
            tv_date = (TextView)itemView.findViewById(R.id.tv_date);
            image_read = (ImageView)itemView.findViewById(R.id.image_read);

            ImageView image_x = (ImageView)layout_profile.findViewById(R.id.image_x);
            TextView tv_item_userName = (TextView)layout_profile.findViewById(R.id.tv_item_userName);
            image_x.setVisibility(View.GONE);
            tv_item_userName.setVisibility(View.GONE);
        }
    }
}

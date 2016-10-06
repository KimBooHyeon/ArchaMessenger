package com.archa.archamessenger.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.archa.archamessenger.Models.Contacts;
import com.archa.archamessenger.R;

import java.util.List;

/**
 * Created by doosoun on 16. 10. 5..
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.RecyclerViewHolders> {

    private List<Contacts> itemList;
    private Context mContext;
    public View rootView;

    public ContactsAdapter(Context context, List<Contacts> itemList){
        this.mContext = context;
        this.itemList = itemList;
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contacs, null);
        RecyclerViewHolders viewHolders = new RecyclerViewHolders(rootView);

        return viewHolders;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.image_circle.setImageResource(itemList.get(position).getProfile());
        holder.personName.setText(itemList.get(position).getPersonName());
        holder.personPhone.setText(itemList.get(position).getPersonPhone());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        public ImageView image_circle;
        public TextView personName;
        public TextView personPhone;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            View layout_profile = itemView.findViewById(R.id.include_profile);
            image_circle = (ImageView)layout_profile.findViewById(R.id.image_profile);

            personName = (TextView)itemView.findViewById(R.id.tv_userName);
            personPhone = (TextView)itemView.findViewById(R.id.tv_userNumber);

            ImageView image_x = (ImageView)layout_profile.findViewById(R.id.image_x);
            TextView tv_item_userName = (TextView)layout_profile.findViewById(R.id.tv_item_userName);
            image_x.setVisibility(View.GONE);
            tv_item_userName.setVisibility(View.GONE);
        }
    }
}

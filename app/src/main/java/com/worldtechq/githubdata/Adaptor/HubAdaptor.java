package com.worldtechq.githubdata.Adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.worldtechq.githubdata.Activity.User;
import com.worldtechq.githubdata.R;

public class HubAdaptor extends RecyclerView.Adapter<HubAdaptor.HubViewHolder> {

    private Context context;
    private User[] data;

    public HubAdaptor(Context context, User[] data){
    this.context=context;
    this.data=data;

    }

    @NonNull
    @Override
    public HubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_user_layout,parent,false);
        return new HubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HubViewHolder holder, int position) {
        final User user = data[position];
        holder.txtuser.setText(user.getLogin());
        Glide.with(context).load(user.getAvatarUrl()).into(holder.imguser);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,user.getLogin(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class HubViewHolder extends RecyclerView.ViewHolder{

        TextView txtuser;
        ImageView imguser;
        public HubViewHolder(View itemView) {
            super(itemView);
            txtuser=itemView.findViewById(R.id.txt);
            imguser=itemView.findViewById(R.id.img);

        }
    }

}

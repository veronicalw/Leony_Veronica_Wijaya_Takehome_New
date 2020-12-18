package com.example.leony_veronica_wijaya_takehome.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;
import com.example.leony_veronica_wijaya_takehome.R;
import com.example.leony_veronica_wijaya_takehome.View.Activity.DetailActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapters extends RecyclerView.Adapter<Adapters.AdapterViewHolder> {
    private Context mContext;
    private List<UserData> userDataList;
    private final int limit = 10000;

    public Adapters(Context mContext, List<UserData> userData){
        this.mContext = mContext;
        this.userDataList = userData;
    }

    @NonNull
    @Override
    public Adapters.AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapters.AdapterViewHolder holder, int position) {
        UserData user = userDataList.get(position);
        Glide.with(mContext)
                .load(user.getAvatarUrl())
                .placeholder(R.color.soft_blue)
                .into(holder.circleImgUser);
        holder.username.setText(user.getLogin());
        holder.url.setText(user.getUrl());

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }
    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView username, url, showDetail;
        ImageView circleImgUser;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txtUserName);
            url = itemView.findViewById(R.id.txtUrlUser);
            circleImgUser = itemView.findViewById(R.id.circleImgUser);
            showDetail = itemView.findViewById(R.id.linkdetail);
            showDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserData userData = userDataList.get(getAdapterPosition());
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.SEARCH_DATA, userData);
                    view.getContext().startActivity(intent);
                    Toast.makeText(mContext, userData.getLogin(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

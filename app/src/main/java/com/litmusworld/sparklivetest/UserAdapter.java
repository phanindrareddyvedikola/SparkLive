package com.litmusworld.sparklivetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litmusworld.sparklivetest.databinding.UserAdapterBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    private UpdateCount updateCountListener;

    private interface UpdateCount{

        void updateCountData(List<User> list);
    }

    public UserAdapter(Context context, List<User> userList) {

        this.context = context;
        this.userList = userList;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(UserAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        User user = userList.get(position);
        Glide.with(context).load(user.getProfileImage()).into(holder.rowXmlViewBinding.roundedImage);
        Glide.with(context).load(user.getThumbnail()).into(holder.rowXmlViewBinding.ivThumbnail);
        holder.rowXmlViewBinding.tvname.setText(user.getName());
        holder.rowXmlViewBinding.tvShortBio.setText(user.getShortBio());
        holder.rowXmlViewBinding.tvPublishAt.setText(user.getPublishedAt());
        holder.rowXmlViewBinding.tvDescription.setText(user.getBio());
        holder.rowXmlViewBinding.tvLikes.setText(user.getLikesCount() + " Likes");
        holder.rowXmlViewBinding.tvViews.setText(user.getViewCount() + " Views");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private UserAdapterBinding rowXmlViewBinding;

        public ViewHolder(final UserAdapterBinding rowXmlViewBinding) {
            super(rowXmlViewBinding.getRoot());
            this.rowXmlViewBinding = rowXmlViewBinding;

            rowXmlViewBinding.imLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    User user = userList.get(getAbsoluteAdapterPosition());
                    user.setLikesCount(user.getLikesCount()+1);
                    notifyItemChanged(getAbsoluteAdapterPosition(),user);

                }
            });

        }
    }


}

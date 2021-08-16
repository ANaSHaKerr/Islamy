package com.bfcai.islamy.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfcai.islamy.Model.Story;
import com.bfcai.islamy.R;
import com.bfcai.islamy.Screens.Stories.Components.storyDetailsActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.storyViewHolder> {

    List<Story> storyList;
    Activity context;
    FirebaseFirestore firestore;

    public StoryAdapter(List<Story> storyList, Activity context) {
        this.storyList = storyList;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryAdapter.storyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.story_layout,parent,false);
       firestore = FirebaseFirestore.getInstance();
       return new storyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull storyViewHolder holder, int position) {
        Story story = storyList.get(position);
        holder.setPostImage(story.getImage());
        holder.setPostTitle(story.getTitle());
        holder.setPostDes(story.getDes());
        long miliseconds = story.getDate().getTime();
        String date = DateFormat.format("MM/dd/yyyy",new Date(miliseconds)).toString();
        holder.setdate(date);
        holder.view.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), storyDetailsActivity.class);
            i.putExtra("title",  story.getTitle());
            i.putExtra("des",  story.getDes());
            i.putExtra("image",  story.getImage());
            i.putExtra("date",  story.getDate());
            v.getContext().startActivity(i);


        });

    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class storyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView , likePic;
        TextView title,des,Pdate,likeCount;
        View view;
        public storyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setPostImage(String imageurl){
            imageView = view.findViewById(R.id.user_post);
            Glide.with(context).load(imageurl).into(imageView);
        }
        public void setPostTitle(String titlePost){
            title = view.findViewById(R.id.caption_tv);
            title.setText(titlePost.length() > 10 ? titlePost.substring(0,10)+"..." : titlePost);
        }
        public void setPostDes(String desPost){
            des = view.findViewById(R.id.des_tv);
            des.setText(desPost.length() > 20 ? desPost.substring(0,20)+"..." : desPost);
        }
        public void setdate(String date){
            Pdate = view.findViewById(R.id.date_tv);
            Pdate.setText(date);
        }
    }
}

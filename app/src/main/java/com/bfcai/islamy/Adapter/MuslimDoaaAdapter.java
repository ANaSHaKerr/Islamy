package com.bfcai.islamy.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfcai.islamy.Model.MuslimDoaa;
import com.bfcai.islamy.R;

import java.util.List;

public class MuslimDoaaAdapter extends RecyclerView.Adapter<MuslimDoaaAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<MuslimDoaa> doaa;

    public MuslimDoaaAdapter(Context ctx, List<MuslimDoaa> doaa){
        this.inflater=LayoutInflater.from(ctx);
        this.doaa=doaa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_api_content_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.doaaTitle.setText(doaa.get(position).getTitle());
        holder.doaaSubtitle.setText(doaa.get(position).getSubtitle());
        holder.doaaNum.setText(doaa.get(position).getNum());
        holder.shareContent.setImageDrawable(Drawable.createFromPath(doaa.get(position).getShare()));
        holder.copyContent.setImageDrawable(Drawable.createFromPath(doaa.get(position).getShare()));
        holder.listenContent.setImageDrawable(Drawable.createFromPath(doaa.get(position).getShare()));

    }

    @Override
    public int getItemCount() {
        return doaa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView doaaTitle,doaaSubtitle,doaaNum;
        ImageView shareContent , copyContent,listenContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doaaTitle=itemView.findViewById(R.id.doaaTitle);
            doaaSubtitle=itemView.findViewById(R.id.doaaSubtitle);
            doaaNum=itemView.findViewById(R.id.doaaNum);
            shareContent= itemView.findViewById(R.id.shareContent);
            copyContent= itemView.findViewById(R.id.copyContent);
            listenContent=itemView.findViewById(R.id.listenContent);

        }
    }
}
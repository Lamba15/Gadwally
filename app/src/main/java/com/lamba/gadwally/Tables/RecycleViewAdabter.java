package com.lamba.gadwally.Tables;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamba.gadwally.R;

import java.util.List;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class RecycleViewAdabter extends RecyclerView.Adapter<RecycleViewAdabter.MyViewHolder> {
    Context ctx;
    List<Model> models;

    public RecycleViewAdabter(Context ctx, List<Model> models) {
        this.ctx = ctx;
        this.models = models;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(ctx);
        view = inflater.inflate(R.layout.recyclerview_content, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.timer1.setText(models.get(position).getDattime1());
        holder.timer2.setText(models.get(position).getDattime2());
        holder.img.setImageResource(models.get(position).getImg());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ctx , Description.class);
//                intent.putExtra("Title" , models.get(position).getTitle());
//                intent.putExtra("Category" , models.get(position).getCategory());
//                intent.putExtra("Description" , models.get(position).getDescription());
//                intent.putExtra("Image" , models.get(position).getImage());
//                ctx.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, timer1, timer2;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.texttitle);
            timer1 = itemView.findViewById(R.id.dat_time1);
            timer2 = itemView.findViewById(R.id.dat_time2);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}

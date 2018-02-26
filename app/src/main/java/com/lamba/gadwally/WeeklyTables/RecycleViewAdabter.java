package com.lamba.gadwally.WeeklyTables;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamba.gadwally.DailyTable.DailyTable;
import com.lamba.gadwally.Models.ImageModel;
import com.lamba.gadwally.Models.TableInfo;
import com.lamba.gadwally.R;

import java.util.List;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class RecycleViewAdabter extends RecyclerView.Adapter<RecycleViewAdabter.MyViewHolder> {
    Context ctx;
    List<TableInfo> tableInfos;
    int lastposition = -1;

    public RecycleViewAdabter(Context ctx, List<TableInfo> tableInfos) {
        this.ctx = ctx;
        this.tableInfos = tableInfos;
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
        holder.title.setText(tableInfos.get(position).getTitle());
        holder.timer1.setText(tableInfos.get(position).getDattime1());
        holder.timer2.setText(tableInfos.get(position).getDattime2());
        holder.img.setImageResource(tableInfos.get(position).getImg());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
        if (position > lastposition) {

            Animation animation = AnimationUtils.loadAnimation(ctx,
                    R.anim.recycler_view_slide);
            animation.setStartOffset(100);

            holder.itemView.startAnimation(animation);
            lastposition = position;
        }
    }

    @Override
    public int getItemCount() {
        return tableInfos.size();
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

package com.lamba.gadwally.Tables;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamba.gadwally.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class Wednsedayfrag extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecycleViewAdabter recycleViewAdabter;
    List<Model> model;


    public Wednsedayfrag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weekday, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        RecycleViewAdabter adabter = new RecycleViewAdabter(getContext(), model);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adabter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ArrayList<>();

        model.add(new Model("Sleep", "00:00", "08:00", "this is My Time for go to bed", R.drawable.sleep));
        model.add(new Model("Work", "09:00", "15:00", "I Have Alot of Tasks Today", R.drawable.work));
        model.add(new Model("Study", "16:30", "18:30", "This is the Day for Economy", R.drawable.study));
        model.add(new Model("My love", "19:00", "20:30", "My Darling W8ting", R.drawable.mylove));
        model.add(new Model("Gym", "21:30", "23:30", "Help me My God", R.drawable.gym));


    }
}

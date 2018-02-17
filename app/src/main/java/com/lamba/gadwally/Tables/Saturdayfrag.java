package com.lamba.gadwally.Tables;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lamba.gadwally.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class Saturdayfrag extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecycleViewAdabter recycleViewAdabter;
    List<Model> model;
    Dialog alertDialog;
    FloatingActionButton fab;

    public Saturdayfrag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weekday, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        RecycleViewAdabter adabter = new RecycleViewAdabter(getContext(), model);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adabter);

        fab = (FloatingActionButton) view.findViewById(R.id.addnew);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View mview = getLayoutInflater().inflate(R.layout.dialog, null);

                ImageButton selectimage = (ImageButton) mview.findViewById(R.id.imageview);
                EditText title = (EditText) mview.findViewById(R.id.texttitle);
                final Button datestart = (Button) mview.findViewById(R.id.dat_time1);
                final Button dateend = (Button) mview.findViewById(R.id.dat_time2);
                Button done = (Button) mview.findViewById(R.id.done);


                dateend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        TimePickerDialog mDatePicker =
                                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                                        String am;

                                        if (hour >= 12){
                                            hour = hour - 12;
                                            am = " PM";
                                        }
                                        else {
                                            am = " AM";
                                        }

                                        dateend.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + am);
                                    }
                                },hour,minute,false);
                        mDatePicker.setTitle("Choose time");
                        mDatePicker.show();
                    }
                });


                datestart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        TimePickerDialog mDatePicker =
                        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                                String am;

                                if (hour >= 12){
                                    hour = hour - 12;
                                    am = " PM";
                                }
                                else {
                                    am = " AM";
                                }

                                datestart.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + am);
                            }
                        },hour,minute,false);
                        mDatePicker.setTitle("Choose time");
                        mDatePicker.show();
                    }
                });


                selectimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                alertDialog = new Dialog(getActivity());
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(mview);
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ArrayList<>();
        model.add(new Model("Sleep", "00:00", "08:00", "this is My Time for go to bed", R.drawable.sleep));
        model.add(new Model("Sleep", "00:00", "08:00", "this is My Time for go to bed", R.drawable.sleep));
        model.add(new Model("Work", "09:00", "15:00", "I Have Alot of Tasks Today", R.drawable.work));
        model.add(new Model("Study", "16:30", "18:30", "This is the Day for Economy", R.drawable.study));
        model.add(new Model("My love", "19:00", "20:30", "My Darling W8ting", R.drawable.mylove));
        model.add(new Model("Gym", "21:30", "23:30", "Help me My God", R.drawable.gym));


    }
}

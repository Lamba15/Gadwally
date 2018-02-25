package com.lamba.gadwally.WeeklyTables;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lamba.gadwally.Models.TableInfo;
import com.lamba.gadwally.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class Mondayfrag extends Fragment {
    View view;
    List<TableInfo> tableInfo;
    Dialog alertDialog;
    FloatingActionButton fab;

    public Mondayfrag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weekday, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        RecycleViewAdabter adabter = new RecycleViewAdabter(getContext(), tableInfo);
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
                final CircularProgressButton done = (CircularProgressButton) mview.findViewById(R.id.done);
                ImageButton cancel = (ImageButton) mview.findViewById(R.id.weekly_cancel);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

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

                                        if (hour >= 12) {
                                            hour = hour - 12;
                                            am = " PM";
                                        } else {
                                            am = " AM";
                                        }

                                        dateend.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + am);
                                    }
                                }, hour, minute, false);
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

                                        if (hour >= 12) {
                                            hour = hour - 12;
                                            am = " PM";
                                        } else {
                                            am = " AM";
                                        }

                                        datestart.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + am);
                                    }
                                }, hour, minute, false);
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
                        @SuppressLint("StaticFieldLeak") AsyncTask<String,String,String> submit = new AsyncTask<String, String, String>() {
                            @Override
                            protected String doInBackground(String... strings) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return "done";
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                if (s.equals("done")){
                                    Toast.makeText(getActivity(), "Need TO get it into Database", Toast.LENGTH_SHORT).show();
                                    done.doneLoadingAnimation(Color.parseColor("#263238"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_done_white_48dp));
                                }
                            }
                        };
                        done.startAnimation();
                        submit.execute();
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
        tableInfo = new ArrayList<>();
        tableInfo.add(new TableInfo("Sleep", "00:00", "08:00",null, "this is My Time for go to bed", R.drawable.sleep));
        tableInfo.add(new TableInfo("Work", "09:00", "15:00",null, "I Have Alot of Tasks Today", R.drawable.work));
        tableInfo.add(new TableInfo("Study", "16:30", "18:30",null, "This is the Day for Economy", R.drawable.study));
        tableInfo.add(new TableInfo("My love", "19:00", "20:30",null, "My Darling W8ting", R.drawable.mylove));
        tableInfo.add(new TableInfo("Gym", "21:30", "23:30",null, "Help me My God", R.drawable.gym));
    }
}

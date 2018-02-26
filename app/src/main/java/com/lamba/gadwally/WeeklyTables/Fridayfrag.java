package com.lamba.gadwally.WeeklyTables;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lamba.gadwally.DailyTable.DailyTable;
import com.lamba.gadwally.Models.ImageModel;
import com.lamba.gadwally.Models.TableInfo;
import com.lamba.gadwally.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class Fridayfrag extends Fragment {
    View view;
    List<TableInfo> tableInfo;
    Dialog alertDialog , imagedialog;
    FloatingActionButton fab;
    ImageButton imgpick , pickercancel ,  imagecancel;
    EditText editText , searchtext;
    List<ImageModel> modelimage;
    RecyclerView recyclerView , recyclerViewpicker;
    RecycleViewImageAdabter pickeradabter;
    public class RecycleViewImageAdabter extends RecyclerView.Adapter<RecycleViewImageAdabter.MyViewHolder> {
        Context ctx;
        List<ImageModel> models;

        public RecycleViewImageAdabter(Context ctx, List<ImageModel> models) {
            this.ctx = ctx;
            this.models = models;
        }

        @Override
        public RecycleViewImageAdabter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.imagepicker_recycler_content, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecycleViewImageAdabter.MyViewHolder holder, final int position) {
            holder.img.setImageResource(models.get(position).getImg());
            holder.title.setText(models.get(position).getTitle());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgpick.setImageResource(models.get(position).getImg());
                    editText.setText(models.get(position).getTitle());
                    imagedialog.dismiss();

                }
            });
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        //for Filter List in Bottom of codes .
        public void filterlist(List<ImageModel> filteredlist) {
            models = filteredlist;
            notifyDataSetChanged();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView title;
            CardView cardView;

            public MyViewHolder(View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.image_choose);
                title = itemView.findViewById(R.id.title_choose);
                cardView = itemView.findViewById(R.id.card_content_picker);
            }
        }
    }
    public Fridayfrag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weekday, container, false);

        recyclerView = view.findViewById(R.id.recycleview);
        RecycleViewAdabter adabter = new RecycleViewAdabter(getContext(), tableInfo);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adabter);

        fab = (FloatingActionButton) view.findViewById(R.id.addnew);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View mview = getLayoutInflater().inflate(R.layout.dialog, null);

                imgpick = mview.findViewById(R.id.weekly_image_picker);
                editText = mview.findViewById(R.id.texttitle);

                imgpick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View nview = getLayoutInflater().inflate(R.layout.imagepicker_content,null);
                        searchtext = nview.findViewById(R.id.search_bar_image_picker);
                        searchtext.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                filter(s.toString());

                            }
                        });


                        recyclerViewpicker = nview.findViewById(R.id.recycler_image_picker);
                        modelimage = new ArrayList<>();
                        modelimage.add(new ImageModel(R.drawable.work, "Work"));
                        modelimage.add(new ImageModel(R.drawable.weekly1, "Weekly 1"));
                        modelimage.add(new ImageModel(R.drawable.study, "Study"));
                        modelimage.add(new ImageModel(R.drawable.sleep, "Sleep"));
                        modelimage.add(new ImageModel(R.drawable.mylove, "My Love"));
                        modelimage.add(new ImageModel(R.drawable.gym, "Gym"));
                        modelimage.add(new ImageModel(R.drawable.work, "Work"));
                        modelimage.add(new ImageModel(R.drawable.weekly1, "Weekly 1"));
                        modelimage.add(new ImageModel(R.drawable.study, "Study"));
                        modelimage.add(new ImageModel(R.drawable.sleep, "Sleep"));
                        modelimage.add(new ImageModel(R.drawable.mylove, "My Love"));
                        modelimage.add(new ImageModel(R.drawable.gym, "Gym"));
                        pickeradabter = new RecycleViewImageAdabter(getContext(), modelimage);
                        recyclerViewpicker.setAdapter(pickeradabter);
                        recyclerViewpicker.setLayoutManager(new GridLayoutManager(getActivity(), 3));


                        imagecancel = nview.findViewById(R.id.close_picker);
                        imagecancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                imagedialog.dismiss();
                            }
                        });
                        imagedialog = new Dialog(getActivity());
                        imagedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        imagedialog.setContentView(nview);
                        imagedialog.setCancelable(false);
                        imagedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        imagedialog.show();
                    }
                });


                 pickercancel = mview.findViewById(R.id.weekly_cancel);
                pickercancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


                final CircularProgressButton done = mview.findViewById(R.id.done);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        @SuppressLint("StaticFieldLeak") AsyncTask<String, String, String> submit = new AsyncTask<String, String, String>() {
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
                                if (s.equals("done")) {
                                    Toast.makeText(getActivity(), "Need TO get it into Database", Toast.LENGTH_SHORT).show();
                                    done.doneLoadingAnimation(Color.parseColor("#263238"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                                }
                            }
                        };
                        done.startAnimation();
                        submit.execute();
                    }
                });


                final Button datestart = mview.findViewById(R.id.dat_time1);
                final Button dateend = mview.findViewById(R.id.dat_time2);
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
        tableInfo.add(new TableInfo("Sleep", "00:00", "08:00", null, "this is My Time for go to bed", R.drawable.sleep));
        tableInfo.add(new TableInfo("Work", "09:00", "15:00", null, "I Have Alot of Tasks Today", R.drawable.work));
        tableInfo.add(new TableInfo("Study", "16:30", "18:30", null, "This is the Day for Economy", R.drawable.study));
        tableInfo.add(new TableInfo("My love", "19:00", "20:30", null, "My Darling W8ting", R.drawable.mylove));
        tableInfo.add(new TableInfo("Gym", "21:30", "23:30", null, "Help me My God", R.drawable.gym));
    }
    private void filter(String text) {
        List<ImageModel> imagetitles = new ArrayList<>();
        for (ImageModel item : modelimage) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                imagetitles.add(item);
            }
        }
        pickeradabter.filterlist(imagetitles);
    }
}

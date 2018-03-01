package com.lamba.gadwally.DailyTable;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.lamba.gadwally.Models.ImageModel;
import com.lamba.gadwally.Models.TableInfo;
import com.lamba.gadwally.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class DailyTable extends AppCompatActivity {
    Dialog imagedialog , alertDialog;
    TextView tv_daily;
    ImageButton imgpick , pickercancel , imagecancel;
    EditText editText , searchtext;
    FloatingActionButton fab;
    List<ImageModel> modelimage;
    RecycleViewImageAdabter pickeradabter;
    RecyclerView recyclerView , recyclerViewpicker;


    public class RecycleViewAdabter extends RecyclerView.Adapter<RecycleViewAdabter.MyViewHolder> {
        Context ctx;
        List<TableInfo> dailycontent;
        int lastposition = -1;

        public RecycleViewAdabter(Context ctx, List<TableInfo> dailycontent) {
            this.ctx = ctx;
            this.dailycontent = dailycontent;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.daily_recyclerview_content, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.title.setText(dailycontent.get(position).getTitle());
            holder.img.setImageResource(dailycontent.get(position).getImg());
            holder.dattime3.setText(dailycontent.get(position).getDattime1());
            holder.dattime4.setText(dailycontent.get(position).getDattime2());
            holder.datdate1.setText(dailycontent.get(position).getDatdate1());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

            if (position > lastposition) {

                Animation animation = AnimationUtils.loadAnimation(ctx,
                        R.anim.recycler_view_animation);
                animation.setStartOffset(150);
                holder.itemView.startAnimation(animation);
                lastposition = position;
            }
        }

        @Override
        public int getItemCount() {
            return dailycontent.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView title, dattime3, dattime4, datdate1;
            CardView cardView;

            public MyViewHolder(View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.imageview);
                title = itemView.findViewById(R.id.texttitle);
                dattime3 = itemView.findViewById(R.id.dat_time3);
                dattime4 = itemView.findViewById(R.id.dat_time4);
                datdate1 = itemView.findViewById(R.id.dat_date1);
                cardView = itemView.findViewById(R.id.cardview);
            }
        }


    }



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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_table);

        Toolbar toolbar = findViewById(R.id.daily_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_daily = findViewById(R.id.toolbar_title_daily);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/bauhaus93.ttf");
        tv_daily.setTypeface(face);

        //Daily RecyclerView and Array
        List<TableInfo> content = new ArrayList<>();
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.sleep));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.work));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.study));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.sleep));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.work));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.study));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.sleep));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.work));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.study));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.sleep));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.work));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.study));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.sleep));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.work));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.study));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.sleep));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.work));
        content.add(new TableInfo("Sleep", "00:00", "00:00", "12:12:2025", null, R.drawable.study));

        // Adapter and Layout Manager
        recyclerView = findViewById(R.id.daily_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RecycleViewAdabter(getApplicationContext(), content));

        fab = (FloatingActionButton) findViewById(R.id.addnew_daily);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog = new Dialog(DailyTable.this);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.daily_dialog);
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();


                imagecancel = alertDialog.findViewById(R.id.cancel_icon);
                imgpick = alertDialog.findViewById(R.id.image_picker2);
                editText = alertDialog.findViewById(R.id.text_title2);
                imagedialog = new Dialog(DailyTable.this);

                imgpick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imagedialog.setContentView(R.layout.imagepicker_content);
                        searchtext = imagedialog.findViewById(R.id.search_bar_image_picker);
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


                        recyclerViewpicker = imagedialog.findViewById(R.id.recycler_image_picker);
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
                        pickeradabter = new RecycleViewImageAdabter(getApplicationContext(), modelimage);
                        recyclerViewpicker.setAdapter(pickeradabter);
                        recyclerViewpicker.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));


                        pickercancel = imagedialog.findViewById(R.id.close_picker);

                        pickercancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                imagedialog.dismiss();
                            }
                        });
                        imagedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        imagedialog.show();
                    }
                });

                final CircularProgressButton done = alertDialog.findViewById(R.id.done2);
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
                                    Toast.makeText(DailyTable.this, "Need TO get it into Database", Toast.LENGTH_SHORT).show();
                                    done.doneLoadingAnimation(Color.parseColor("#263238"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                                }
                            }
                        };
                        done.startAnimation();
                        submit.execute();}
                });


                final Button datstart2 = alertDialog.findViewById(R.id.dat_time3);
                final Button datend2 = alertDialog.findViewById(R.id.dat_time4);
                final Button dailydate = alertDialog.findViewById(R.id.dat_date1);

                datstart2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker =
                                new TimePickerDialog(DailyTable.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                                        String am;

                                        if (hour >= 12) {
                                            hour = hour - 12;
                                            am = " PM";
                                        } else {
                                            am = " AM";
                                        }

                                        datstart2.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + am);
                                    }
                                }, hour, minute, false);
                        mTimePicker.setTitle("Choose Start Time");
                        mTimePicker.show();

                    }
                });


                datend2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker =
                                new TimePickerDialog(DailyTable.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                                        String am;

                                        if (hour >= 12) {
                                            hour = hour - 12;
                                            am = " PM";
                                        } else {
                                            am = " AM";
                                        }

                                        datend2.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + am);
                                    }
                                }, hour, minute, false);
                        mTimePicker.setTitle("Choose End Time");

                        mTimePicker.show();

                    }
                });
                dailydate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();

                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog mDatepicker = new DatePickerDialog(DailyTable.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dailydate.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                            }

                        }, day, month, year);
                        mDatepicker.setTitle("Choose Date");
                        mDatepicker.updateDate(2018, 1, 1);
                        mDatepicker.show();
                    }
                });
                imagecancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


            }
        });
    }

    // This is Search Bar Filter for get what you want for Avatar.
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
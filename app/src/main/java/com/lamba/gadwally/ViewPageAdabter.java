package com.lamba.gadwally;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ViewPageAdabter extends PagerAdapter {
     Context context;
     LayoutInflater inflater;
    // List for Images.
     int[] images = {R.drawable.weekly1, R.drawable.calendar512, R.drawable.contacts0circle};
    //List for Titles.
     String[] titles = {"Weekly Table", "Daily Table", "Contacts"};
    //List for Descriptions.
     String[] descriptions = {"You Sure Need To Organize Your Weekly Works Don't Forget TO System it."
            , "Sudden Events and Works Every Day ... More Jobs Sure Needs to System."
            , "Your Mobile Stolen or Formatted  , Don't Worry Your All Contacts in Safe."};
    //List for Background Colors.
     int[] backgroundcolors = {Color.argb(154,236,222,143),
            Color.argb(154,236,166,143),
            Color.argb(154,143,169,236)};
    //List for textcolors.
     int[] textcolors = {Color.rgb(0,40,172),
            Color.rgb(2,97,6),
            Color.rgb(140,9,2)};

    public ViewPageAdabter(Context context) {
        this.context = context;

    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return (view == (LinearLayout) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.slider_view);
        ImageView img = view.findViewById(R.id.func_img);
        TextView title = view.findViewById(R.id.func_text);
        TextView desci = view.findViewById(R.id.func_desc);
        Typeface face = Typeface.createFromAsset(context.getAssets(),"fonts/bauhaus93.ttf");

        linearLayout.setBackgroundColor(backgroundcolors[position]);

        img.setImageResource(images[position]);

        title.setText(titles[position]);
        title.setTextColor(textcolors[position]);
        title.setTypeface(face);

        desci.setText(descriptions[position]);
        desci.setTextColor(textcolors[position]);
        desci.setTypeface(face);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

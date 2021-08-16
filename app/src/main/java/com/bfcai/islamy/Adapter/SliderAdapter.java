package com.bfcai.islamy.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bfcai.islamy.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slideImages = {
            R.drawable.ic_contract,
            R.drawable.ic_pray,
            R.drawable.ic_quran,
    };

    public String[] slideHeadings ={
            "Note",
            "Graces",
            "Quran"
    };

    public String[] slideDescriptions ={
            "Welcome to your diary",
            "Write your daily memoirs before going to sleep about the Graces you thank your Lord for,as He said in His Noble Quran",
            "(Al-Duha) -11- And proclaim the Grace of your Lord",
    };


    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

        ImageView slideImageView =  view.findViewById(R.id.slider_iv);
        TextView slideHeading =  view.findViewById(R.id.slider_tv_heading);
        TextView slideDescription =  view.findViewById(R.id.slider_tv_description);

        slideImageView.setImageResource(slideImages[position]);
        slideHeading.setText(slideHeadings[position]);
        slideDescription.setText(slideDescriptions[position]);

        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);  //todo: RelativeLayout??
    }
}
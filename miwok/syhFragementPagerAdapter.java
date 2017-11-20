package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/18.
 */

public class syhFragementPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[]=new String[]{"数字","颜色"};
    private int[] imageResId={R.drawable.ic_play_arrow_white_36dp, R.drawable.ic_play_arrow_white_36dp};
    private Context context;
    public syhFragementPagerAdapter(FragmentManager fm,Context ct) {
        super(fm);
        context=ct;
    }

    @Override
    public int getCount() {
        return 12;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        //return super.getPageTitle(position);
//        return tabTitles[position];
//    }

    public View getTabView(int position){
        View v= LayoutInflater.from(context).inflate(R.layout.tablayout,null);
        TextView tv=(TextView)v.findViewById(R.id.tabtext);
        tv.setText(tabTitles[0]+position);
        ImageView img=(ImageView)v.findViewById(R.id.iconimg);
        img.setImageResource(imageResId[0]);
        return v;
    }

    @Override
    public Fragment getItem(int position) {
        if (position%2== 0) {
            return new NumbersFragment();
        } else {
            return new ColorFragment();
        }
    }
}

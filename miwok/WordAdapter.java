package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent ,false);
        }

        Word item= getItem(position);

        TextView nametv=(TextView)listItemView.findViewById(R.id.default_text_view);

        nametv.setText(item.getDefaultTranslation());

        TextView nametr=(TextView)listItemView.findViewById(R.id.niwok_text_view);

        nametr.setText(item.getmMiwokTranslation());

        ImageView imv=(ImageView)listItemView.findViewById(R.id.longliu);
        if (item.hasImage()) {
            //显示指定的图片
            imv.setImageResource(item.getImgpath());
            imv.setVisibility(View.VISIBLE);
        }else{
            //隐藏该图片
            imv.setVisibility(View.GONE);
        }

        return listItemView;
        //return super.getView(position, convertView, parent);
    }

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context,0,  objects);
    }
}

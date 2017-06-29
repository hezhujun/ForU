package com.example.king.foru.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.king.foru.R;

import com.example.king.foru.model.ProfileItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by King on 2017/6/29.
 */

public class ProfileAdapeter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<ProfileItem> datas;
    private ImageView imageView;
    private TextView textView;
    private Context context;

    public ProfileAdapeter(Context context,List<ProfileItem> datas){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = mInflater.inflate(R.layout.profile_lv_item,null);
        }

        imageView = (ImageView) view.findViewById(R.id.profile_item_iv);
        textView = (TextView) view.findViewById(R.id.profile_item_tv);

        Picasso.with(context).load(datas.get(i).getImgId()).into(imageView);
        textView.setText(datas.get(i).getText());
        return view;
    }
}

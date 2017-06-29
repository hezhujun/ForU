package com.example.king.foru.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.king.foru.R;
import com.example.king.foru.model.ProfileItem;
import com.example.king.foru.view.adapter.ProfileAdapeter;
import com.wingjay.blurimageviewlib.FastBlurUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_listview1)
    ListView listView1;

    @BindView(R.id.profile_listview2)
    ListView listView2;


    List<ProfileItem> datas1;
    List<ProfileItem> datas2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,getActivity());

        datas1 = new ArrayList<>();
        datas2 = new ArrayList<>();

        datas1.add(new ProfileItem(R.drawable.profile_detail, "我的资产"));
        datas1.add(new ProfileItem(R.drawable.profile_sys_mes, "评价"));

        datas2.add(new ProfileItem(R.drawable.profile_feedback, "产品意见"));
        datas2.add(new ProfileItem(R.drawable.profile_version, "版本检测"));
        datas2.add(new ProfileItem(R.drawable.profile_about, "关于"));

        listView1.setAdapter(new ProfileAdapeter(getContext(),datas1));
        listView2.setAdapter(new ProfileAdapeter(getContext(),datas2));

        setListViewHeight(listView1);
        setListViewHeight(listView2);
    }

    public void setListViewHeight(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}

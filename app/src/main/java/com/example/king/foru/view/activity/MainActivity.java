package com.example.king.foru.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.king.foru.R;
import com.example.king.foru.presenter.ipresenter.IMainPresenter;
import com.example.king.foru.presenter.presenterimpl.MainPresenterImpl;
import com.example.king.foru.view.adapter.MainViewPagerAdapter;
import com.example.king.foru.view.fragment.ForhelpFragment;
import com.example.king.foru.view.fragment.ProfileFragment;
import com.example.king.foru.view.fragment.TalkFragment;
import com.example.king.foru.view.fragment.TaskFragment;
import com.example.king.foru.view.fragment.TaskInformationFragment;
import com.example.king.foru.view.iview.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView,View.OnClickListener{

    @BindViews({R.id.main_linearlayout_imageview_task, R.id.main_linearlayout_imageview_information
            , R.id.main_linearlayout_imageview_forhelp, R.id.main_linearlayout_imageview_message
            , R.id.main_linearlayout_imageview_profile})
    List<ImageView> imgs;
    @BindViews({R.id.main_task,R.id.main_information,R.id.main_forhelp
    ,R.id.main_message,R.id.main_profile})
    List<LinearLayout> linears;
    @BindViews({R.id.main_linearlayout_textview_task,R.id.main_linearlayout_imageview_information
    ,R.id.main_linearlayout_textview_forhelp,R.id.main_linearlayout_textview_message
    ,R.id.main_linearlayout_textview_profile})
    List<TextView> txts;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolBarTitle;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;

    List<Integer> imgsResourceGreen = new ArrayList<>();
    List<Integer> imgsResourceGray = new ArrayList<>();
    IMainPresenter mainPresenter;
    private String[] titles = {"求助任务","个人任务","发布求助","私信情况","个人中心"};
    private int index;
    private List<Fragment> fragmentLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    @Override
    public void onChangeColor(View v) {
        resetColor();
        index = linears.indexOf(v);
        txts.get(index).setTextColor(getResources().getColor(R.color.colorPrimary));
        imgs.get(index).setImageResource(imgsResourceGreen.get(index));
    }

    @Override
    public void onChangePage() {
        viewPager.setCurrentItem(index);
    }

    @Override
    public void onClick(View view) {
        mainPresenter.changeColor(view);
        toolBarTitle.setText(titles[index]);
        mainPresenter.changePage();
    }

    private void initData(){
        fragmentLists.add(new TaskFragment());
        fragmentLists.add(new TaskInformationFragment());
        fragmentLists.add(new ForhelpFragment());
        fragmentLists.add(new TalkFragment());
        fragmentLists.add(new ProfileFragment());

        ButterKnife.bind(this);

        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),fragmentLists));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetColor();
                txts.get(position).setTextColor(getResources().getColor(R.color.colorPrimary));
                imgs.get(position).setImageResource(imgsResourceGreen.get(position));
                toolBarTitle.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        for(LinearLayout linear:linears){
            linear.setOnClickListener(this);
        }

        mainPresenter = new MainPresenterImpl(this);

        imgsResourceGreen.add(R.drawable.order_green);
        imgsResourceGreen.add(R.drawable.calender_green);
        imgsResourceGreen.add(R.drawable.add);
        imgsResourceGreen.add(R.drawable.email);
        imgsResourceGreen.add(R.drawable.profile_green);

        imgsResourceGray.add(R.drawable.order_gray);
        imgsResourceGray.add(R.drawable.calender_gray);
        imgsResourceGray.add(R.drawable.add);
        imgsResourceGray.add(R.drawable.email);
        imgsResourceGray.add(R.drawable.profile_gray);

    }

    private void resetColor(){

        for (int i = 0; i < imgs.size(); i++) {
            imgs.get(i).setImageResource(imgsResourceGray.get(i));
            txts.get(i).setTextColor(getResources().getColor(R.color.colorMainTxt));
        }
    }


}

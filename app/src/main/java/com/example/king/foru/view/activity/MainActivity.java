package com.example.king.foru.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.king.foru.R;
import com.example.king.foru.view.fragment.ForhelpFragment;
import com.example.king.foru.view.fragment.ProfileFragment;
import com.example.king.foru.view.fragment.TalkFragment;
import com.example.king.foru.view.fragment.TaskFragment;
import com.example.king.foru.view.fragment.TaskInformationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindViews({R.id.main_linearlayout_imageview_task, R.id.main_linearlayout_imageview_information
            , R.id.main_linearlayout_imageview_forhelp, R.id.main_linearlayout_imageview_message
            , R.id.main_linearlayout_imageview_profile})
    List<ImageView> imgs;

    @BindViews({R.id.main_task,R.id.main_information,R.id.main_forhelp
    ,R.id.main_message,R.id.main_profile})
    List<LinearLayout> linears;

    @BindViews({R.id.main_linearlayout_textview_task,R.id.main_linearlayout_textview_information
    ,R.id.main_linearlayout_textview_forhelp,R.id.main_linearlayout_textview_message
    ,R.id.main_linearlayout_textview_profile})
    List<TextView> txts;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolBarTitle;

    List<Integer> imgsResourceGreen = new ArrayList<>();
    List<Integer> imgsResourceGray = new ArrayList<>();
    private String[] titles = {"求助任务","个人任务","发布求助","私信情况","个人中心"};
    private int index = 0;
    private FragmentManager fragmentManager;
    private Fragment taskFragment;
    private Fragment taskInformationFragment;
    private Fragment forhelpFragment;
    private Fragment talkFragment;
    private Fragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }


    public void ChangeColor(View v) {
        resetColor();
        index = linears.indexOf(v);
        txts.get(index).setTextColor(getResources().getColor(R.color.colorPrimary));
        imgs.get(index).setImageResource(imgsResourceGreen.get(index));
    }

    @Override
    public void onClick(View view) {
        ChangeColor(view);
        toolBarTitle.setText(titles[index]);
        setTabSelection();
    }

    private void initData(){

        ButterKnife.bind(this);

        for(LinearLayout linear:linears){
            linear.setOnClickListener(this);
        }

        fragmentManager = getSupportFragmentManager();

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

        setTabSelection();

    }



    private void resetColor(){

        for (int i = 0; i < imgs.size(); i++) {
            imgs.get(i).setImageResource(imgsResourceGray.get(i));
            txts.get(i).setTextColor(getResources().getColor(R.color.colorMainTxt));
        }
    }

    private void hideFragments(FragmentTransaction transaction){
        if(taskFragment != null){
            transaction.hide(taskFragment);
        }
        if(taskInformationFragment != null){
            transaction.hide(taskInformationFragment);
        }
        if(forhelpFragment != null){
            transaction.hide(forhelpFragment);
        }
        if(talkFragment != null){
            transaction.hide(talkFragment);
        }
        if(profileFragment != null){
            transaction.hide(profileFragment);
        }
    }

    private void setTabSelection(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        switch (index){
            case 0:
                if (taskFragment == null){
                    taskFragment = new TaskFragment();
                    transaction.add(R.id.content,taskFragment);
                }
                else{
                    transaction.show(taskFragment);
                }
                break;
            case 1:
                if (taskInformationFragment == null){
                    taskInformationFragment = new TaskInformationFragment();
                    transaction.add(R.id.content,taskInformationFragment);
                }
                else{
                    transaction.show(taskInformationFragment);
                }
                break;
            case 2:
                if (forhelpFragment == null){
                    forhelpFragment = new ForhelpFragment();
                    transaction.add(R.id.content,forhelpFragment);
                }
                else{
                    transaction.show(forhelpFragment);
                }
                break;
            case 3:
                if (talkFragment == null){
                    talkFragment = new TalkFragment();
                    transaction.add(R.id.content,talkFragment);
                }
                else{
                    transaction.show(talkFragment);
                }
                break;
            case 4:
                if (profileFragment == null){
                    profileFragment = new ProfileFragment();
                    transaction.add(R.id.content,profileFragment);
                }
                else{
                    transaction.show(profileFragment);
                }
                break;
        }

        transaction.commit();

    }


}

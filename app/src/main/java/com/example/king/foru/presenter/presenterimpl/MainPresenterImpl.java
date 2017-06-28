package com.example.king.foru.presenter.presenterimpl;

import android.view.View;

import com.example.king.foru.presenter.ipresenter.IMainPresenter;
import com.example.king.foru.view.iview.IMainView;

/**
 * Created by King on 2017/6/28.
 */

public class MainPresenterImpl implements IMainPresenter{
    IMainView iMainView;

    public MainPresenterImpl(IMainView iMainView){
        super();
        this.iMainView = iMainView;
    }

    @Override
    public void changeColor(View v) {
        iMainView.onChangeColor(v);
    }

    @Override
    public void changePage() {
        iMainView.onChangePage();
    }
}

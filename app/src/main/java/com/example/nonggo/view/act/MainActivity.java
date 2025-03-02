package com.example.nonggo.view.act;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.example.nonggo.R;
 
import com.example.nonggo.base.BaseMvpActivity;
import com.example.nonggo.base.BasePresenter;
import com.example.nonggo.contract.LoginContract;
import com.example.nonggo.model.MainModel;
import com.example.nonggo.presenter.MainPresenter;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.operators.single.SingleDelay;
import io.reactivex.schedulers.Schedulers;


/**
 * @Author Kotlin MVP Plugin
 * @Date 2025/03/01
 * @Description input description
 **/
public class MainActivity extends BaseMvpActivity<LoginContract.View, MainPresenter> implements LoginContract.View {


    private View lunch;

    @Override
    protected void initData() {
        Utils.init(getApplication());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
     //显示开机页
       lunch = findView(R.id.inc_merge_lunch);
       lunchDelayedTask();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(new MainModel());
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFail() {

    }

    @SuppressLint("CheckResult")
    private void lunchDelayedTask() {
        // 使用 Completable 实现延时任务
         Completable.timer(3, java.util.concurrent.TimeUnit.SECONDS) // 延时 3 秒
                .subscribeOn(Schedulers.io()) // 在 IO 线程执行延时
                .observeOn(AndroidSchedulers.mainThread()) // 切换到主线程执行 UI 操作
                .subscribe(() -> {
                    lunch.setVisibility(View.GONE);
                }, throwable -> {


                });
    }
}

package com.example.nonggo.presenter;

import com.example.nonggo.base.BasePresenter;
import com.example.nonggo.contract.LoginContract;
import com.example.nonggo.model.MainModel;


import org.jetbrains.annotations.NotNull;



/**
 * @Author Kotlin MVP Plugin
 * @Date 2025/03/01
 * @Description input description
 **/
public class MainPresenter extends  BasePresenter<LoginContract.View>{
    private final MainModel loginModel;

    public MainPresenter(MainModel loginModel) {
        this.loginModel = loginModel;
    }
}

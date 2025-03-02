package com.example.nonggo.base;

// View层接口基类
public interface IBaseView {
    void showLoading();
    void hideLoading();
    void onError(String message);
}

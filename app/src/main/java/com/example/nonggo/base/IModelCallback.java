package com.example.nonggo.base;

public interface IModelCallback<T> {
    void onSuccess(T data);
    void onFailure(String error);
}

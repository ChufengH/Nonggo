package com.example.nonggo.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView> {
    private WeakReference<V> viewRef;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    protected V getView() {
        return viewRef != null ? viewRef.get() : null;
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void destroy() {
        compositeDisposable.clear();
    }

    protected boolean isViewAttached() {
        return getView() != null;
    }
}


package com.example.nonggo.base;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseModel {


    protected <T> void executeRequest(
            Observable<T> observable,
            IModelCallback<T> callback) {

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(T t) {
                        if (callback != null) {
                            callback.onSuccess(t);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}

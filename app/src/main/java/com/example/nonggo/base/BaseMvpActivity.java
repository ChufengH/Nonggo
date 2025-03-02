package com.example.nonggo.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// Activity基类封装
public abstract class BaseMvpActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends AppCompatActivity implements IBaseView {

   protected P presenter;
   private final String TAG = "BaseMvpActivity";
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      // 布局自动加载封装
      setContentView(getLayoutId());
      // 初始化视图
      initView(savedInstanceState);
      //初始化监听器
      initListener();
      // 创建Presenter
      presenter = createPresenter();
      if (presenter != null) {
         presenter.attachView((V) this);
      }
      // 初始化数据
      initData();
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      if (presenter != null) {
         presenter.detachView();
         presenter.destroy();

      }
   }


   protected abstract void initData() ;
   protected abstract void initView(Bundle savedInstanceState) ;
   protected abstract void initListener() ;
   protected abstract int getLayoutId();
   protected abstract P createPresenter();

   @Override
   public void showLoading() {
      // 实现通用加载动画
   }

   @Override
   public void hideLoading() {
      // 隐藏加载动画
   }

   @Override
   public void onError(String message) {
      Log.e(TAG,"onError:"+message);
   }

   // 封装findViewById
   protected <T extends View> T findView(@IdRes int id) {
      return findViewById(id);
   }
}
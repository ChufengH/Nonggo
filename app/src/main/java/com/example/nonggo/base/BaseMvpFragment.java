package com.example.nonggo.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseMvpFragment<V extends IBaseView, P extends BasePresenter<V>>
        extends Fragment implements IBaseView {

    protected P presenter;
    private boolean isViewCreated = false;    // View是否创建完成
    private boolean isVisibleToUser = false;  // 是否对用户可见
    private boolean isFirstLoad = true;       // 是否是第一次加载
    private final String TAG ="BaseMvpFragment" ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        isViewCreated = true;
        checkLazyLoad();
    }

    // 实现懒加载关键
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        checkLazyLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isFirstLoad = true;
        if (presenter != null) {
            presenter.detachView();
            presenter.destroy();
        }
    }

    /**
     * 检查是否需要懒加载
     */
    private void checkLazyLoad() {
        if (isViewCreated && isVisibleToUser) {
            if (isFirstLoad) {
                onLazyLoad(true);
                isFirstLoad = false;
            } else {
                onLazyLoad(false);
            }
        }
    }

    /**
     * 是否需要每次可见都加载数据（默认仅首次加载）
     */
    protected boolean needReloadOnVisible() {
        return false;
    }

    /**
     * 懒加载方法（核心）
     * @param isFirstLoad 是否首次加载
     */
    protected void onLazyLoad(boolean isFirstLoad) {
        // 子类按需实现
    }

    // 抽象方法
    protected abstract int getLayoutId();
    protected abstract P createPresenter();
    protected abstract void initView(View rootView);

    // 以下实现IBaseView接口
    @Override
    public void showLoading() {
        // 实现加载动画
    }

    @Override
    public void hideLoading() {
        // 隐藏加载动画
    }

    @Override
    public void onError(String message) {
        Log.e(TAG,"onError:"+message);
    }
}

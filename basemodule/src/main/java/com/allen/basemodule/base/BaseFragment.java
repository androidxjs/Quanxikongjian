package com.allen.basemodule.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.basemodule.base.factory.PresenterFactory;
import com.allen.basemodule.base.proxy.PresenterProxy;
import com.allen.basemodule.config.Constants;
import com.allen.basemodule.widget.stateview.StateView;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by xjs on 2019/1/17.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseViewInterface {
    private PresenterProxy proxy = new PresenterProxy(PresenterFactory.<V, P>createFactory(getClass()));
    private StateView mStateView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStateView = StateView.inject(injectTarget());
        ImmersionBar.with(this).init();
        if (savedInstanceState != null) {
            proxy.onRestoreInstanceState(savedInstanceState.getBundle(Constants.PRESENTER_SAVE_KEY));
        }
        proxy.onCreate((V) this);
        view = inflater.inflate(getLayoutId(), null);
        initView();
        return view;
    }

    protected abstract View injectTarget();

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
        proxy.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
        ImmersionBar.with(this).init();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constants.PRESENTER_SAVE_KEY, proxy.onSaveInstanceState());
    }

}

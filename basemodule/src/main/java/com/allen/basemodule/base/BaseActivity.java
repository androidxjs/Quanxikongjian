package com.allen.basemodule.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.allen.basemodule.base.factory.PresenterFactory;
import com.allen.basemodule.base.proxy.PresenterProxy;
import com.allen.basemodule.config.Constants;
import com.allen.basemodule.widget.stateview.StateView;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by xjs on 2019/1/17.
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseViewInterface {
    private PresenterProxy proxy = new PresenterProxy(PresenterFactory.<V, P>createFactory(getClass()));
    private StateView mStateView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 所有子类都将继承这些相同的属性,请在设置界面之后设置
        setContentView(getLayoutId());
        mStateView = StateView.inject(injectTarget());
        ImmersionBar.with(this).init();
        if (savedInstanceState != null) {
            proxy.onRestoreInstanceState(savedInstanceState.getBundle(Constants.PRESENTER_SAVE_KEY));
        }
        proxy.onCreate((V) this);
        initView();
    }

    protected abstract View injectTarget();

    @Override
    protected void onDestroy() {
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(Constants.PRESENTER_SAVE_KEY, proxy.onSaveInstanceState());
    }

}

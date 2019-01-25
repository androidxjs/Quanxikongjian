package com.allen.basemodule.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.basemodule.commen.LoginInterceptor;
import com.allen.basemodule.config.Constants;


/**
 * Created by xjs on 2019/1/22.
 * 路由工具类
 */
public class RouterUtil {
    /**
     * 通过url跳转页面
     *
     * @param url
     */
    public static void goToActivity(String url) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IS_LOGIN, SharePreferenceUtil.getBoolean(Constants.IS_LOGIN, false));
        ARouter.getInstance()
                .build(url).with(bundle)
                .setProvider(new LoginInterceptor())
                .navigation();
    }

    /**
     * 通过url跳转页面，转场动画(API16+)
     *
     * @param url
     */
    public static void goToActivity(View view, String url) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ARouter.getInstance()
                .build(url)
                .withOptionsCompat(compat)
                .setProvider(new LoginInterceptor())
                .navigation();
    }

    /**
     * 通过url跳转页面，共享元素,转场动画(API16+)
     *
     * @param url
     */
    public static void goToActivity(Activity activity, View view, String url, String sharedName) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, sharedName);
        ARouter.getInstance()
                .build(url)
                .withOptionsCompat(compat)
                .setProvider(new LoginInterceptor())
                .navigation(activity);
    }

    /**
     * 通过url,bundle跳转页面
     *
     * @param url
     * @param bundle
     */
    public static void goToActivity(String url, Bundle bundle) {
        bundle.putBoolean(Constants.IS_LOGIN, SharePreferenceUtil.getBoolean(Constants.IS_LOGIN, false));
        ARouter.getInstance()
                .build(url).with(bundle)
                .setProvider(new LoginInterceptor())
                .navigation();
    }

    /**
     * startActivityForResult
     *
     * @param
     */
    public static void goToActivity(String url, Activity activity, int requestcode) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IS_LOGIN, SharePreferenceUtil.getBoolean(Constants.IS_LOGIN, false));
        ARouter.getInstance()
                .build(url).with(bundle)
                .setProvider(new LoginInterceptor())
                .navigation(activity, requestcode);
    }

    /**
     * 获取Fragment
     *
     * @return
     */
    public static Fragment getFragment(String url) {
        return (Fragment) ARouter.getInstance().build(url).navigation();
    }

    public static boolean checkLoginState(Context context) {
        return true;
    }
}

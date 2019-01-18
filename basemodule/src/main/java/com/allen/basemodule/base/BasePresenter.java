package com.allen.basemodule.base;

import android.os.Bundle;
import android.util.Log;

import com.allen.basemodule.config.Constants;

/**
 * Created by xjs on 2019/1/18.
 */

public abstract class BasePresenter<V extends BaseView> {
    private V baseView;

    public BasePresenter() {
    }

    public void onBindView(V view) {
        this.baseView = view;
    }

    public void onUnbindView() {
        this.baseView = null;
    }

    public void onDestroyPersenter() {
        Log.e(Constants.TAG, "-------> onDestroyPersenter");
    }

    public void onSaveInstanceState(Bundle outState) {
        Log.e(Constants.TAG, "-------> onSaveInstanceState");
    }

    public V getView() {
        return baseView;
    }
}

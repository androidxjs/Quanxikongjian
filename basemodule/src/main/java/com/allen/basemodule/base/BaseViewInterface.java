package com.allen.basemodule.base;

import android.support.annotation.LayoutRes;

/**
 * Created by xjs on 2019/1/18.
 */

public interface BaseViewInterface {
    /**
     * get布局id
     *
     * @return
     */
    @LayoutRes
    int getLayoutId();

    /**
     * 初始化布局
     *
     * @return
     */
    void initView();
}

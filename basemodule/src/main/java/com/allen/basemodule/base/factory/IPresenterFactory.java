package com.allen.basemodule.base.factory;

import com.allen.basemodule.base.BasePresenter;
import com.allen.basemodule.base.BaseView;

/**
 * Created by xjs on 2019/1/18.
 */

public interface IPresenterFactory<V extends BaseView, P extends BasePresenter<V>> {
    P createPresenter();
}

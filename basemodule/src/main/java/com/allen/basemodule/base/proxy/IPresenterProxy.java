package com.allen.basemodule.base.proxy;

import com.allen.basemodule.base.BasePresenter;
import com.allen.basemodule.base.BaseView;

/**
 * Created by xjs on 2019/1/18.
 */

public interface IPresenterProxy<V extends BaseView,P extends BasePresenter<V>> {
    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getPresenter();
}

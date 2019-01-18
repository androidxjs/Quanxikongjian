package com.allen.basemodule.base.factory;

import com.allen.basemodule.base.BasePresenter;
import com.allen.basemodule.base.BaseView;
import com.allen.basemodule.base.CreatePresenter;

/**
 * Created by xjs on 2019/1/18.
 */

public class PresenterFactory<V extends BaseView, P extends BasePresenter<V>> implements IPresenterFactory<V, P> {
    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;

    public PresenterFactory(Class<P> presenterclass) {
        this.mPresenterClass = presenterclass;
    }
    public static <V extends BaseView,P extends BasePresenter<V>> PresenterFactory<V,P> createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if(annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterFactory<V,P>(aClass);
    }
    @Override
    public P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter create failed!，" +
                    "please check if declaration @CreatePresenter(xxx.class) anotation or not");
        }
    }
}

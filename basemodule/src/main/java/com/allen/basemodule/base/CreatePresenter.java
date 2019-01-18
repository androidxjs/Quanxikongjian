package com.allen.basemodule.base;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xjs on 2019/1/18.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}

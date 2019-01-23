package com.allen.basemodule.commen;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.allen.basemodule.config.Constants;
import com.allen.basemodule.utils.RouterUtil;

/**
 * Created by xjs on 2019/1/22.
 * 路由拦截器
 */
@Interceptor(priority = 8, name = Constants.LOGIN_INTERCEPTOR)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
//        if (postcard.getExtra() == ConstantMap.LOGIN_EXTRA) {
//            //判断是否登录。
//            boolean isLogin = postcard.getExtras().getBoolean(ConstantMap.IS_LOGIN);
//            if (isLogin) {
//                callback.onContinue(postcard);
//            } else {
//                //如果没有登录，那么跳转到登录界面。
//                RouterUtil.goToActivity(RouterUrlManager.LOGIN);
//            }
//        } else {
//            callback.onContinue(postcard);
//        }
    }

    @Override
    public void init(Context context) {

    }
}

package com.allen.basemodule.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.BuildConfig;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.basemodule.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhouyou.http.EasyHttp;

/**
 * Created by xjs on 2019/1/18.
 */

public class AllenApplication extends MultiDexApplication {
    private Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //分包初始化
        MultiDex.install(this);
        initARouter();
        EasyHttp.init(this);
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的基本设置构建器
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {
                //取消内容不满一页时开启上拉加载功能
                refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
                //是否在刷新的时候禁止列表的操作
                refreshLayout.setDisableContentWhenRefresh(true);
                //是否在加载的时候禁止列表的操作
                refreshLayout.setDisableContentWhenLoading(true);
            }
        });
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.white, R.color.white);
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}

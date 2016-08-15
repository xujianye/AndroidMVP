package com.xjy.hyx.mvpretrofitproject.retrofit.handler;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 16:51
 * email：jianyexu@hyx.com
 */
public interface ResHandler<T, D> {
    T parse(D d);
}

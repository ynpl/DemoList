package com.neufmer.ygfstore.view.uichange;

import com.wangxing.code.mvvm.base.event.SingleLiveEvent;

/**
 * Created by WangXing on 2019/7/8.
 */
public class SwipeRefreshUIChangeObservable {

    //下拉刷新完成
    public SingleLiveEvent<Void> finishRefreshing = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> finishRefreshingWithNoMore = new SingleLiveEvent<>();
    //上拉加载完成
    public SingleLiveEvent<Void> finishLoadMore = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> finishLoadMoreWithNoMore = new SingleLiveEvent<>();
    //没有更多
    public SingleLiveEvent<Void> noMore = new SingleLiveEvent<>();

}

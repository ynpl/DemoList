package com.neufmer.ygfstore.ui.main;

import android.app.Application;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.api.source.MainModel;
import com.neufmer.ygfstore.bean.TokenRenew;
import com.wangxing.code.mvvm.base.BaseViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.manager.CacheInfoManager;

/**
 * Created by WangXing on 2019/6/11.
 */
public class MainViewModel extends BaseViewModel<MainModel> {

    public SingleLiveEvent<Integer> renewUC = new SingleLiveEvent<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        request(model.postRenew(Const.header()), new ApiCallBack<TokenRenew>() {
            @Override
            protected void onSuccess(TokenRenew renew, String message) {
                CacheInfoManager.getInstance().saveUserToken(renew.getToken());
                renewUC.setValue(0);
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                renewUC.setValue(exception.code);

            }
        },true);

    }
}

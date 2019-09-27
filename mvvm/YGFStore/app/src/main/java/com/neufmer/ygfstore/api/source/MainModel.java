package com.neufmer.ygfstore.api.source;

import com.neufmer.ygfstore.api.AccountApi;
import com.neufmer.ygfstore.bean.TokenRenew;
import com.wangxing.code.mvvm.http.RetrofitClient;
import com.wangxing.code.mvvm.http.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by WangXing on 2019/6/28.
 */
public class MainModel {

    public Observable<BaseResponse<TokenRenew>> postRenew(Map<String, String> header) {
        return RetrofitClient.getInstance().createApi(AccountApi.class).renew(header);
    }

}

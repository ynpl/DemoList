package com.neufmer.ygfstore.api.source;

import com.neufmer.ygfstore.api.AccountApi;
import com.neufmer.ygfstore.bean.UserBean;
import com.wangxing.code.mvvm.http.RetrofitClient;
import com.wangxing.code.mvvm.http.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by WangXing on 2019/6/28.
 */
public class MineModel {

    public Observable<BaseResponse<Void>> postLogout(Map<String, String> header) {
        return RetrofitClient.getInstance().createApi(AccountApi.class).logout(header);
    }

    public Observable<BaseResponse<UserBean>> getUser(Map<String, String> header, String userId) {
        return RetrofitClient.getInstance().createApi(AccountApi.class).user(header, userId);
    }


}

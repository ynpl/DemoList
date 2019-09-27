package com.neufmer.ygfstore.api.source;

import com.neufmer.ygfstore.api.AccountApi;
import com.neufmer.ygfstore.bean.LoginBean;
import com.wangxing.code.mvvm.http.RetrofitClient;
import com.wangxing.code.mvvm.http.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.HeaderMap;

/**
 * Created by WangXing on 2019/6/18.
 */
public class LoginModel {

    public Observable<BaseResponse<LoginBean>> postLogin(RequestBody body) {
        return RetrofitClient.getInstance().createApi(AccountApi.class).login(body);
    }

    public Observable<BaseResponse<Void>> postVerCode(RequestBody body) {
        return RetrofitClient.getInstance().createApi(AccountApi.class).vercode(body);
    }

    public Observable<BaseResponse<Void>> postResetpwd(RequestBody body) {
        return RetrofitClient.getInstance().createApi(AccountApi.class).resetpwd(body);
    }

    public Observable<BaseResponse<Void>> postUpdatepwd(Map<String, String> header, RequestBody body){
        return RetrofitClient.getInstance().createApi(AccountApi.class).updatepwd(header,body);
    }

}

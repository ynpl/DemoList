package com.neufmer.ygfstore.api;

import com.neufmer.ygfstore.bean.LoginBean;
import com.neufmer.ygfstore.bean.TokenRenew;
import com.neufmer.ygfstore.bean.UserBean;
import com.wangxing.code.mvvm.http.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by WangXing on 2019/6/13.
 */
public interface AccountApi {

    /**
     * 登录接口
     *
     * @param body
     * @return
     */
    @POST("/login")
    Observable<BaseResponse<LoginBean>> login(@Body RequestBody body);

    /**
     * 退出接口
     *
     * @param header
     * @return
     */
    @POST("/logout")
    Observable<BaseResponse<Void>> logout(@HeaderMap Map<String, String> header);

    /**
     * 获取新token
     *
     * @param header
     * @return
     */
    @POST("/token/renew")
    Observable<BaseResponse<TokenRenew>> renew(@HeaderMap Map<String, String> header);

    /**
     * 重置密码
     *
     * @param body
     * @return
     */
    @POST("/resetpwd")
    Observable<BaseResponse<Void>> resetpwd(@Body RequestBody body);

    /**
     * 获取验证码
     *
     * @param body
     * @return
     */
    @POST("/vercode")
    Observable<BaseResponse<Void>> vercode(@Body RequestBody body);

    /**
     * 修改密码
     *
     * @param body
     * @return
     */
    @POST("/updatepwd")
    Observable<BaseResponse<Void>> updatepwd(@HeaderMap Map<String, String> header,@Body RequestBody body);

    @GET("/user/{userId}")
    Observable<BaseResponse<UserBean>> user(@HeaderMap Map<String, String> header,@Path("userId") String userId);

}

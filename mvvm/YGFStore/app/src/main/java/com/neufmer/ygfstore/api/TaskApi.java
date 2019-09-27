package com.neufmer.ygfstore.api;

import com.neufmer.ygfstore.bean.GroupsUsersBean;
import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.bean.InspectionsheetsBean;
import com.neufmer.ygfstore.bean.StoresBean;
import com.neufmer.ygfstore.bean.TaskBean;
import com.neufmer.ygfstore.bean.TasksBean;
import com.wangxing.code.mvvm.http.base.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by WangXing on 2019/6/26.
 */
public interface TaskApi {


    /**
     * 查询任务列表
     *
     * @param headers
     * @param status    #任务状态 #必传
     * @param type  #任务类型 # 必传;TEAM：团体任务；PERSON：个人任务；STORE：门店任务
     * @param startDate  #开始时间
     * @param endDate   # 结束时间
     * @param keyword   #模糊查询，非必传，[没有的就不传该字段]
     * @param pn    #分页号  非必传，默认：1
     * @param pz    #非必传 分页大小，默认：20
     * @return
     */
    @GET("/tasks")
    Observable<BaseResponse<TasksBean>> tasks(@HeaderMap Map<String, String> headers
            , @Query("status") String status
            , @Query("type") String type
            , @Query("startDate") String startDate
            , @Query("endDate") String endDate
            , @Query("keyword") String keyword
            , @Query("pn") String pn
            , @Query("pz") String pz
    );



    //lt
    /**
     * 查询门店任务列表
     *
     * @param headers
     * @param status    #任务状态 #必传
     * @param type  #任务类型 # 必传;TEAM：团体任务；PERSON：个人任务；STORE：门店任务
     * @param clientId  #门店id
     * @param startDate #开始时间
     * @param endDate   # 结束时间
     * @param keyword   #模糊查询，非必传，[没有的就不传该字段]
     * @param pn     #分页号  非必传，默认：1
     * @param pz     #非必传 分页大小，默认：20
     * @return
     */
    @GET("/tasks")
    Observable<BaseResponse<TasksBean>> querySingleStoretasks(@HeaderMap Map<String, String> headers
            , @Query("status") String status
            , @Query("type") String type
            , @Query("clientId") int clientId
            , @Query("startDate") String startDate
            , @Query("endDate") String endDate
            , @Query("keyword") String keyword
            , @Query("pn") String pn
            , @Query("pz") String pz
    );


    /**
     * 单个任务查询
     *
     * @param headers
     * @param id
     * @return
     */
    @GET("/task/{id}")
    Observable<BaseResponse<TaskBean>> taskSingle(@HeaderMap Map<String, String> headers, @Path("id") int id);

    /**
     * 单个任务查询
     *
     * @param headers
     * @param id
     * @return
     */
    @POST("/task/{id}/start")
    Observable<BaseResponse<Void>> taskStart(@HeaderMap Map<String, String> headers, @Path("id") int id);

    /**
     * 查询门店
     *
     * @param headers
     * @param longitude
     * @param latitude
     * @param keyword
     * @return
     */
    @GET("/stores")
    Observable<BaseResponse<List<StoresBean>>> stores(@HeaderMap Map<String, String> headers
            , @Query("longitude") String longitude
            , @Query("latitude") String latitude
            , @Query("inspectorIds") String inspectorIds
            , @Query("keyword") String keyword
    );

    /**
     * 巡查组、人员查询
     *
     * @param headers
     * @return
     */
    @GET("/groups/users")
    Observable<BaseResponse<List<GroupsUsersBean>>> groups_users(@HeaderMap Map<String, String> headers);

    /**
     * 巡查表查询
     *
     * @param headers
     * @return
     */
    @GET("/inspectionsheets")
    Observable<BaseResponse<List<InspectionsheetsBean>>> inspectionsheets(@HeaderMap Map<String, String> headers);

    /**
     * 巡查标准信息查询
     *
     * @param headers
     * @return
     */
    @GET("/task/{id}/inspectionsheet")
    Observable<BaseResponse<InspectionsheetResultBean>> inspectionsheetsQuery(@HeaderMap Map<String, String> headers, @Path("id") String id);

    /**
     * 新建任务
     *
     * @param headers
     * @return
     */
    @POST("/tasks/batchcreate")
    Observable<BaseResponse<Void>> batchcreate(@HeaderMap Map<String, String> headers, @Body RequestBody body);

    /**
     * 巡查结果查询
     *
     * @param headers
     * @return
     */
    @POST("/tasks/{id}/result")
    Observable<BaseResponse<Void>> taskResult(@HeaderMap Map<String, String> headers, @Path("id") int id);


    @Multipart
    @POST("/oss/upload")
    Observable<BaseResponse<String>> upload(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);


    /**
     * 完成单次任务巡查
     *
     * @param headers
     * @param taskId
     * @return
     */
    @GET("/task/{taskId}/complete")
    Observable<BaseResponse<Void>> complete(@HeaderMap Map<String, String> headers
            , @Path("taskId") String taskId
    );


    //lt
    /**
     * 上传
     *
     * @param headers
     * @return
     */
    @POST("/task/{taskId}/submit")
    Observable<BaseResponse<Void>> submit(@HeaderMap Map<String, String> headers ,@Path("taskId") String taskId, @Body RequestBody body);

}

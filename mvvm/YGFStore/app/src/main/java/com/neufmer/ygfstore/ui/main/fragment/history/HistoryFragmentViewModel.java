package com.neufmer.ygfstore.ui.main.fragment.history;

import android.annotation.SuppressLint;
import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.SelectDateBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.ContentParamDao;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.neufmer.ygfstore.ui.search_history.date.SearchHistoryDateActivity;
import com.neufmer.ygfstore.ui.search_history.keyword.SearchHistoryKeywordActivity;
import com.wangxing.code.mvvm.base.BaseViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.http.util.RequestBodyUtil;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.ContextUtils;
import com.wangxing.code.mvvm.utils.GsonUtil;
import com.wangxing.code.mvvm.utils.KLog;
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by WangXing on 2019/6/13.
 */

//lt
public class HistoryFragmentViewModel extends BaseViewModel<TaskModel> {
//public class HistoryFragmentViewModel extends BaseViewModel {

    //lt
    private final SubmitParamDao submitParamDao;
    private List<SubmitParamEntity> mSubmitParamEntities = new ArrayList<>();
    private ContentParamDao contentParamDao;


    public ObservableField<String> selectedDate = new ObservableField<>(ContextUtils.getContext().getString(R.string.history_fragment_current_month));

    public ObservableInt syncVisibility = new ObservableInt(View.GONE);

    private SelectDateBean mSelectDateBean;

    public HistoryFragmentViewModel(@NonNull Application application) {
        super(application);
        //lt
        AppDatabase database = AppDatabase.getDatabase();
        submitParamDao = database.SubmitParamDao();
        submitParamDao.querySubmitListByUserId(CacheInfoManager.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<SubmitParamEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<SubmitParamEntity> submitParamEntities) {
                        mSubmitParamEntities = submitParamEntities;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });


    }

    /**
     * 选择日期
     */
    public BindingCommand selectDate = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle mBundle = new Bundle();
            if (mSelectDateBean != null) {
                if (!StringUtils.isEmpty(mSelectDateBean.getMonthDay())) {
                    mBundle.putInt("searchType", 0);
                } else {
                    mBundle.putInt("searchType", 1);
                }
            } else {
                mBundle.putInt("searchType", 0);
            }
            mBundle.putParcelable("searchData", mSelectDateBean);
            startActivity(SearchHistoryDateActivity.class, mBundle);
        }
    });

    @SuppressLint("SimpleDateFormat")
    public void setSelectDateBean(SelectDateBean data) {
        mSelectDateBean = data;
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar calendar = Calendar.getInstance();
        try {
            if (!StringUtils.isEmpty(data.getMonthDay())) {
                //按月
                date = monthFormat.parse(data.getMonthDay());
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);
                calendar.setTime(date);
                if (currentMonth == calendar.get(Calendar.MONTH) &&
                        currentYear == calendar.get(Calendar.YEAR)) {
                    selectedDate.set(ContextUtils.getContext().getString(R.string.history_fragment_current_month));
                } else {
                    selectedDate.set(calendar.get(Calendar.MONTH) + 1 + "月");
                }
            } else {
                //按日
                date = dayFormat.parse(data.getStartDay());
                calendar.setTime(date);
                String startMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                String startDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

                date = dayFormat.parse(data.getEndDay());
                calendar.setTime(date);
                String endMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                String endDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                selectedDate.set(startMonth + "-" + startDay + "~" + endMonth + "-" + endDay);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索历史
     */
    public BindingCommand searchHistory = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SearchHistoryKeywordActivity.class);
        }
    });

    /**
     * 同步
     */
    public BindingCommand syncClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            // TODO: 2019/6/26 通过发送liveEvent实现

            //lt
            for(final SubmitParamEntity spe : mSubmitParamEntities){
                final int taskId = spe.taskId;
                contentParamDao.queryContentList(CacheInfoManager.getInstance().getUserId(), ""+taskId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new SingleObserver<List<ContentParamEntity>>() {
                            private List<ContentParamEntity> mContentParamEntities;

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<ContentParamEntity> contentParamEntities) {
                                KLog.e("DB contentParamEntities is : " + GsonUtil.GsonString(contentParamEntities));
                                mContentParamEntities = contentParamEntities;
                                sync(mContentParamEntities,spe,taskId+"");

                            }

                            @Override
                            public void onError(Throwable e) {
                                KLog.e(e.toString());
                            }
                        });



            }
        }
    });



    private void sync( final List<ContentParamEntity> mContentParamEntities,final SubmitParamEntity spe,final String taskId){
        final int size = mContentParamEntities.size();
        final ContentParamEntity[] array = mContentParamEntities.toArray(new ContentParamEntity[size]);

        //上传图片
        Observable.fromArray(array)
                .groupBy(new Function<ContentParamEntity, List<String>>() {
                    @Override
                    public List<String> apply(ContentParamEntity entity) throws Exception {
                        return entity.getImage();
                    }
                })
                .subscribe(new Consumer<GroupedObservable<List<String>, ContentParamEntity>>() {
                    @Override
                    public void accept(GroupedObservable<List<String>, ContentParamEntity> observable) throws Exception {

                        int keySize = observable.getKey().size();
                        if (keySize != 0) {
                            final String[] images = observable.getKey().toArray(new String[keySize]);

                            observable.subscribe(new Consumer<ContentParamEntity>() {
                                @Override
                                public void accept(final ContentParamEntity entity) throws Exception {

                                    Observable.fromArray(images).concatMap(new Function<String, Observable<ArrayList<String>>>() {
                                        @Override
                                        public Observable<ArrayList<String>> apply(String s) throws Exception {
                                            final ArrayList<String> resultList = new ArrayList<>();
//                                        MultipartBody.Part file = RequestBodyUtil.getFilePart("file", s);
                                            File file = new File(s);
                                            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                                            MultipartBody.Part formData = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                                            request(model.postUpload(Const.header(), formData), new ApiCallBack<String>() {

                                                @Override
                                                protected void onSuccess(String s, String message) {
                                                    resultList.add(s);
                                                }

                                                @Override
                                                protected void onFailed(ResponseThrowable exception) {
                                                    KLog.e(GsonUtil.GsonString(exception.message));
                                                }
                                            });
                                            if (resultList.size() == images.length) {
                                                return Observable.just(resultList);
                                            } else {
                                                return Observable.just(new ArrayList<String>(

                                                ));
                                            }

                                        }
                                    })
                                            .subscribe(new Consumer<ArrayList<String>>() {
                                                @Override
                                                public void accept(ArrayList<String> strings) throws Exception {
                                                    if (!strings.isEmpty()) {
                                                        entity.setImage(strings);
                                                    }

                                                }
                                            });

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    final String[] inspectorSign = new String[1];
                                    final String[] contactSign = new String[1];
                                    //完成  上传巡店签名
                                    if (!StringUtils.isEmpty(spe.getInspectorSign())) {
                                        final Map<String, String> map = new HashMap<>();
                                        map.put("content", GsonUtil.GsonString(array));
                                        map.put("abandonReason", spe.getAbandonReason());
                                        map.put("remark", spe.getRemark());
                                        File file = new File(spe.getInspectorSign());
                                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                                        MultipartBody.Part formData = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                                        request(model.postUpload(Const.header(), formData), new ApiCallBack<String>() {

                                            @Override
                                            protected void onSuccess(String s, String message) {
                                                inspectorSign[0] = s;
                                                //巡查签名网络路径
                                            }

                                            @Override
                                            protected void onFailed(ResponseThrowable exception) {
                                                KLog.e(GsonUtil.GsonString(exception.message));
                                            }

                                            @Override
                                            public void onComplete() {
                                                super.onComplete();
                                                //完成后店名签名不为空继续上传
                                                if (!StringUtils.isEmpty(spe.getContactSign())) {
                                                    File file = new File(spe.getContactSign());
                                                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                                                    MultipartBody.Part formData = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                                                    request(model.postUpload(Const.header(), formData), new ApiCallBack<String>() {

                                                        @Override
                                                        protected void onSuccess(String s, String message) {
                                                            contactSign[0] = s;
                                                            //巡查签名网络路径
                                                        }

                                                        @Override
                                                        protected void onFailed(ResponseThrowable exception) {
                                                            KLog.e(GsonUtil.GsonString(exception.message));
                                                        }

                                                        @Override
                                                        public void onComplete() {
                                                            super.onComplete();

                                                            map.put("inspectorSign", inspectorSign[0]);
                                                            map.put("contactSign", contactSign[0]);

                                                            RequestBody body = RequestBodyUtil.getBody(map);
                                                            // TODO: 2019/7/18  调用提交巡查数据接口
                                                            //lt
                                                            if(!StringUtils.isEmpty(taskId))
                                                                request(model.submit(Const.header(), taskId, body), new ApiCallBack() {
                                                                    @Override
                                                                    protected void onSuccess(Object o, String message) {
                                                                        ToastUtils.showShort("提交成功");
                                                                    }

                                                                    @Override
                                                                    protected void onFailed(ResponseThrowable exception) {
                                                                        ToastUtils.showShort("提交失败");
                                                                    }
                                                                },true);
                                                        }
                                                    });
                                                } else {
                                                    //为空直接提交上传数据
                                                    map.put("inspectorSign", inspectorSign[0]);
                                                    map.put("contactSign", contactSign[0]);
                                                    RequestBody body = RequestBodyUtil.getBody(map);
                                                    // TODO: 2019/7/18  调用提交巡查数据接口
                                                    //lt
                                                    if(!StringUtils.isEmpty(taskId))
                                                        request(model.submit(Const.header(), taskId, body), new ApiCallBack() {
                                                            @Override
                                                            protected void onSuccess(Object o, String message) {
                                                                ToastUtils.showShort("提交成功");
                                                            }

                                                            @Override
                                                            protected void onFailed(ResponseThrowable exception) {
                                                                ToastUtils.showShort("提交失败");
                                                            }
                                                        },true);
                                                }


                                            }
                                        });
                                    }

                                }
                            });
                        }


                    }
                });

    }

}

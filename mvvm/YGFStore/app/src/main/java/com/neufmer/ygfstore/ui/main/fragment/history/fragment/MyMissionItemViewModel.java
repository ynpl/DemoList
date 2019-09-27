package com.neufmer.ygfstore.ui.main.fragment.history.fragment;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.LoginBean;
import com.neufmer.ygfstore.bean.TaskBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.ContentParamDao;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.neufmer.ygfstore.view.ProgressButton;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.http.body.ProgressBody;
import com.wangxing.code.mvvm.http.body.ProgressBodyListener;
import com.wangxing.code.mvvm.http.body.ProgressInfo;
import com.wangxing.code.mvvm.http.util.RequestBodyUtil;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.GsonUtil;
import com.wangxing.code.mvvm.utils.KLog;
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
public class MyMissionItemViewModel extends MultiItemViewModel<MyMissionFragmentViewModel> {

    private Observable<Long> observe;
    public TaskBean field;
    public String assignees;
    public String time;
    public boolean isSync;
    private Disposable subscribe;
    private ProgressButton mProgressButton;
    private TaskModel model;

    private final ContentParamDao contentParamDao;
    private final SubmitParamDao submitParamDao;
    private List<ContentParamEntity> mContentParamEntities;
    private SubmitParamEntity mSubmitParamEntity;



    public MyMissionItemViewModel(@NonNull MyMissionFragmentViewModel viewModel, TaskBean data, TaskModel model) {
        super(viewModel);
        field = data;
        assignees = field.getAssigneesStr();
        time = field.getTimeStr();
        isSync = Const.SUBMITED.equals(field.getStatus());
        this.model = model;
        AppDatabase database = AppDatabase.getDatabase();
        contentParamDao = database.contentParamDao();
        submitParamDao = database.SubmitParamDao();


        observe = Observable.interval(700, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());

    }

//    public BindingCommand viewTask = new BindingCommand(new BindingAction() {
//        @Override
//        public void call() {
//            viewModel.startActivity(PatrolTaskActivity.class);
//        }
//    });

    public BindingCommand<ProgressButton> current = new BindingCommand<ProgressButton>(new BindingConsumer<ProgressButton>() {
        @Override
        public void call(ProgressButton progressButton) {
            mProgressButton = progressButton;
        }
    });

    public BindingCommand onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (subscribe != null) {
                subscribe.dispose();
            }
            mProgressButton.reset();
        }
    });

    public BindingCommand<String> onProgressListener = new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String action) {

            TaskBean tb = field;
            final String taskId = tb.getId()+"";
            LiveEventBus.get().with("onProgressListener",String.class).post(taskId+"");

            initData(taskId);

            final int size = mContentParamEntities.size();
            final ContentParamEntity[] array = mContentParamEntities.toArray(new ContentParamEntity[size]);
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
                                                viewModel.request(model.postUpload(Const.header(), formData), new ApiCallBack<String>() {

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
                                                    return Observable.just(new ArrayList<String>());
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
                                        if (!StringUtils.isEmpty(mSubmitParamEntity.getInspectorSign())) {
                                            final Map<String, String> map = new HashMap<>();
                                            map.put("content", GsonUtil.GsonString(array));
                                            map.put("abandonReason", mSubmitParamEntity.getAbandonReason());
                                            map.put("remark", mSubmitParamEntity.getRemark());
                                            File file = new File(mSubmitParamEntity.getInspectorSign());
                                            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                                            MultipartBody.Part formData = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                                            viewModel.request(model.postUpload(Const.header(), formData), new ApiCallBack<String>() {

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
                                                    if (!StringUtils.isEmpty(mSubmitParamEntity.getContactSign())) {
                                                        File file = new File(mSubmitParamEntity.getContactSign());
                                                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                                                        MultipartBody.Part formData = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                                                        viewModel.request(model.postUpload(Const.header(), formData), new ApiCallBack<String>() {

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
                                                                    viewModel.request(model.submit(Const.header(), taskId, body), new ApiCallBack() {
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
                                                            viewModel.request(model.submit(Const.header(), taskId, body), new ApiCallBack() {
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























            Map<String, String> map = new HashMap<>();


            ProgressBody body = new ProgressBody(map, new ProgressBodyListener() {
                @Override
                public void onProgress(final ProgressInfo progressInfo) {
                            Observable.interval(progressInfo.getContentLength(), TimeUnit.MILLISECONDS)
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                                        @Override
                                        public void accept(Long aLong) throws Exception {
                                            if (mProgressButton.getState() == ProgressButton.FINISH) {
                                                subscribe.dispose();
                                                return;
                                            }
                                            int p = new Random().nextInt(20);
                                            mProgressButton.setProgress(mProgressButton.getProgress() + progressInfo.getCurrentBytes());

                                        }
                                    });
                }
            });

//            viewModel.request(Observable.create(new ObservableOnSubscribe<body>() {
//                @Override
//                public void subscribe(ObservableEmitter emitter) throws Exception {
//
//                }
//            }), new ApiCallBack() {
//                @Override
//                protected void onSuccess(Object o, String message) {
//
//                }
//
//                @Override
//                protected void onFailed(ResponseThrowable exception) {
//
//                }
//
//                @Override
//                public void onNext(Object o) {
//
//                }
//            });



            switch (action) {
                case "progress":
                    subscribe = observe.subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (mProgressButton.getState() == ProgressButton.FINISH) {
                                subscribe.dispose();
                                return;
                            }
                            int p = new Random().nextInt(20);
                            mProgressButton.setProgress(mProgressButton.getProgress() + p);

                        }
                    });
                    break;
                case "pause":
                    subscribe.dispose();
                    break;
                case "resume":
                    onProgressListener.execute("progress");
                    break;
                case "finish":
                    subscribe.dispose();
                    break;
            }
        }
    });


    private void initData(String taskId) {

        contentParamDao.queryContentList(CacheInfoManager.getInstance().getUserId(), taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<ContentParamEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ContentParamEntity> contentParamEntities) {
                        KLog.e("DB contentParamEntities is : " + GsonUtil.GsonString(contentParamEntities));
                        mContentParamEntities = contentParamEntities;
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("本地缓存数据异常。");
                        KLog.e(e.toString());
                    }
                });

        submitParamDao.querySubmitDetail(CacheInfoManager.getInstance().getUserId(), taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<SubmitParamEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SubmitParamEntity submitParamEntity) {
                        mSubmitParamEntity = submitParamEntity;
                        KLog.e("DB SubmitParamEntity is : " + GsonUtil.GsonString(submitParamEntity));
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("本地缓存数据异常。");
                    }
                });



    }


}

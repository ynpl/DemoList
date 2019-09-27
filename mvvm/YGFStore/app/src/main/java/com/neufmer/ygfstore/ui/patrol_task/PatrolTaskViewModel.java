package com.neufmer.ygfstore.ui.patrol_task;

import android.annotation.SuppressLint;
import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.TaskBean;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailActivity;
import com.neufmer.ygfstore.util.MapLocationUtil;
import com.neufmer.ygfstore.view.uichange.MultiStatusLayoutUIChangeObservable;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.manager.CacheInfoManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by WangXing on 2019/6/19.
 */

@SuppressLint("SimpleDateFormat")
public class PatrolTaskViewModel extends ToolbarViewModel<TaskModel> {

    /**
     * 设置为visible的时 setLineBg(R.drawable.bg_transparent);
     */
    public ObservableInt addressErrorTip = new ObservableInt(View.GONE);
    private ObservableDouble latitude = new ObservableDouble();
    private ObservableDouble longitude = new ObservableDouble();

    public ObservableBoolean startTask = new ObservableBoolean(false);

    public ObservableField<String> assigneesText = new ObservableField<>("");

    public ObservableField<String> currentLocationText = new ObservableField<>("定位中...");
    public ObservableField<String> currentDateText = new ObservableField<>("");

    public MultiStatusLayoutUIChangeObservable multiStatusLayoutUI = new MultiStatusLayoutUIChangeObservable();

    public SingleLiveEvent<TaskBean> setDataUC = new SingleLiveEvent<>();
    private int mTaskId;

    public PatrolTaskViewModel(@NonNull Application application) {
        super(application);
        SimpleDateFormat result = new SimpleDateFormat("yyyy/MM/dd");
        currentDateText.set(result.format(new Date()));
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.patrol_task_activity_title);
        setLineBg(R.drawable.bg_transparent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MapLocationUtil.getInstance().stopLocation();
    }

    /**
     * 开始巡查任务
     */
    public BindingCommand startTaskClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putInt("taskId", mTaskId);
            startActivity(TaskDetailActivity.class, bundle);
        }
    });

    /**
     * 重新定位
     */
    public BindingCommand reLocationClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showDialog();
            MapLocationUtil.getInstance().getLocation(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (latitude.get() == aMapLocation.getLatitude() &&
                            longitude.get() == aMapLocation.getLongitude()) {
                        addressErrorTip.set(View.GONE);
                        startTask.set(true);
                    } else {
                        addressErrorTip.set(View.VISIBLE);
//                        startTask.set(false);
                        startTask.set(true);
                    }
                    CacheInfoManager.getInstance().saveKeyValue("latitude", String.valueOf(latitude));
                    CacheInfoManager.getInstance().saveKeyValue("longitude", String.valueOf(longitude));
                    currentLocationText.set(aMapLocation.getAoiName());
                    dismissDialog();
                }
            });
        }
    });

    public void getTaskSingle(int taskId) {
        mTaskId = taskId;
        request(model.getTaskSingle(Const.header(), taskId), new ApiCallBack<TaskBean>() {


            @Override
            protected void onSuccess(final TaskBean taskBean, String message) {
                setDataUC.setValue(taskBean);
                StringBuilder builder = new StringBuilder();
                List<TaskBean.AssigneesBean> assignees = taskBean.getAssignees();
                for (int i = 0; i < assignees.size(); i++) {
                    String name = assignees.get(i).getName();
                    if (i == assignees.size() - 1) {
                        builder.append(name);
                    } else {
                        builder.append(name).append("\t");
                    }
                }
                assigneesText.set(builder.toString());
                MapLocationUtil.getInstance().getLocation(new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        if (taskBean.getTargetStore().getLatitude() == aMapLocation.getLatitude() &&
                                taskBean.getTargetStore().getLongitude() == aMapLocation.getLongitude()) {
                            addressErrorTip.set(View.GONE);
                            startTask.set(true);
                        } else {
                            addressErrorTip.set(View.VISIBLE);
//                            startTask.set(false);
                            startTask.set(true);
                        }
                        latitude.set(taskBean.getTargetStore().getLatitude());
                        longitude.set(taskBean.getTargetStore().getLongitude());
                        CacheInfoManager.getInstance().saveKeyValue("latitude", String.valueOf(latitude));
                        CacheInfoManager.getInstance().saveKeyValue("longitude", String.valueOf(longitude));
                        currentLocationText.set(aMapLocation.getAoiName());
                    }
                });
                multiStatusLayoutUI.showContent.call();
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                if (exception.handleCode(Const.errorCode())) {
                    exception.toast();
                }
            }
        });
    }

    private String dateFromat(String date) {
        SimpleDateFormat resultFor = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat result = new SimpleDateFormat("yyyy/MM/dd");
        Date parse = null;
        try {
            parse = resultFor.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.format(parse);
    }

}

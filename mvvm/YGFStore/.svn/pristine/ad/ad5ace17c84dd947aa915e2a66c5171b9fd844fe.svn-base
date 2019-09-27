package com.neufmer.ygfstore.ui.main.fragment.history.fragment;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.SelectDateBean;
import com.neufmer.ygfstore.bean.TaskBean;
import com.neufmer.ygfstore.bean.TasksBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.ContentParamDao;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.neufmer.ygfstore.ui.main.fragment.task.TaskFragmentViewModel;
import com.neufmer.ygfstore.ui.main.fragment.task.TaskItemViewModel;
import com.neufmer.ygfstore.util.LogHelper;
import com.neufmer.ygfstore.view.uichange.SwipeRefreshUIChangeObservable;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wangxing.code.mvvm.base.BaseViewModel;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
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
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by WangXing on 2019/6/13.
 */
public class MyMissionFragmentViewModel extends BaseViewModel<TaskModel> {

    //lt
    private int index = -1;
    private String taskType = Const.PERSON;
    private String taskState = Const.COMPLETED;
    public List<TaskBean> resultList = new ArrayList<>();
    private int page = 1;
    private String startDate;
    private String endDate;
    public SwipeRefreshUIChangeObservable swipeRefreshUC = new SwipeRefreshUIChangeObservable();
    public ObservableField<String> currentDateText = new ObservableField<>();
    public ObservableList<MultiItemViewModel> itemList = new ObservableArrayList<>();
    //选择的日期
    private SelectDateBean sdb;

    public ItemBinding<MultiItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<MultiItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MultiItemViewModel item) {
            if (position == 0 && index == 1) {
//                获取itemtype
                Object itemType = item.getItemType();
//                设置itemtype
                item.multiItemType("header_type");
                itemBinding.set(BR.viewModel, R.layout.header_history_task_list);
            } else {
                itemBinding.set(BR.viewModel, R.layout.item_task_history_list);
            }
        }
    });



    public MyMissionFragmentViewModel(@NonNull Application application) {
        super(application);
        // lt


        String[] weeks = ContextUtils.getContext().getResources().getStringArray(R.array.week_string_array);
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        startDate = format.format(time);
        endDate = startDate;
        getData();

    }

    private void getData() {
        request(model.getTasks(Const.header(), taskState, taskType, startDate, endDate, "", page + "", "10"), new ApiCallBack<TasksBean>() {
            @Override
            protected void onSuccess(TasksBean tasksBean, String message) {
                List<TaskBean> tasks = tasksBean.getTasks();
                resultList.addAll(tasks);
                int tasksSize = tasks.size();
                for (TaskBean bean : tasks) {
                    if(itemList.size() == 0 && index == 1){
                        itemList.add(new MyMissionItemHeaderViewModel(MyMissionFragmentViewModel.this,resultList,new ArrayList<TaskBean>()));
                    }else{
                        itemList.add(new MyMissionItemViewModel(MyMissionFragmentViewModel.this, bean,model));
                    }

                }
                //刷新
                if (page == 1) {
                    if (tasksSize != 10) {
                        swipeRefreshUC.finishRefreshingWithNoMore.call();
                    } else {
                        swipeRefreshUC.finishRefreshing.call();
                    }
                } else {
                    //加载
                    if (tasksSize != 10) {
                        swipeRefreshUC.finishLoadMoreWithNoMore.call();
                    } else {
                        swipeRefreshUC.finishLoadMore.call();
                    }
                }
                page++;
            }

            @Override
            public void onComplete() {
                super.onComplete();

            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                //刷新
                if (page == 1) {
                    swipeRefreshUC.finishRefreshing.call();
                } else {
                    //加载
                    swipeRefreshUC.finishLoadMore.call();
                }
            }
        });

    }

    public BindingCommand<RefreshLayout> onLoadMoreListener = new BindingCommand<RefreshLayout>(new BindingConsumer<RefreshLayout>() {
        @Override
        public void call(RefreshLayout refreshLayout) {
            ToastUtils.showShort("onLoadMoreListener");
            getData();
        }
    });

    public BindingCommand<RefreshLayout> onRefreshListener = new BindingCommand<RefreshLayout>(new BindingConsumer<RefreshLayout>() {
        @Override
        public void call(RefreshLayout refreshLayout) {
            ToastUtils.showShort("onRefreshListener");
            page = 1;
            itemList.clear();
            resultList.clear();
            getData();
        }
    });


    public void setIndex(int index) {
        this.index = index;
        switch(index){
            case 1:
                taskType = Const.PERSON;
                taskState = Const.COMPLETED;//默认完成任务
            break;
            case 2:
                taskType = Const.TEAM;
                taskState = Const.SUBMITED;
                break;
            case 3:
                taskType = Const.STORE;
                taskState = Const.SUBMITED;
                break;
            default:
                break;
        }
    }


    public void setSelectDate(SelectDateBean data){
        this.sdb = data;
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar calendar = Calendar.getInstance();
        try {
            if (!StringUtils.isEmpty(data.getMonthDay())) {
                //按月
                date = monthFormat.parse(data.getMonthDay());
                calendar.setTime(date);
                int targetMonth = calendar.get(Calendar.MONTH)+1;
                int targetYear = calendar.get(Calendar.YEAR);
                int lastDay =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                startDate = targetYear+"-"+(targetMonth<10 ?("0"+targetMonth):(targetMonth))+"-"+"01";
                endDate =  targetYear+"-"+(targetMonth<10 ?("0"+targetMonth):(targetMonth))+"-"+lastDay;


            } else {
                //按日
                date = dayFormat.parse(data.getStartDay());
                calendar.setTime(date);
                String startMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                String startYear = String.valueOf(calendar.get(Calendar.YEAR));
                String startDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                startDate = startYear+"-"
                        +(calendar.get(Calendar.MONTH) + 1<10 ?("0"+startMonth):(startMonth))+"-"
                        +((calendar.get(Calendar.DAY_OF_MONTH)<10?"0"+startDay:startDay));
                date = dayFormat.parse(data.getEndDay());
                calendar.setTime(date);
                String endYear = String.valueOf(calendar.get(Calendar.YEAR));
                String endMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                String endDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                endDate = endYear+"-"
                        +(calendar.get(Calendar.MONTH) + 1<10 ?("0"+endMonth):(endMonth))+"-"
                        +((calendar.get(Calendar.DAY_OF_MONTH)<10?"0"+endDay:endDay));

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void setTaskState(String state) {
        this.taskState = state;
    }
}

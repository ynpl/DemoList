package com.neufmer.ygfstore.ui.main.fragment.history.fragment;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.bean.TaskBean;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.utils.StringUtils;

import java.util.List;

/**
 * Created by WangXing on 2019/6/13.
 */
public class MyMissionItemHeaderViewModel extends MultiItemViewModel {



    public String  mCompleteCounts;
    int pCompleteCounts = 0;
    public String  mSubmitCounts;
    int pSubmitCounts = 0;
//    public ObservableField<MyMissionHeaderBean>   field;


    //左边radioButton选项观察
    public ObservableBoolean leftRadioCheck = new ObservableBoolean(true);
    //右边radioButton选项观察
    public ObservableBoolean rightRadioCheck = new ObservableBoolean(false);

    public MyMissionItemHeaderViewModel(@NonNull MyMissionFragmentViewModel viewModel, TaskBean data) {
        super(viewModel);
//        field.set(data);
    }


    public MyMissionItemHeaderViewModel(@NonNull MyMissionFragmentViewModel viewModel
            , List<TaskBean> completeList
            , List<TaskBean> unsubmitList) {
        super(viewModel);
        setData(completeList,unsubmitList);
//        field.set(mHeaderBean);
    }

    private void setData(List<TaskBean> completeList, List<TaskBean> unsubmitList) {

        for(TaskBean b : completeList){

            String state = b.getStatus();
            if(StringUtils.isEmpty(state)) continue;
            if(Const.COMPLETED.equals(state))
                pCompleteCounts++;

        }

        for(TaskBean b : unsubmitList){

            String state = b.getStatus();
            if(StringUtils.isEmpty(state)) continue;
            if(Const.COMPLETED.equals(state))
                pSubmitCounts++;

        }

        mCompleteCounts = pCompleteCounts+"";
        mSubmitCounts = pSubmitCounts+"";

    }


    /**
     * 切换完成任务list
     */
    public BindingCommand leftTabClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (!leftRadioCheck.get()) {
                leftRadioCheck.set(true);
                rightRadioCheck.set(false);
            }

            LiveEventBus.get().with("TabClick").post("leftTabClick");
        }
    });
    /**
     * 切换待同步任务list
     */
    public BindingCommand rightTabClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (!rightRadioCheck.get()) {
                rightRadioCheck.set(true);
                leftRadioCheck.set(false);
            }
            LiveEventBus.get().with("TabClick").post("rightTabClick");
        }
    });
}

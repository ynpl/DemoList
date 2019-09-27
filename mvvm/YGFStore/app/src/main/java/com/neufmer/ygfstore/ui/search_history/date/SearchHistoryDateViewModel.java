package com.neufmer.ygfstore.ui.search_history.date;

import android.annotation.SuppressLint;
import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.SelectDateBean;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;

/**
 * Created by WangXing on 2019/6/24.
 */
@SuppressLint("SimpleDateFormat")
public class SearchHistoryDateViewModel extends ToolbarViewModel {

    public ObservableInt mSearchType = new ObservableInt(0);
    public ObservableInt mSearchTypeDay = new ObservableInt(View.VISIBLE);
    public ObservableInt mSearchTypeMonth = new ObservableInt(View.GONE);

    public ObservableField<String> monthDay = new ObservableField<>("");
    public ObservableField<String> startDay = new ObservableField<>("");
    public ObservableField<String> endDay = new ObservableField<>("");
    public SingleLiveEvent<Integer> switchDateTypeUC = new SingleLiveEvent<>();
    public SingleLiveEvent<RadioButton> onCheckedCommandUC = new SingleLiveEvent<>();
    public SingleLiveEvent<SelectDateBean> completeUC = new SingleLiveEvent<>();

    public SearchHistoryDateViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.search_date_activity_title);
        setRightTextColor(R.color.main_blue_5584FF);
        setRightTextSize(R.dimen.font_14);
        setRightTextRes(R.string.common_complete);
        setRightTextVisible(View.VISIBLE);
        setRightTextBg(R.drawable.bg_transparent);
        setRightHasSelect(true);
        setLeftIconVisible(View.GONE);
        setLeftTextVisibleObservable(View.VISIBLE);
    }

    @Override
    protected void rightTextOnClick() {
        super.rightTextOnClick();
        SelectDateBean bean;
        if (mSearchType.get() == 0) {
            bean = new SelectDateBean();
            bean.setMonthDay(monthDay.get());
            completeUC.setValue(bean);
        } else {
            bean = new SelectDateBean();
            bean.setStartDay(startDay.get());
            bean.setEndDay(endDay.get());
            completeUC.setValue(bean);
        }
    }

    public void setSearchType(int searchType) {
        mSearchType.set(searchType);
    }

    /**
     * 切换日历
     */
    public BindingCommand switchSearchType = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (mSearchType.get() == 0) {
                mSearchType.set(1);
                mSearchTypeMonth.set(View.VISIBLE);
                mSearchTypeDay.set(View.GONE);
            } else {
                mSearchType.set(0);
                mSearchTypeDay.set(View.VISIBLE);
                mSearchTypeMonth.set(View.GONE);
            }
            switchDateTypeUC.setValue(mSearchType.get());
        }
    });

    /**
     * 开始时间结束时间切换
     */
    public BindingCommand<RadioButton> onCheckedCommand = new BindingCommand<>(new BindingConsumer<RadioButton>() {
        @Override
        public void call(RadioButton btn) {
            onCheckedCommandUC.setValue(btn);
        }
    });

}

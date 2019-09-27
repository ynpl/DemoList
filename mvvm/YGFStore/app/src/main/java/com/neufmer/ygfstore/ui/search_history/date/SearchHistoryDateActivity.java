package com.neufmer.ygfstore.ui.search_history.date;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.SelectDateBean;
import com.neufmer.ygfstore.databinding.ActivitySearchDateBinding;
import com.neufmer.ygfstore.event.EventKeys;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;
import com.wangxing.code.mvvm.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class SearchHistoryDateActivity extends BaseActivity<ActivitySearchDateBinding, SearchHistoryDateViewModel> {

    private int searchType;
    private SelectDateBean searchData;

    private TimePickerView pvTime;
    private Calendar selectedDate;
    private Calendar startDate;
    private RadioButton checkedBtn;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_search_date;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            searchType = mBundle.getInt("searchType", 0);
            searchData = mBundle.getParcelable("searchData");
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setSearchType(searchType);
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setStatusBar(this, false, false);
        setSupportActionBar(binding.include.toolbar);
        viewModel.initToolbar();
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(1970, 0, 10, 0, 0, 0);
        checkedBtn = binding.rb1;
        if (searchType == 0) {
            viewModel.mSearchTypeDay.set(View.VISIBLE);
            viewModel.mSearchTypeMonth.set(View.GONE);
            pvTime = getMonthType();
        } else {
            viewModel.mSearchTypeMonth.set(View.VISIBLE);
            viewModel.mSearchTypeDay.set(View.GONE);
            pvTime = getDayType();
        }
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (searchData != null && !StringUtils.isEmpty(searchData.getMonthDay())) {
            viewModel.monthDay.set(searchData.getMonthDay());
            try {
                Calendar instance = Calendar.getInstance();
                instance.setTime(monthFormat.parse(searchData.getMonthDay()));
                pvTime.setDate(instance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            viewModel.monthDay.set(monthFormat.format(selectedDate.getTime()));
        }
        if (searchData != null && !StringUtils.isEmpty(searchData.getStartDay()) && !StringUtils.isEmpty(searchData.getEndDay())) {
            viewModel.startDay.set(searchData.getStartDay());
            viewModel.endDay.set(searchData.getEndDay());
            String parseStr;
            if (checkedBtn.getId() == binding.rb1.getId()) {
                parseStr = searchData.getStartDay();
            } else {
                parseStr = searchData.getEndDay();
            }
            try {
                Date parse = dayFormat.parse(parseStr);
                Calendar instance = Calendar.getInstance();
                instance.setTime(parse);
                pvTime.setDate(instance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            viewModel.startDay.set(dayFormat.format(selectedDate.getTime()));
            viewModel.endDay.set(dayFormat.format(selectedDate.getTime()));
        }

        pvTime.show(false);

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.switchDateTypeUC.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer dateType) {
                if (dateType != null) {
                    if (dateType == 0) {
                        pvTime = getMonthType();
                        if (!StringUtils.isEmpty(viewModel.monthDay.get())) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                            try {
                                Date parse = format.parse(viewModel.monthDay.get());
                                Calendar instance = Calendar.getInstance();
                                instance.setTime(parse);
                                pvTime.setDate(instance);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        pvTime.show(false);
                    } else {
                        pvTime = getDayType();
                        String checkedDate = checkedBtn.getText().toString();
                        String parseStr;
                        if (checkedBtn.getId() == binding.rb1.getId()) {
                            parseStr = viewModel.startDay.get();
                        } else {
                            parseStr = viewModel.endDay.get();
                        }
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date parse = format.parse(parseStr);
                            Calendar instance = Calendar.getInstance();
                            instance.setTime(parse);
                            pvTime.setDate(instance);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        pvTime.show(false);
                    }
                }
            }
        });
        viewModel.onCheckedCommandUC.observe(this, new Observer<RadioButton>() {
            @Override
            public void onChanged(@Nullable RadioButton button) {
                checkedBtn = button;
                assert checkedBtn != null;
                CharSequence checkedBtnText = checkedBtn.getText();
                if (!StringUtils.isEmpty(checkedBtnText)) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date parse = format.parse(checkedBtnText.toString());
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(parse);
                        pvTime.setDate(instance);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        viewModel.completeUC.observe(this, new Observer<SelectDateBean>() {
            @Override
            public void onChanged(@Nullable SelectDateBean data) {
                LiveEventBus.get().with(EventKeys.LIVE_EVENT_RETURN_SELECTED_DATE).post(data);
                finish();
            }
        });
    }

    /**
     * 按月选择日期样式
     *
     * @return
     */
    private TimePickerView getMonthType() {
        return new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                        viewModel.monthDay.set(format.format(date));
                    }
                })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                    }
                })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "日", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(ContextCompat.getColor(this, R.color.color_F1F1F1))
                .setContentTextSize(20)
                .setTextColorCenter(ContextCompat.getColor(this, R.color.grey_666666))
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setDecorView(binding.frameLayout)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setBgColor(ContextCompat.getColor(this, R.color.white_ffffff))
                .setOutSideColor(0x00000000)
                .setOutSideCancelable(false)
                .build();
    }

    /**
     * 按日选择日期样式
     *
     * @return
     */
    private TimePickerView getDayType() {
        return new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        if (checkedBtn != null) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            if (checkedBtn.getId() == binding.rb1.getId()) {
                                viewModel.startDay.set(format.format(date));
                            } else {
                                viewModel.endDay.set(format.format(date));
                            }
                        }
                    }
                })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(ContextCompat.getColor(this, R.color.color_F1F1F1))
                .setContentTextSize(20)
                .setTextColorCenter(ContextCompat.getColor(this, R.color.grey_666666))
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setDecorView(binding.frameLayout)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setBgColor(ContextCompat.getColor(this, R.color.white_ffffff))
                .setOutSideColor(0x00000000)
                .setOutSideCancelable(false)
                .build();
    }


}

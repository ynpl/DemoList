package com.neufmer.ygfstore.view.choosedateview;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.neufmer.ygfstore.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by zangpeng on 2018/3/5.
 */

public class ChooseDateDialogFragment extends DialogFragment implements View.OnClickListener {
    private RecyclerView mDateRecyclerView;
    private ChooseDateRecyclerAdapter mChooseRecyclerAdapter;
    private ArrayList<ChooseDateBean> mData = new ArrayList<>();
    private int mStartPosition = -1, mEndPosition = -1;
    private OnChooseDateCompletedListener chooseDateCompletedListener;
    private TextView mTvDone;
    private TextView mTvCancel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zp_test", "onCreate")
        ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 去掉默认title
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 外部点击是否可以收起该dialog
        getDialog().setCanceledOnTouchOutside(false);

        Window window = this.getDialog().getWindow();
        if (window != null) {
            //去掉dialog默认的padding
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            // 设置高度为屏幕高度的四分之三
            lp.height = UIUtils.getScreenHeight(getActivity()) * 3 / 4;
            //设置dialog的位置在底部
            lp.gravity = Gravity.BOTTOM;
            //设置dialog的动画
            lp.windowAnimations = R.style.AnimBottom;
            window.setAttributes(lp);
            window.setBackgroundDrawable(new ColorDrawable());
        }

        View view = inflater.inflate(R.layout.dialog_choose_date, container, false);

        initView(view);
        initRecyclerView();
        initData();

        return view;
    }

    public void setChooseDateCompletedListener(OnChooseDateCompletedListener chooseDateCompletedListener) {
        this.chooseDateCompletedListener = chooseDateCompletedListener;
    }

    private void initView(View view) {
        mDateRecyclerView = (RecyclerView) view.findViewById(R.id.choose_date_recycler_view);
        mTvDone = view.findViewById(R.id.done);
        mTvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvCancel = view.findViewById(R.id.cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    private void initRecyclerView() {
        mChooseRecyclerAdapter = new ChooseDateRecyclerAdapter(getActivity(), mData);

        mChooseRecyclerAdapter.setDateCompleteListener(new ChooseDateRecyclerAdapter.DateCompleteListener() {
            @Override
            public void onSelectComplete(final String start, final String end, int startPosition, int endPosition) {
                mStartPosition = startPosition;
                mEndPosition = endPosition;

                if (chooseDateCompletedListener != null) {
                    chooseDateCompletedListener.onChooseDateCompleted(start, end, startPosition, endPosition);

                }

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getContext(), "s:" + start + "e:" + end, Toast.LENGTH_LONG).show();
//                        dismiss();
//                    }
//                }, 500);
            }
        });

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 这个item占有几个位置
                return (mChooseRecyclerAdapter.getItemViewType(position)
                        == ChooseDateRecyclerAdapter.TYPE_YEAR_MONTH ? layoutManager.getSpanCount() : 1);
            }
        });

        mDateRecyclerView.setAdapter(mChooseRecyclerAdapter);
        mDateRecyclerView.setLayoutManager(layoutManager);
        mDateRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initData() {

        if (mData != null && mData.size() <= 0) {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            for (int i = 0; i < 12; i++) {
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                mData.add(new ChooseDateBean(1, String.valueOf(calendar.get(Calendar.MONTH) + 1) + "月"));

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                for (int j = 1; j < dayOfWeek; j++) {
                    DateOfDayBean bean = new DateOfDayBean();
                    bean.setDayOfMonth("");
                    mData.add(new ChooseDateBean(2, bean));
                }

                int days = getDayCountByYearAndMonth(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1));
                for (int j = 0; j < days; j++) {
                    DateOfDayBean bean = new DateOfDayBean();
                    bean.setDayOfMonth(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                    bean.setCalendar(calendar);
//                    Log.e("zp_test", "day: " + calendar.get(Calendar.DAY_OF_MONTH) + " month: " + calendar.get(Calendar.MONTH));

                    if (i == 0 && calendar.get(Calendar.DAY_OF_MONTH) == currentDay) {
                        bean.setToday(true);
                        bean.setCanClick(true);
                    }

                    if (calendar.after(Calendar.getInstance())) {
                        bean.setCanClick(true);
                    }

                    mData.add(new ChooseDateBean(2, bean));

                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }

            }

        }


        if (mStartPosition != -1 && mEndPosition != -1) {
            mChooseRecyclerAdapter.setStartPosition(mStartPosition);
            mChooseRecyclerAdapter.setEndPosition(mEndPosition);
        } else {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.DAY_OF_MONTH, 7);
            int pos = getPositionByCalendar(calendar1);
            if (pos >= 0 && pos < mData.size() - 1) {
                mStartPosition = pos;
                mChooseRecyclerAdapter.setStartPosition(pos);
                mChooseRecyclerAdapter.setEndPosition(pos);
            }
        }


        mChooseRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mStartPosition != -1) {
            mDateRecyclerView.scrollToPosition(mStartPosition);
        }
    }

    /**
     * 计算指定年月的天数
     */
    private int getDayCountByYearAndMonth(int year, int month) {
        int days = 30;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
        }
        return days;
    }

    private boolean compareTowCalendarIsSameDay(Calendar calendar1, Calendar calendar2) {
        if (calendar1 == null || calendar2 == null) {
            return false;
        }

        return calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);

    }

    private int getPositionByCalendar(Calendar calendar) {
        if (calendar == null || mData.size() <= 0) {
            return -1;
        }

        for (int i = 0; i < mData.size(); i++) {
            Object object = mData.get(i).getViewContent();
            if (object != null && object instanceof DateOfDayBean) {
                DateOfDayBean dayBean = (DateOfDayBean) object;
                if (compareTowCalendarIsSameDay(calendar, dayBean.getCalendar())) {
                    return i;
                }
            }
        }

        return -1;

    }

    public interface OnChooseDateCompletedListener {
        void onChooseDateCompleted(String calendarStart, String calendarEnd, int start, int end);
    }

}

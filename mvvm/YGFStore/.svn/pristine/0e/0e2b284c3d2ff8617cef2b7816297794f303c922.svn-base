package com.neufmer.ygfstore.view.choosedateview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.neufmer.ygfstore.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by zangpeng on 2018/3/5.
 */

public class ChooseDateRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_YEAR_MONTH = 1;
    public static final int TYPE_DAY = 2;

    private LayoutInflater mInflater;
    private ArrayList<ChooseDateBean> mChooseDateBean;
    private Context context;
    private int mStartPosition = -1;
    private int mEndPosition = -1;
    private int whiteColor;
    private int black2Color;
    private int viewWidth;
    private DateCompleteListener mDateCompleteListener;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ChooseDateRecyclerAdapter(Context context, ArrayList<ChooseDateBean> mChooseDateBean) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mChooseDateBean = mChooseDateBean;
        init();
    }

    public ChooseDateRecyclerAdapter(ArrayList<ChooseDateBean> mChooseDateBean, Context context, int mStartPosition, int mEndPosition) {
        this.mChooseDateBean = mChooseDateBean;
        this.context = context;
        this.mStartPosition = mStartPosition;
        this.mEndPosition = mEndPosition;
        init();
    }

    private void init() {
        whiteColor = context.getResources().getColor(R.color.white_ffffff);
        black2Color = context.getResources().getColor(R.color.black_333333);
        // 之前放在onBindViewHolder中进行获取的，结果导致gpu渲染图上黑色条框很长
        viewWidth = UIUtils.getScreenWidth(context) / 7;
    }

    public void setDateCompleteListener(DateCompleteListener mDateCompleteListener) {
        this.mDateCompleteListener = mDateCompleteListener;
    }

    public void setStartPosition(int mStartPosition) {
        this.mStartPosition = mStartPosition;
    }

    public void setEndPosition(int mEndPosition) {
        this.mEndPosition = mEndPosition;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (TYPE_DAY == viewType) {
            holder = new DayViewHolder(mInflater.inflate(R.layout.item_recycler_date_day, parent, false));
        } else {
            holder = new YearAndMonthViewHolder(mInflater.inflate(R.layout.item_recycler_date_month_year, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChooseDateBean dateBean = mChooseDateBean.get(position);
        if (dateBean != null) {
            if (holder instanceof DayViewHolder) {
                DayViewHolder dayViewHolder = (DayViewHolder) holder;

                ViewGroup.LayoutParams paramsSelected = dayViewHolder.mLlSelectedView.getLayoutParams();
                paramsSelected.height = paramsSelected.width = viewWidth;
                dayViewHolder.mLlSelectedView.setLayoutParams(paramsSelected);

                if (dateBean.getViewContent() != null) {
                    setHolderDayData(dayViewHolder, dateBean.getViewContent(), position);
                }

            } else if (holder instanceof YearAndMonthViewHolder) {
                YearAndMonthViewHolder yearHolder = (YearAndMonthViewHolder) holder;
                if (dateBean.getViewContent() instanceof String) {
                    yearHolder.yearAndMonth.setText((String) dateBean.getViewContent());
                } else {
                    yearHolder.yearAndMonth.setText("");
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mChooseDateBean == null ? 0 : mChooseDateBean.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mChooseDateBean.get(position).getViewType();
    }

    private void setHolderDayData(DayViewHolder dayViewHolder, Object viewContent, int position) {
        if (viewContent instanceof DateOfDayBean) {
            DateOfDayBean dateOfDayBean = (DateOfDayBean) viewContent;

            dayViewHolder.mLine.setVisibility(View.INVISIBLE);
            dayViewHolder.mLeftRectangle.setVisibility(View.INVISIBLE);
            dayViewHolder.mRightRectangle.setVisibility(View.INVISIBLE);

            if (dayViewHolder.mTvDayOfWeek.getVisibility() == View.GONE) {
                dayViewHolder.mTvDayOfWeek.setVisibility(View.VISIBLE);
            }

            if (mStartPosition != -1 && mEndPosition == -1) {
                // 选了开始，没选结束
                if (mStartPosition == position) {
                    dayViewHolder.mLlSelectedView.setBackgroundResource(R.drawable.bg_dot_blue);
                    dayViewHolder.mTvBeginOrEnd.setVisibility(View.VISIBLE);
                    dayViewHolder.mTvDayOfWeek.setTextColor(whiteColor);
                    dayViewHolder.mTvBeginOrEnd.setText("开始");

                    if (dateOfDayBean.isToday()) {
                        dayViewHolder.mTvDayOfWeek.setText("今天");
                    } else {
                        dayViewHolder.mTvDayOfWeek.setText(dateOfDayBean.getDayOfMonth());
                    }

                } else {
                    dayViewHolder.mLlSelectedView.setBackgroundResource(R.drawable.bg_dot_white);
                    dayViewHolder.mTvBeginOrEnd.setVisibility(View.INVISIBLE);

                    handlerNormalCase(dateOfDayBean, dayViewHolder);
                }

            } else if (mStartPosition != -1) {
                // 选了开始与结束
                if (position >= mStartPosition && position <= mEndPosition) {
                    // 选中区域的样式
                    dayViewHolder.mLlSelectedView.setBackgroundResource(R.drawable.bg_dot_blue);
                    dayViewHolder.mTvHoliday.setVisibility(View.GONE);
                    dayViewHolder.mTvBeginOrEnd.setVisibility(View.VISIBLE);
                    dayViewHolder.mTvDayOfWeek.setTextColor(whiteColor);

                    if (mStartPosition == mEndPosition) {
                        dayViewHolder.mTvBeginOrEnd.setText("结束");
                        dayViewHolder.mTvHoliday.setText("开始");
                        dayViewHolder.mTvHoliday.setTextColor(whiteColor);
                        dayViewHolder.mTvHoliday.setVisibility(View.VISIBLE);
                        dayViewHolder.mTvDayOfWeek.setVisibility(View.GONE);
                    } else {
                        dayViewHolder.mTvDayOfWeek.setVisibility(View.VISIBLE);
                        if (position == mStartPosition) {
                            dayViewHolder.mLine.setVisibility(View.VISIBLE);
                            dayViewHolder.mRightRectangle.setVisibility(View.VISIBLE);
                            dayViewHolder.mTvBeginOrEnd.setText("开始");

                        } else if (position == mEndPosition) {
                            dayViewHolder.mLine.setVisibility(View.VISIBLE);
                            dayViewHolder.mLeftRectangle.setVisibility(View.VISIBLE);
                            dayViewHolder.mTvBeginOrEnd.setText("结束");

                        } else {
                            dayViewHolder.mLlSelectedView.setBackgroundResource(0);
                            dayViewHolder.mLine.setVisibility(View.VISIBLE);
                            dayViewHolder.mLeftRectangle.setVisibility(View.VISIBLE);
                            dayViewHolder.mRightRectangle.setVisibility(View.VISIBLE);


                            dayViewHolder.mTvBeginOrEnd.setVisibility(View.INVISIBLE);
                            dayViewHolder.mTvDayOfWeek.setTextColor(context.getResources().getColor(R.color.black_333333));

                        }

                        if (dateOfDayBean.isToday()) {
                            dayViewHolder.mTvDayOfWeek.setText("今天");
                        } else {
                            dayViewHolder.mTvDayOfWeek.setText(dateOfDayBean.getDayOfMonth());
                        }

                    }

                } else {
                    // 未选中按照原来逻辑进行
                    dayViewHolder.mLlSelectedView.setBackgroundResource(R.drawable.bg_dot_white);
                    dayViewHolder.mTvBeginOrEnd.setVisibility(View.INVISIBLE);
                    handlerNormalCase(dateOfDayBean, dayViewHolder);

                }

            } else {
                // 什么都没选
                handlerNormalCase(dateOfDayBean, dayViewHolder);
            }
        }
    }

    private void handlerNormalCase(DateOfDayBean dateOfDayBean, DayViewHolder dayViewHolder) {
        if (dateOfDayBean.isToday()) {
            dayViewHolder.mTvDayOfWeek.setText("今天");
            dayViewHolder.mTvDayOfWeek.setTextColor(context.getResources().getColor(R.color.main_blue_5584FF));
        } else {
            dayViewHolder.mTvDayOfWeek.setText(dateOfDayBean.getDayOfMonth());
            if (dateOfDayBean.isCanClick()) {
                dayViewHolder.mTvDayOfWeek.setTextColor(black2Color);

            } else {
                dayViewHolder.mTvDayOfWeek.setTextColor(context.getResources().getColor(R.color.color_999999));

            }
        }
    }

    class DayViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        LinearLayout mLlSelectedView;
        TextView mTvDayOfWeek;
        TextView mTvBeginOrEnd;
        TextView mTvHoliday;
        View mLine;
        View mLeftRectangle, mRightRectangle;

        DayViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mLlSelectedView = (LinearLayout) itemView.findViewById(R.id.choose_date_selected_ll);
            mTvDayOfWeek = (TextView) itemView.findViewById(R.id.day_of_month);
            mTvBeginOrEnd = (TextView) itemView.findViewById(R.id.day_begin_or_end);
            mLine = itemView.findViewById(R.id.center_line);
            mTvHoliday = (TextView) itemView.findViewById(R.id.day_holiday);
            mLeftRectangle = itemView.findViewById(R.id.left_rectangle);
            mRightRectangle = itemView.findViewById(R.id.right_rectangle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = getAdapterPosition();
                    if (clickPosition != RecyclerView.NO_POSITION) {
                        ChooseDateBean bean = mChooseDateBean.get(clickPosition);
                        if (bean == null || bean.getViewType() == TYPE_YEAR_MONTH) {
                            return;
                        }

                        if (bean.getViewContent() instanceof DateOfDayBean) {
                            DateOfDayBean dayBean = (DateOfDayBean) bean.getViewContent();
                            if (!dayBean.isCanClick()) {
                                return;
                            }

                            if (mStartPosition == -1 && mEndPosition == -1) {
                                mStartPosition = clickPosition;
                                notifyItemChanged(clickPosition);
                            } else if (mEndPosition == -1) {
                                if (clickPosition >= mStartPosition) {
                                    mEndPosition = clickPosition;
                                    notifyItemRangeChanged(mStartPosition, (mEndPosition - mStartPosition + 1));
                                    if (mDateCompleteListener != null) {
                                        ChooseDateBean dateBeanStart = mChooseDateBean.get(mStartPosition);
                                        ChooseDateBean dateBeanEnd = mChooseDateBean.get(mEndPosition);
                                        if (dateBeanStart.getViewContent() instanceof DateOfDayBean
                                                && dateBeanEnd.getViewContent() instanceof DateOfDayBean) {
                                            mDateCompleteListener.onSelectComplete(simpleDateFormat.format(((DateOfDayBean) dateBeanStart.getViewContent()).getCalendar().getTime())
                                                    , simpleDateFormat.format(((DateOfDayBean) dateBeanEnd.getViewContent()).getCalendar().getTime()), mStartPosition, mEndPosition);
                                        }
                                    }
                                } else {
                                    notifyItemChanged(mStartPosition);
                                    mStartPosition = clickPosition;
                                    mEndPosition = -1;
                                    notifyItemChanged(mStartPosition);
                                }
                            } else {
                                notifyItemRangeChanged(mStartPosition, (mEndPosition - mStartPosition + 1));

                                mStartPosition = clickPosition;
                                mEndPosition = -1;
                                notifyItemChanged(mStartPosition);

                            }


                        }
                    }
                }
            });
        }
    }

    class YearAndMonthViewHolder extends RecyclerView.ViewHolder {
        TextView yearAndMonth;

        YearAndMonthViewHolder(View itemView) {
            super(itemView);
            yearAndMonth = (TextView) itemView.findViewById(R.id.month_year);
        }
    }

    public interface DateCompleteListener {
        void onSelectComplete(String start, String end, int startPosition, int endPosition);
    }
}

package com.neufmer.ygfstore.binding.recyclerview;

import android.support.v7.widget.RecyclerView;

import com.neufmer.ygfstore.view.GridDividerItemDecoration;
import com.wangxing.code.mvvm.binding.viewadapter.recyclerview.LineManagers;
import com.wangxing.code.mvvm.utils.ConvertUtils;

/**
 * Created by WangXing on 2019/7/17.
 */
public class AppLineManagers extends LineManagers {

    public static LineManagerFactory gridDivider(final int firstAndLastColumnW, final int firstRowTopMargin) {
        return new LineManagerFactory() {
            @Override
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new GridDividerItemDecoration(recyclerView.getContext(), ConvertUtils.dp2px(firstAndLastColumnW), ConvertUtils.dp2px(firstRowTopMargin), ConvertUtils.dp2px(firstRowTopMargin));
            }
        };
    }




}

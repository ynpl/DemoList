package com.neufmer.ygfstore.binding.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by WangXing on 2019/7/17.
 */
public class AppLayoutManagers extends LayoutManagers {

    protected AppLayoutManagers() {
    }

    public static AppLayoutManagers.LayoutManagerFactory gridLayout(final int count) {

        return new AppLayoutManagers.LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), count);
            }
        };
    }

}

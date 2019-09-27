package com.neufmer.ygfstore.ui.search_history.keyword;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.wangxing.code.mvvm.base.ItemViewModel;

/**
 * Created by WangXing on 2019/6/26.
 */
public class SearchHistoryResultItemViewModel extends ItemViewModel<SearchHistoryKeywordViewModel> {

    public ObservableField<String> field = new ObservableField<>();

    public SearchHistoryResultItemViewModel(@NonNull SearchHistoryKeywordViewModel viewModel, String s) {
        super(viewModel);
        field.set(s);
    }
}

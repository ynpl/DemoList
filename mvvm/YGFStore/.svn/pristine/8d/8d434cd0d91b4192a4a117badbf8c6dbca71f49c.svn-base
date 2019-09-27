package com.neufmer.ygfstore.ui.search_history.keyword;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/6/26.
 */
public class SearchHistoryKeywordItemViewModel extends ItemViewModel<SearchHistoryKeywordViewModel> {

    public ObservableField<String> mData = new ObservableField<>();

    public SearchHistoryKeywordItemViewModel(@NonNull SearchHistoryKeywordViewModel viewModel, String data) {
        super(viewModel);
        mData.set(data);
    }

    /**
     * 设置搜索历史为keyword
     */
    public BindingCommand onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.keyWord.set(mData.get());
        }
    });

}

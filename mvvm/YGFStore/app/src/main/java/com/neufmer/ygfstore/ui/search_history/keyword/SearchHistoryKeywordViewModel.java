package com.neufmer.ygfstore.ui.search_history.keyword;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.neufmer.ygfstore.BR;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.utils.StringUtils;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by WangXing on 2019/6/25.
 */
public class SearchHistoryKeywordViewModel extends ToolbarViewModel {

    public ObservableField<String> keyWord = new ObservableField<>("");

    public ObservableInt searchHistory = new ObservableInt(View.VISIBLE);
    public ObservableInt searchResult = new ObservableInt(View.GONE);

    public ObservableInt clearText = new ObservableInt(View.GONE);

    public SingleLiveEvent<String> selectionUC = new SingleLiveEvent<>();

    /*****************************************搜索结果******************************************/
    public ObservableList<SearchHistoryResultItemViewModel> searchResultItems = new ObservableArrayList<>();

    public ItemBinding<SearchHistoryResultItemViewModel> searchResultItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_search_result);
    /*******************************************************************************************/

    /*****************************************搜索历史******************************************/
    public ObservableList<SearchHistoryKeywordItemViewModel> items = new ObservableArrayList<>();

    public ItemBinding<SearchHistoryKeywordItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_search_history);

    /*******************************************************************************************/

    public SearchHistoryKeywordViewModel(@NonNull Application application) {
        super(application);
        for (int i = 0; i < 10; i++) {
            items.add(new SearchHistoryKeywordItemViewModel(this, "张三" + i));
            items.add(new SearchHistoryKeywordItemViewModel(this, "李四" + i));
            items.add(new SearchHistoryKeywordItemViewModel(this, "合生汇店" + i));
        }

        for (int i = 0; i < 20; i++) {
            searchResultItems.add(new SearchHistoryResultItemViewModel(this, "张三" + i));
        }
    }

    public BindingCommand cancelClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


    public BindingCommand<String> textChange = new BindingCommand<>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            if (StringUtils.isEmpty(s)) {
                searchHistory.set(View.VISIBLE);
                searchResult.set(View.GONE);
                clearText.set(View.GONE);
            } else {
                searchHistory.set(View.GONE);
                searchResult.set(View.VISIBLE);
                selectionUC.setValue(s);
                clearText.set(View.VISIBLE);
            }
        }
    });

    public BindingCommand clearTextClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            keyWord.set("");
        }
    });

}

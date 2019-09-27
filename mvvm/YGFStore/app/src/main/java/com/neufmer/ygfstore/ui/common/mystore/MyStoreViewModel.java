package com.neufmer.ygfstore.ui.common.mystore;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.StoresBean;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.neufmer.ygfstore.view.uichange.MultiStatusLayoutUIChangeObservable;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by WangXing on 2019/6/19.
 */
public class MyStoreViewModel extends ToolbarViewModel<TaskModel> {

    public ObservableList<MyStoreItemViewModel> itemList = new ObservableArrayList<>();
    public ItemBinding<MyStoreItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_mine_my_store_list);

    public MultiStatusLayoutUIChangeObservable statusLayoutUC = new MultiStatusLayoutUIChangeObservable();

    public MyStoreViewModel(@NonNull Application application) {
        super(application);

    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.my_store_activity_title);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        request(model.getStores(Const.header(), "", "", "", ""), new ApiCallBack<List<StoresBean>>() {

            @Override
            protected void onSuccess(List<StoresBean> storesBeans, String message) {
                for (StoresBean storesBean : storesBeans) {
                    MyStoreItemViewModel taskItemViewModel = new MyStoreItemViewModel(MyStoreViewModel.this, storesBean);
                    itemList.add(taskItemViewModel);
                }
                statusLayoutUC.showContent.call();

            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                if (exception.handleCode(Const.errorCode())) {
                    exception.toast();
                }
                statusLayoutUC.showEmpty.call();
            }
        });
    }
}

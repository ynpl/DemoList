package com.neufmer.ygfstore.ui.view_picture;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.BR;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by WangXing on 2019/7/17.
 */
public class ViewPictureViewModel extends ToolbarViewModel {

    public SingleLiveEvent<Void> addPictureUC = new SingleLiveEvent<>();

    public ObservableList<MultiItemViewModel> items = new ObservableArrayList<>();

    public ItemBinding<MultiItemViewModel> binding = ItemBinding.of(new OnItemBind<MultiItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MultiItemViewModel item) {
            if (position == items.size() - 1) {
                itemBinding.set(BR.viewModel, R.layout.item_view_picture_footer);
            } else {
                itemBinding.set(BR.viewModel, R.layout.item_view_picture);
            }
        }
    });

    public ViewPictureViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.view_picture_activity_title);
    }

    protected void setImages(ArrayList<String> list) {

        for (String s : list) {
                items.add(new ViewPictureItemViewModel(ViewPictureViewModel.this, s));
        }
        items.add(new ViewPictureFooterItemViewModel(ViewPictureViewModel.this));
    }

}

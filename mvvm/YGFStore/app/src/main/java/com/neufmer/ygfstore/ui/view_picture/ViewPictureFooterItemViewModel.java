package com.neufmer.ygfstore.ui.view_picture;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/7/17.
 */
public class ViewPictureFooterItemViewModel extends MultiItemViewModel<ViewPictureViewModel> {

    public ViewPictureFooterItemViewModel(@NonNull ViewPictureViewModel viewModel) {
        super(viewModel);
    }

    public BindingCommand addPicture = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.addPictureUC.call();
        }
    });

}

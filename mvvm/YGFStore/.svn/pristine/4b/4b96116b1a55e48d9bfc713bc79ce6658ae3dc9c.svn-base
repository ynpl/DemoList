package com.neufmer.ygfstore.ui.view_picture;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.photoview.PhotoViewAttacher;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.base.MultiItemViewModel;

/**
 * Created by WangXing on 2019/7/17.
 */
public class ViewPictureItemViewModel extends MultiItemViewModel<ViewPictureViewModel> {


    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableInt roundSize = new ObservableInt(4);

    public ViewPictureItemViewModel(@NonNull ViewPictureViewModel viewModel, String s) {
        super(viewModel);
        imageUrl.set(s);
        //预览图片方法
//        PictureSelector.create(PhotoFragment.this).themeStyle(themeId).openExternalPreview(position, selectList);
    }
}

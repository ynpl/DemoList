package com.neufmer.ygfstore.ui.task_detail.multiitem;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.event.param.PictureParam;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.neufmer.ygfstore.ui.view_picture.ViewPictureActivity;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.KLog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WangXing on 2019/7/9.
 * 图片itemViewModel
 */
public class ImageItemViewModel extends ParentMultiItemViewModel {

    public InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean mData;

    public ObservableInt imagePreview = new ObservableInt(View.GONE);
    public ObservableInt description = new ObservableInt(View.VISIBLE);
    public ObservableInt roundSize = new ObservableInt(4);
    public ObservableInt isNeedAttachImage = new ObservableInt(View.GONE);

    public ImageItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean data) {
        super(viewModel,data);
        mData = data;
        if (mData.isIsNeedAttachImage()) {
            isNeedAttachImage.set(View.VISIBLE);
        } else {
            isNeedAttachImage.set(View.GONE);
        }
        visibilityUI();

    }

    public BindingCommand getPicture = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.getPictureUC.setValue(ImageItemViewModel.this);
        }
    });

    public BindingCommand viewPicUC = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            PictureParam param = new PictureParam();
            param.setId(mData.getId());
            param.setImageList(new ArrayList<>(imagePaths));
            bundle.putParcelable(KEY_PIC_PATH, param);
            viewModel.startActivity(ViewPictureActivity.class, bundle);
        }
    });



    private void visibilityUI() {
        imagePaths.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<String>>() {
            @Override
            public void onChanged(ObservableList<String> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<String> sender, int positionStart, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    description.set(View.VISIBLE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    description.set(View.GONE);
                    isNeedAttachImage.set(View.GONE);
                }
            }

            @Override
            public void onItemRangeInserted(ObservableList<String> sender, int positionStart, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    description.set(View.VISIBLE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    description.set(View.GONE);
                    isNeedAttachImage.set(View.GONE);
                }
            }

            @Override
            public void onItemRangeMoved(ObservableList<String> sender, int fromPosition, int toPosition, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    description.set(View.VISIBLE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    description.set(View.GONE);
                    isNeedAttachImage.set(View.GONE);
                }
            }

            @Override
            public void onItemRangeRemoved(ObservableList<String> sender, int positionStart, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    description.set(View.VISIBLE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    description.set(View.GONE);
                    isNeedAttachImage.set(View.GONE);
                }
            }
        });

    }

}

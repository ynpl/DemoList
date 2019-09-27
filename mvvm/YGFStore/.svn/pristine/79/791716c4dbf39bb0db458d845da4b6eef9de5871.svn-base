package com.neufmer.ygfstore.ui.task_detail.multiitem;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.event.param.PictureParam;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.neufmer.ygfstore.ui.view_picture.ViewPictureActivity;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.utils.ContextUtils;

import java.util.ArrayList;

/**
 * Created by WangXing on 2019/7/9.
 * 填空itemViewModel
 */
public class FillBlanksItemViewModel extends ParentMultiItemViewModel {

    public InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean mData;

    public ObservableBoolean visibilityStyle = new ObservableBoolean();

    public ObservableInt inputType = new ObservableInt(InputType.TYPE_CLASS_TEXT);

    public ObservableInt imagePreview = new ObservableInt(View.GONE);
    public ObservableInt roundSize = new ObservableInt(4);
    public ObservableInt isNeedAttachImage = new ObservableInt(View.GONE);
    public ObservableField<Drawable> observableField = new ObservableField<>(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_time));


    public FillBlanksItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean data) {
        super(viewModel, data);
        mData = data;
        InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.StyleBean styleBean = mData.getQuestion().getStyle().get(0);
        if (styleBean.isIsDate() || styleBean.isIsTime()) {
            if (styleBean.isIsTime()) {
                observableField.set(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_time));
            } else if (styleBean.isIsDate()) {
                observableField.set(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_calender));
            }
            visibilityStyle.set(true);
        } else {
            visibilityStyle.set(false);
        }
        if (!styleBean.getDataType().equals("char")) {
            inputType.set(InputType.TYPE_CLASS_NUMBER);
        }

        visibilityUI();


    }

    public BindingCommand getPicture = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.getPictureUC.setValue(FillBlanksItemViewModel.this);
        }
    });

    public BindingCommand selectTime = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.selectTimeUC.setValue(FillBlanksItemViewModel.this);
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
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    isNeedAttachImage.set(View.GONE);
                }
            }

            @Override
            public void onItemRangeInserted(ObservableList<String> sender, int positionStart, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    isNeedAttachImage.set(View.GONE);
                }
            }

            @Override
            public void onItemRangeMoved(ObservableList<String> sender, int fromPosition, int toPosition, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    isNeedAttachImage.set(View.GONE);
                }
            }

            @Override
            public void onItemRangeRemoved(ObservableList<String> sender, int positionStart, int itemCount) {
                if (sender.isEmpty()) {
                    imagePreview.set(View.GONE);
                    isNeedAttachImage.set(View.VISIBLE);
                } else {
                    imagePreview.set(View.VISIBLE);
                    isNeedAttachImage.set(View.GONE);
                }
            }
        });

    }

}

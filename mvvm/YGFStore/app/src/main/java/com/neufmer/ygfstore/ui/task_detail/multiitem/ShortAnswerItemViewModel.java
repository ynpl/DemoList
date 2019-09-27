package com.neufmer.ygfstore.ui.task_detail.multiitem;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.ContextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WangXing on 2019/7/9.
 * 简答itemViewModel
 */
public class ShortAnswerItemViewModel extends ParentMultiItemViewModel {

    public InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean mData;

    public ShortAnswerItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean data) {
        super(viewModel,data);
        mData = data;

    }

    public BindingCommand<EditText> editTextBinding = new BindingCommand<>(new BindingConsumer<EditText>() {
        @Override
        public void call(EditText editText) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) editText.getLayoutParams();
            if (mData.isNeedMultirow()) {
                layoutParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, ContextUtils.getContext().getResources().getDisplayMetrics()));
                editText.setSingleLine(false);
            } else {
                layoutParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, ContextUtils.getContext().getResources().getDisplayMetrics()));
                editText.setSingleLine(true);
            }
            editText.setLayoutParams(layoutParams);
        }
    });




}

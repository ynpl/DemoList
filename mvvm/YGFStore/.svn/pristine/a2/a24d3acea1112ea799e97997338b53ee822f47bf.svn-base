package com.neufmer.ygfstore.ui.task_detail.multiitem;

import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.event.param.PictureParam;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.neufmer.ygfstore.ui.view_picture.ViewPictureActivity;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.utils.ContextUtils;

import java.util.ArrayList;

/**
 * Created by WangXing on 2019/7/9.
 * 多选itemViewModel
 */
public class MultipleSelectionItemViewModel extends ParentMultiItemViewModel {

    public InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean mData;

    public ObservableInt imagePreview = new ObservableInt(View.GONE);
    public ObservableInt roundSize = new ObservableInt(4);
    public ObservableInt isNeedAttachImage = new ObservableInt(View.GONE);

    public MultipleSelectionItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean data) {
        super(viewModel, data);
        mData = data;
        if (mData.isIsNeedAttachImage()) {
            isNeedAttachImage.set(View.VISIBLE);
        } else {
            isNeedAttachImage.set(View.GONE);
        }
        visibilityUI();
    }

    public BindingCommand<LinearLayout> addCheckBox = new BindingCommand<>(new BindingConsumer<LinearLayout>() {
        @Override
        public void call(LinearLayout linearLayout) {
            linearLayout.removeAllViews();
            for (int i = 0; i < mData.getQuestion().getOptions().size(); i++) {
                InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.OptionsBean option = mData.getQuestion().getOptions().get(i);
                CheckBox box = new CheckBox(linearLayout.getContext());
                box.setLayoutParams(new RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT));
                box.setButtonDrawable(R.drawable.btn_common_check);
                box.setTextColor(ContextCompat.getColor(ContextUtils.getContext(), R.color.grey_666666));
                box.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                box.setText(option.getOption());
                box.setId(i);
                box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        String id = String.valueOf(buttonView.getId());
                        if (isChecked) {
                            if (!answer.contains(id)) {
                                answer.add(id);
                            }
                            InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.OptionsBean optionsBean = mData.getQuestion().getOptions().get(Integer.parseInt(id));
                            //选中该选项时是否需要弹出自定义原因
                            if (optionsBean.isIsNeedChoiceFailReason()) {
                                viewModel.choiceFailreasonsUC.setValue(MultipleSelectionItemViewModel.this);
                            }
                        } else {
                            if (answer.contains(id)) {
                                answer.remove(answer.indexOf(id));
                            }
                        }
                    }
                });
                linearLayout.addView(box);
                if (!answer.isEmpty()) {
                    if (answer.contains(String.valueOf(i))) {
                        box.setChecked(true);
                    }
                }
            }
        }
    });

    public BindingCommand getPicture = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.getPictureUC.setValue(MultipleSelectionItemViewModel.this);
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

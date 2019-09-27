package com.neufmer.ygfstore.ui.task_detail.multiitem;

import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
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
import java.util.List;

/**
 * Created by WangXing on 2019/7/9.
 * 单选itemViewModel
 */
public class SingleElectionItemViewModel extends ParentMultiItemViewModel {


    public InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean mData;
    public ObservableInt imagePreview = new ObservableInt(View.GONE);
    public ObservableInt roundSize = new ObservableInt(4);
    public ObservableInt isNeedAttachImage = new ObservableInt(View.GONE);

    public SingleElectionItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean data) {
        super(viewModel, data);
        mData = data;
        if (mData.isIsNeedAttachImage()) {
            isNeedAttachImage.set(View.VISIBLE);
        } else {
            isNeedAttachImage.set(View.GONE);
        }
        visibilityUI();

    }

    public BindingCommand<RadioGroup> addRadioButton = new BindingCommand<>(new BindingConsumer<RadioGroup>() {
        @Override
        public void call(final RadioGroup radioGroup) {
            //监听绘图
//            ViewTreeObserver vto = radioGroup.getViewTreeObserver();
//            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    radioGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    KLog.e(radioGroup.getHeight());
//                    KLog.e(radioGroup.getWidth());
//                }
//            });
            radioGroup.removeAllViews();
            List<InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.StyleBean> style = mData.getQuestion().getStyle();
            for (int i = 0; i < mData.getQuestion().getOptions().size(); i++) {
                InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.OptionsBean option = mData.getQuestion().getOptions().get(i);
                RadioButton button = new RadioButton(radioGroup.getContext());
                button.setLayoutParams(new RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT));
                button.setButtonDrawable(R.drawable.btn_common_check);
                button.setTextColor(ContextCompat.getColor(ContextUtils.getContext(), R.color.grey_666666));
                button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                button.setText(option.getOption());
                button.setId(i);
                radioGroup.addView(button);
                if (answer.contains(String.valueOf(i))) {
                    button.setChecked(true);
                }
            }


        }
    });


    public BindingCommand<RadioButton> onRadioGroupChangedCommand = new BindingCommand<>(new BindingConsumer<RadioButton>() {
        @Override
        public void call(RadioButton button) {
            String id = String.valueOf(button.getId());
            answer.clear();
            answer.add(id);
            InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.OptionsBean optionsBean = mData.getQuestion().getOptions().get(button.getId());
            //选中该选项时是否需要弹出自定义原因
            if (optionsBean.isIsNeedChoiceFailReason()) {
                viewModel.choiceFailreasonsUC.setValue(SingleElectionItemViewModel.this);
            }

        }
    });

    public BindingCommand getPicture = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.getPictureUC.setValue(SingleElectionItemViewModel.this);
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

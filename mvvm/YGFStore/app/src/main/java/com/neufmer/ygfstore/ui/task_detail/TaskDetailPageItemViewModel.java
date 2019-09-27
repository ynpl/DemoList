package com.neufmer.ygfstore.ui.task_detail;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.ui.task_detail.multiitem.FillBlanksItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.ImageItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.MultipleSelectionItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.ShortAnswerItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.SingleElectionItemViewModel;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.base.MultiItemViewModel;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by WangXing on 2019/7/9.
 */
public class TaskDetailPageItemViewModel extends ItemViewModel<TaskDetailViewModel> {

    public InspectionsheetResultBean.InspectionQuestionsBean mData;

    public ObservableList<MultiItemViewModel> items = new ObservableArrayList<>();
    public ItemBinding<MultiItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<MultiItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MultiItemViewModel item) {
            //通过item的类型, 动态设置Item加载的布局
            String itemType = (String) item.getItemType();
            if (itemType.equals(Const.SINGLECHOICE)) {
                itemBinding.set(BR.viewModel, R.layout.item_single_election);
            } else if (itemType.equals(Const.MULTIPLECHOICE)) {
                itemBinding.set(BR.viewModel, R.layout.item_multiple_selection);
            } else if (itemType.equals(Const.FILLINBLANK)) {
                itemBinding.set(BR.viewModel, R.layout.item_fill_blanks);
            } else if (itemType.equals(Const.IMAGE)) {
                itemBinding.set(BR.viewModel, R.layout.item_image);
            } else {
                itemBinding.set(BR.viewModel, R.layout.item_short_answer);
            }

        }
    });

    public TaskDetailPageItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean data) {
        super(viewModel);
        mData = data;

        MultiItemViewModel itemViewModel = null;
        for (InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean bean : mData.getDetails()) {
            String itemType = bean.getType();
            if (itemType.equals(Const.SINGLECHOICE)) {
                itemViewModel = new SingleElectionItemViewModel(viewModel, bean);
            } else if (itemType.equals(Const.MULTIPLECHOICE)) {
                itemViewModel = new MultipleSelectionItemViewModel(viewModel, bean);
            } else if (itemType.equals(Const.FILLINBLANK)) {
                itemViewModel = new FillBlanksItemViewModel(viewModel, bean);
            } else if (itemType.equals(Const.IMAGE)) {
                itemViewModel = new ImageItemViewModel(viewModel, bean);
            } else {
                itemViewModel = new ShortAnswerItemViewModel(viewModel, bean);
            }
            itemViewModel.multiItemType(itemType);
            items.add(itemViewModel);
        }

    }
}

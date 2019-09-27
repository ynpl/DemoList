package com.neufmer.ygfstore.ui.addtask;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityAddTaskBinding;
import com.neufmer.ygfstore.view.choosedateview.ChooseDateDialogFragment;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.view.utils.VMDialogUtil;

public class AddTaskActivity extends BaseActivity<ActivityAddTaskBinding, AddTaskViewModel> implements ChooseDateDialogFragment.OnChooseDateCompletedListener {

    //店铺dialog
    private Dialog storeDialog;
    //人员dialog
    private Dialog personnelDialog;
    //日期dialog
    private ChooseDateDialogFragment chooseDateDialogFragment;
    //巡查规则
    private Dialog surfaceDialog;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_add_task;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setStatusBar(this, false, false);
        setSupportActionBar(binding.addTaskInclude.toolbar);
        viewModel.initToolbar();
        chooseDateDialogFragment = new ChooseDateDialogFragment();
        chooseDateDialogFragment.setChooseDateCompletedListener(this);
        personnelDialog = new VMDialogUtil.Builder(this)
                .setLayoutId(R.layout.dialog_choose_personnel_list)
                .setWindowAnimations(R.style.AnimBottom)
                .setViewModelId(BR.viewModel)
                .setViewModel(viewModel)
                .build();

        storeDialog = new VMDialogUtil.Builder(this)
                .setLayoutId(R.layout.dialog_choose_store_list)
                .setWindowAnimations(R.style.AnimBottom)
                .setViewModelId(BR.viewModel)
                .setViewModel(viewModel)
                .build();

        EditText searchET = storeDialog.findViewById(R.id.view_header_search);
        searchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //调用查找店铺接口
                    if (!StringUtils.isTrimEmpty(v.getText().toString())) {
                        viewModel.getStores();
                    }
                    return true;
                }
                return false;
            }
        });

        surfaceDialog = new VMDialogUtil.Builder(this)
                .setLayoutId(R.layout.dialog_choose_surface)
                .setWindowAnimations(R.style.AnimBottom)
                .setViewModelId(BR.viewModel)
                .setViewModel(viewModel)
                .build();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        //显示选择店铺dialog
        viewModel.showOwnerDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void o) {
                if (!storeDialog.isShowing()) {
                    storeDialog.show();
                }
            }
        });
        //关闭选择店铺dialog
        viewModel.dismissOwnerDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void o) {
                if (storeDialog.isShowing()) {
                    storeDialog.dismiss();
                }
            }
        });
        //显示选择人员dialog
        viewModel.showPersonnelDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (!personnelDialog.isShowing()) {
                    personnelDialog.show();
                }
            }
        });
        //关闭巡查人员dialog
        viewModel.dismissPersonnelDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (personnelDialog.isShowing()) {
                    personnelDialog.dismiss();
                }
            }
        });
        //显示选择时间dialog
        viewModel.showTimeDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {

                chooseDateDialogFragment.showNow(getSupportFragmentManager(), "");
            }
        });
        //显示巡查规则dialog
        viewModel.showSurfaceDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (!surfaceDialog.isShowing()) {
                    surfaceDialog.show();
                }
            }
        });

        //关闭巡查规则dialog
        viewModel.dismissSurfaceDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (surfaceDialog.isShowing()) {
                    surfaceDialog.dismiss();
                }
            }
        });

        //完成选择巡查规则dialog
        viewModel.completeSurfaceDialogUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (surfaceDialog.isShowing()) {
                    surfaceDialog.dismiss();
                }
            }
        });
    }

    /**
     * 设置开始时间和结束时间
     *
     * @param calendarStart
     * @param calendarEnd
     * @param start
     * @param end
     */
    @Override
    public void onChooseDateCompleted(String calendarStart, String calendarEnd, int start, int end) {
        viewModel.selectedDate.set(calendarStart + "~" + calendarEnd);
        viewModel.param.setStartDate(calendarStart);
        viewModel.param.setFinishDate(calendarEnd);
    }

    @Override
    public void onBackPressed() {
        final MaterialDialog dialog = new MaterialDialog.Builder(AddTaskActivity.this).customView(R.layout.dialog_give_up_task_tip, false).show();
        View view = dialog.getCustomView();
        assert view != null;
        view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

    }
}

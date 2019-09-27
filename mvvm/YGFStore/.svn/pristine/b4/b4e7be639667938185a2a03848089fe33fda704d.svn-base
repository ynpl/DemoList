package com.neufmer.ygfstore.ui.addtask;

import android.annotation.SuppressLint;
import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.params.BatchCreateParam;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.GroupsUsersBean;
import com.neufmer.ygfstore.bean.InspectionsheetsBean;
import com.neufmer.ygfstore.bean.StoresBean;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.GsonUtil;
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import okhttp3.RequestBody;

@SuppressLint("SimpleDateFormat")
public class AddTaskViewModel extends ToolbarViewModel<TaskModel> {

    public BatchCreateParam param = new BatchCreateParam();
    private List<Integer> mTargetStores = new ArrayList<>();//目标店铺id
    private List<Integer> mAssignees = new ArrayList<>();//巡查人员id
    private int mInspectionSheet;

    public ObservableField<String> stores = new ObservableField<>("");
    public ObservableField<String> storesId = new ObservableField<>("");

    public ObservableField<String> personnel = new ObservableField<>("");

    public ObservableField<String> selectedDate = new ObservableField<>("");

    public ObservableField<String> count = new ObservableField<String>("1");

    public ObservableField<String> surface = new ObservableField<String>("");

    /****************************************巡查店铺***************************************/

    public ObservableList<AddTaskStoreItemViewModel> items = new ObservableArrayList<>();
    public ItemBinding<AddTaskStoreItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_choose_store_owner_list);
    public ObservableField<String> keyWord = new ObservableField<>("");
    public SingleLiveEvent<Void> showOwnerDialogUC = new SingleLiveEvent<Void>();
    public SingleLiveEvent<Void> dismissOwnerDialogUC = new SingleLiveEvent<Void>();

    /****************************************巡查人员***************************************/

    public ObservableList<AddTaskPersonnelItemViewModel> personnelItems = new ObservableArrayList<>();
    public ItemBinding<AddTaskPersonnelItemViewModel> personnelItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_choose_personnel_group);
    public SingleLiveEvent<Void> showPersonnelDialogUC = new SingleLiveEvent<Void>();
    public SingleLiveEvent<Void> dismissPersonnelDialogUC = new SingleLiveEvent<Void>();

    /****************************************巡查时间***************************************/

    public SingleLiveEvent<Void> showTimeDialogUC = new SingleLiveEvent<Void>();

    /****************************************巡查规则***************************************/

    public ObservableList<AddTaskSurfaceItemViewModel> surfaceItems = new ObservableArrayList<>();
    public ItemBinding<AddTaskSurfaceItemViewModel> surfaceItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_choose_surface);
    public SingleLiveEvent<Void> showSurfaceDialogUC = new SingleLiveEvent<Void>();
    public SingleLiveEvent<Void> dismissSurfaceDialogUC = new SingleLiveEvent<Void>();
    public SingleLiveEvent<Void> completeSurfaceDialogUC = new SingleLiveEvent<Void>();


    public AddTaskViewModel(@NonNull Application application) {
        super(application);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh");
        selectedDate.set(format.format(new Date()) + "～" + format.format(new Date()));
        mAssignees.add(Integer.valueOf(CacheInfoManager.getInstance().getUserId()));
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.btn_add_new_task);
        setLeftCloseStyle();
        setRightTextVisible(View.VISIBLE);
        setRightHasSelect(false);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        getGroupUsers();
        getInspectionsheets();
    }


    /**
     * toolbar右边文字点击事件
     */
    @Override
    protected void rightTextOnClick() {
        super.rightTextOnClick();
//        ToastUtils.showShort("完成");
        postBatchCreate();
    }

    /**
     * 显示店铺信息dialog
     */
    public BindingCommand showOwnerDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getStores();
        }
    });

    /**
     * 关闭店铺信息dialog
     */
    public BindingCommand dismissOwnerDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            dismissOwnerDialogUC.call();
        }
    });

    /**
     * 显示巡查人员dialog
     */
    public BindingCommand showPersonnelDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showPersonnelDialogUC.call();
        }
    });

    /**
     * 关闭巡查人员dialog
     */
    public BindingCommand dismissPersonnelDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            dismissPersonnelDialogUC.call();
        }
    });

    /**
     * 选择完成之后关闭巡查人员dialog
     */
    public BindingCommand completePersonnelDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            StringBuilder personnelData = new StringBuilder();
            for (AddTaskPersonnelItemViewModel personnelItem : personnelItems) {
                if (personnelItems.indexOf(personnelItem) != 0) {
                    personnelData.append("\t");
                }
                for (int i = 0; i < personnelItem.items.size(); i++) {
                    AddTaskPersonnelGroupItemViewModel item = personnelItem.items.get(i);
                    if (item.itemSelect.get()) {
                        mAssignees.add(item.itemData.getId());
                        String s = item.itemData.getName();
                        if (i == items.size() - 1) {
                            personnelData.append(s);
                        } else {
                            personnelData.append(s).append("\t");
                        }
                    }
                }
            }
            personnel.set(personnelData.toString().equals("\t") ? "" : personnelData.toString());
            dismissPersonnelDialogUC.call();
        }
    });


    /**
     * 显示巡查时间dialog
     */
    public BindingCommand showTimeDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showTimeDialogUC.call();
        }
    });

    /**
     * 显示巡查规则dialog
     */
    public BindingCommand showSurfaceDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showSurfaceDialogUC.call();
        }
    });

    /**
     * 关闭巡查规则dialog
     */
    public BindingCommand dismissSurfaceDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            dismissSurfaceDialogUC.call();
        }
    });

    /**
     * 完成选择巡查规则dialog
     */
    public BindingCommand completeSurfaceDialog = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            for (AddTaskSurfaceItemViewModel item : surfaceItems) {
                if (item.selectFlag.get()) {
                    surface.set(item.mData.getName());
                    mInspectionSheet = item.mData.getId();
                }
            }
            completeSurfaceDialogUC.call();
        }
    });

    /**
     * 店铺信息选择完成
     * 设置ui显示样式
     */
    public BindingCommand selectCompleteClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            keyWord.set("");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                AddTaskStoreItemViewModel item = items.get(i);
                //被选择的子项
                if (item.selectFlag.get()) {
                    mTargetStores.add(item.mData.getId());
                    //获取子项数据
                    String viewStr = item.mData.getLegalRepresentative() + " " + item.mData.getCode();
                    if (i == items.size() - 1) {
                        builder.append(viewStr);
                    } else {
                        builder.append(viewStr).append("\n");
                    }
                }
            }
            dismissOwnerDialogUC.call();
            stores.set(builder.toString());
        }
    });

    public BindingCommand<String> textChanged = new BindingCommand<>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            if (!StringUtils.isEmpty(stores.get()) &&
                    !StringUtils.isEmpty(personnel.get()) &&
                    !StringUtils.isEmpty(selectedDate.get()) &&
                    !StringUtils.isEmpty(surface.get()) &&
                    !StringUtils.isEmpty(s)) {
                setRightHasSelect(true);
            } else {
                setRightHasSelect(false);
            }
        }
    });

    /**
     * 判断右上角完成是否可以点击
     */
    public BindingCommand<TextView> patrolCount = new BindingCommand<>(new BindingConsumer<TextView>() {
        @Override
        public void call(TextView v) {
            v.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int counts) {
                    if (!StringUtils.isEmpty(stores.get()) &&
                            !StringUtils.isEmpty(personnel.get()) &&
                            !StringUtils.isEmpty(selectedDate.get()) &&
                            !StringUtils.isEmpty(count.get()) &&
                            !StringUtils.isEmpty(s)) {
                        setRightHasSelect(true);
                    } else {
                        setRightHasSelect(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    });

    /**
     * 获取门店信息
     */
    public void getStores() {
        request(model.getStores(Const.header(), "", "", GsonUtil.GsonString(mAssignees), keyWord.get()), new ApiCallBack<List<StoresBean>>() {

            @Override
            protected void onSuccess(List<StoresBean> storesBeans, String message) {
                if (!items.isEmpty()) {
                    items.clear();
                }
                for (StoresBean bean : storesBeans) {
                    items.add(new AddTaskStoreItemViewModel(AddTaskViewModel.this, bean));
                }
                showOwnerDialogUC.call();
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                if (exception.handleCode(Const.errorCode())) {
                    exception.toast();
                }
            }
        }, true);
    }

    /**
     * 获取分组数据
     */
    private void getGroupUsers() {
        request(model.getGroupsUsers(Const.header()), new ApiCallBack<List<GroupsUsersBean>>() {

            @Override
            protected void onSuccess(List<GroupsUsersBean> groupsUsersBeans, String message) {
                for (GroupsUsersBean groupsUsersBean : groupsUsersBeans) {
                    for (GroupsUsersBean.UsersBean user : groupsUsersBean.getUsers()) {
                        if (CacheInfoManager.getInstance().getUserId().equals(String.valueOf(user.getId()))) {
                            personnel.set(user.getName());
                        }
                    }
                    personnelItems.add(new AddTaskPersonnelItemViewModel(AddTaskViewModel.this, groupsUsersBean));
                }
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {

            }
        });
    }

    /**
     * 获取巡查表
     */
    private void getInspectionsheets() {
        request(model.getInspectionsheets(Const.header()), new ApiCallBack<List<InspectionsheetsBean>>() {

            @Override
            protected void onSuccess(List<InspectionsheetsBean> inspectionsheetsBeans, String message) {
                for (InspectionsheetsBean inspectionsheetsBean : inspectionsheetsBeans) {
                    surfaceItems.add(new AddTaskSurfaceItemViewModel(AddTaskViewModel.this, inspectionsheetsBean));
                }
                surfaceItems.get(0).selectFlag.set(true);
                mInspectionSheet = surfaceItems.get(0).mData.getId();
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {

            }
        });
    }

    /**
     * 上传新建任务
     */
    private void postBatchCreate() {
        param.setInspectionSheet(String.valueOf(mInspectionSheet));
        param.setTargetCompleteTimes(Integer.parseInt(Objects.requireNonNull(count.get())));
        param.setTargetStores(mTargetStores);
        param.setAssignees(mAssignees);
        param.getBody();
        RequestBody body = param.getBody();
        request(model.postBatchcreate(Const.header(), body), new ApiCallBack<Void>() {

            @Override
            protected void onSuccess(Void aVoid, String message) {
                ToastUtils.showShort(R.string.toast_content_4);
                finish();
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {

            }
        }, true);
    }

}

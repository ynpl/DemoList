package com.neufmer.ygfstore.ui.task_detail;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.TaskModel;
import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.ContentParamDao;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.neufmer.ygfstore.ui.signature.patrol.PatrolSignatureActivity;
import com.neufmer.ygfstore.ui.task_detail.failreasonsitem.TaskDetailFailreasonsChildsItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.failreasonsitem.TaskDetailFailreasonsGroupItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.FillBlanksItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.ParentMultiItemViewModel;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.utils.GsonUtil;
import com.wangxing.code.mvvm.utils.KLog;
import com.wangxing.code.mvvm.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by WangXing on 2019/6/26.
 */
public class TaskDetailViewModel extends ToolbarViewModel<TaskModel> {

    public int mTaskId;

    //recyclerview
    public ObservableList<TaskDetailItemViewModel> items = new ObservableArrayList<>();
    public ItemBinding<TaskDetailItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_task_detail);

    //viewpager
    public ObservableList<TaskDetailPageItemViewModel> pageItems = new ObservableArrayList<>();
    public ItemBinding<TaskDetailPageItemViewModel> pageItemBinding = ItemBinding.of(BR.viewModel, R.layout.fragment_task_layout);

    //failreasons Group
    public ObservableList<TaskDetailFailreasonsGroupItemViewModel> failreasonsGroupItems = new ObservableArrayList<>();
    public ItemBinding<TaskDetailFailreasonsGroupItemViewModel> failreasonsGroupItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_failreasons_group);

    //failreasons Item
    public ObservableList<TaskDetailFailreasonsChildsItemViewModel> failreasonsChildsItems = new ObservableArrayList<>();
    public ItemBinding<TaskDetailFailreasonsChildsItemViewModel> failreasonsChildsItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_failreasons_childs);

    //切换巡查信息页面
    public SingleLiveEvent<Integer> switchPagerUC = new SingleLiveEvent<>();
    public SingleLiveEvent<Integer> offscreenPageLimitUC = new SingleLiveEvent<>();

    //选择失败原因
    public SingleLiveEvent<ParentMultiItemViewModel> choiceFailreasonsUC = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> dismissFailreasonsUC = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> completeFailreasonsUC = new SingleLiveEvent<>();

    //选择图片
    public SingleLiveEvent<MultiItemViewModel> getPictureUC = new SingleLiveEvent<>();
    private ContentParamDao contentParamDao;

    //选择时间
    public SingleLiveEvent<FillBlanksItemViewModel> selectTimeUC = new SingleLiveEvent<>();

    public TaskDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setTaskId(int mTaskId) {
        this.mTaskId = mTaskId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase database = AppDatabase.getDatabase();
        contentParamDao = database.contentParamDao();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppDatabase.onDestroy();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.task_detail_activity_title);
        setRightTextVisible(View.VISIBLE);
        setRightHasSelect(true);
    }

    @Override
    protected void rightTextOnClick() {
        super.rightTextOnClick();
        saveTask();
        Bundle bundle = new Bundle();
        bundle.putString("taskId", String.valueOf(mTaskId));
        startActivity(PatrolSignatureActivity.class, bundle);
    }


    /**
     * 查询任务内容列表
     */
    public void queryTaskContents() {
    }

    /**
     * 保存任务
     */
    public void saveTask() {

        List<ContentParamEntity> saveList = new ArrayList<>();
        for (TaskDetailPageItemViewModel pageItem : pageItems) {
            for (MultiItemViewModel item : pageItem.items) {
                ParentMultiItemViewModel itemViewModel = (ParentMultiItemViewModel) item;
                saveList.add(itemViewModel.getEntity());
            }
        }
        int size = saveList.size();
        ContentParamEntity[] array = saveList.toArray(new ContentParamEntity[size]);
        Observable.fromArray(array)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContentParamEntity>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        showDialog();
                    }

                    @Override
                    public void onNext(ContentParamEntity entity) {
                        KLog.e("onNext queryContentDetail entity is " + GsonUtil.GsonString(entity));
                        if (entity.getId() != 0) {
                            int id = contentParamDao.updateContentParam(entity);
                            KLog.e("updateContentParam is is " + id);
                        } else {
                            long id = contentParamDao.saveContentParam(entity);
                            KLog.e("saveContentParam is is " + id);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dismissDialog();
                        ToastUtils.showShort(R.string.task_detail_activity_not_save_success);
                        finish();
                    }
                });

    }

    public BindingCommand dismissFailreasonsDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            dismissFailreasonsUC.call();
        }
    });

    public BindingCommand completeFailreasonsDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            completeFailreasonsUC.call();
        }
    });

    /**
     * 获取动态巡查信息
     */
    public void getInspectionsheetsQuery() {
        request(model.getInspectionsheetsQuery(Const.header(), String.valueOf(mTaskId)), new ApiCallBack<InspectionsheetResultBean>() {

            @Override
            protected void onSuccess(InspectionsheetResultBean inspectionsheetResultBean, String message) {
                offscreenPageLimitUC.setValue(inspectionsheetResultBean.getInspectionQuestions().size());
                for (InspectionsheetResultBean.InspectionQuestionsBean bean : inspectionsheetResultBean.getInspectionQuestions()) {
                    items.add(new TaskDetailItemViewModel(TaskDetailViewModel.this, bean));
                    pageItems.add(new TaskDetailPageItemViewModel(TaskDetailViewModel.this, bean));
                }
                for (InspectionsheetResultBean.FailreasonsBean failreason : inspectionsheetResultBean.getFailreasons()) {
                    failreasonsGroupItems.add(new TaskDetailFailreasonsGroupItemViewModel(TaskDetailViewModel.this, failreason));
                }
                for (InspectionsheetResultBean.FailreasonsBean.ChildsBean child : inspectionsheetResultBean.getFailreasons().get(0).getChilds()) {
                    child.setGroupId(inspectionsheetResultBean.getFailreasons().get(0).getGroupId());
                    failreasonsChildsItems.add(new TaskDetailFailreasonsChildsItemViewModel(TaskDetailViewModel.this, child));
                }
                items.get(0).selectFlag.set(true);
                failreasonsGroupItems.get(0).selectFlag.set(true);


            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                if (exception.handleCode(Const.errorCode())) {
                    exception.toast();
                }
            }


        });
    }


}

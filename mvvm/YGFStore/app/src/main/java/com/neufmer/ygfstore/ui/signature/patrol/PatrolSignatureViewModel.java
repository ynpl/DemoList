package com.neufmer.ygfstore.ui.signature.patrol;

import android.app.Application;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.manager.CacheInfoManager;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WangXing on 2019/6/27.
 */
public class PatrolSignatureViewModel extends ToolbarViewModel {

    private final SubmitParamDao mSubmitParam;
    public SingleLiveEvent<Void> clearSignatureUC = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> saveSignatureUC = new SingleLiveEvent<>();

    public PatrolSignatureViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getDatabase();
        mSubmitParam = database.SubmitParamDao();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppDatabase.onDestroy();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.patrol_signature_activity_title);
    }

    public BindingCommand clearSignatureClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            clearSignatureUC.call();
        }
    });

    public BindingCommand confirmSignatureClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            saveSignatureUC.call();
        }
    });


    protected void saveSubmitParam(final String path, final String taskId) {
        mSubmitParam.querySubmitDetail(CacheInfoManager.getInstance().getUserId(), taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<SubmitParamEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SubmitParamEntity submitParamEntity) {
                        submitParamEntity.setInspectorSign(path);
                        mSubmitParam.updateSubmitParam(submitParamEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        SubmitParamEntity entity = new SubmitParamEntity();
                        entity.setCreateBy(CacheInfoManager.getInstance().getUserId());
                        entity.setInspectorSign(path);
                        entity.setTaskId(Integer.parseInt(taskId));
                        mSubmitParam.saveSubmitParam(entity);
                    }
                });
    }

}

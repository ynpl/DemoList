package com.neufmer.ygfstore.ui.signature.storefront;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.neufmer.ygfstore.ui.signature.give_up.GiveUpSignatureActivity;
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
public class StoreFrontSignatureViewModel extends ToolbarViewModel {

    private final SubmitParamDao mSubmitParam;
    private String mTaskId;
    public SingleLiveEvent<Void> clearSignatureUC = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> confirmSignatureUC = new SingleLiveEvent<>();

    public StoreFrontSignatureViewModel(@NonNull Application application) {
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
        setTitleTextRes(R.string.store_front_signature_activity_title);
        setRightTextRes(R.string.store_front_signature_activity_give_up_sign);
        setRightTextVisible(View.VISIBLE);
        setRightHasSelect(true);
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
            confirmSignatureUC.call();
        }
    });

    protected void setTaskId(String taskId) {
        mTaskId = taskId;
    }

    @Override
    protected void rightTextOnClick() {
        super.rightTextOnClick();
        Bundle bundle = new Bundle();
        bundle.putString("taskId", mTaskId);
        startActivity(GiveUpSignatureActivity.class, bundle);
    }

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
                        submitParamEntity.setContactSign(path);
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

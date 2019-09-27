package com.neufmer.ygfstore.ui.signature.give_up;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.dao.SubmitParamDao;
import com.neufmer.ygfstore.db.entity.SubmitParamEntity;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.ContextUtils;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WangXing on 2019/6/27.
 */
public class GiveUpSignatureViewModel extends ToolbarViewModel {

    private final SubmitParamDao submitParamDao;
    private String mTaskId;
    private String mAbandonReason;
    public ObservableInt editVisibility = new ObservableInt(View.GONE);

    public ObservableField<String> editContent = new ObservableField("");

    public SingleLiveEvent<Void> goSyncUC = new SingleLiveEvent<>();

    public GiveUpSignatureViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getDatabase();
        submitParamDao = database.SubmitParamDao();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppDatabase.getDatabase();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.give_up_signature_activity_title);
    }

    protected void setTaskId(String taskId) {
        mTaskId = taskId;
    }

    public BindingCommand<RadioButton> onChangedCommand = new BindingCommand<>(new BindingConsumer<RadioButton>() {
        @Override
        public void call(RadioButton button) {
            mAbandonReason = button.getText().toString();
            if (button.getText().toString().equals(ContextUtils.getContext().getString(R.string.give_up_signature_activity_reason_2))) {
                editVisibility.set(View.VISIBLE);
            } else {
                editVisibility.set(View.GONE);
                editContent.set("");
            }
        }
    });

    public BindingCommand onConfirmClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            submitParamDao.querySubmitDetail(CacheInfoManager.getInstance().getUserId(), mTaskId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SubmitParamEntity>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(SubmitParamEntity submitParamEntity) {
                            submitParamEntity.setAbandonReason(mAbandonReason);
                            submitParamEntity.setRemark(editContent.get());
                            submitParamDao.updateSubmitParam(submitParamEntity);
                            goSyncUC.call();
                        }

                        @Override
                        public void onError(Throwable e) {
                            SubmitParamEntity entity = new SubmitParamEntity();
                            entity.setAbandonReason(mAbandonReason);
                            entity.setRemark(editContent.get());
                            submitParamDao.saveSubmitParam(entity);
                            goSyncUC.call();
                        }
                    });

        }
    });

}

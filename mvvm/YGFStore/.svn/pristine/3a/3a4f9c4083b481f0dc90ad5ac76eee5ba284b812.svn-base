package com.neufmer.ygfstore.ui.task_detail.multiitem;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.db.AppDatabase;
import com.neufmer.ygfstore.db.entity.ContentParamEntity;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WangXing on 2019/7/16.
 */
public class ParentMultiItemViewModel extends MultiItemViewModel<TaskDetailViewModel> {

    public List<String> answer = new ArrayList<>();

    public ContentParamEntity entity = new ContentParamEntity();

    public ObservableList<String> imagePaths = new ObservableArrayList<>();

    public ObservableField<String> remarkTest = new ObservableField<>("");

    public List<List<String>> failReasonList = new ArrayList<>();

    public ObservableField<String> fillText = new ObservableField<>();

    public ObservableField<String> selectText = new ObservableField<>();

    public ObservableField<String> answerText = new ObservableField<>();

    protected static final String KEY_PIC_PATH = "PIC_PATH";


    public ParentMultiItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean data) {
        super(viewModel);
        entity.setQuestionId(data.getId());
        AppDatabase.getDatabase().contentParamDao().queryContentDetail(CacheInfoManager.getInstance().getUserId(), String.valueOf(viewModel.mTaskId), String.valueOf(data.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<ContentParamEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ContentParamEntity e) {
                        entity = e;
                        imagePaths.addAll(entity.getImage());
                        failReasonList.addAll(e.getFailReason());
                        if (!entity.getAnswer().isEmpty()) {
                            fillText.set(entity.getAnswer().get(0));
                            selectText.set(entity.getAnswer().get(0));
                            answerText.set(entity.getAnswer().get(0));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public ContentParamEntity getEntity() {
        if (!StringUtils.isEmpty(fillText.get())) {
            answer.clear();
            answer.add(fillText.get());
        }
        if (!StringUtils.isEmpty(selectText.get())) {
            answer.clear();
            answer.add(selectText.get());
        }
        if (!StringUtils.isEmpty(answerText.get())) {
            answer.clear();
            answer.add(answerText.get());
        }
        entity.setTaskId(viewModel.mTaskId);
        entity.setCreateBy(CacheInfoManager.getInstance().getUserId());
        entity.setImage(imagePaths);
        entity.setAnswer(answer);
        entity.setRemark(remarkTest.get());
        entity.setFailReason(failReasonList);
        return entity;
    }

}

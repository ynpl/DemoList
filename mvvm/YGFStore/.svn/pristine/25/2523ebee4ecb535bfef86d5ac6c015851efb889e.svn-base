package com.neufmer.ygfstore.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.neufmer.ygfstore.db.converter.ContentConverter;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by WangXing on 2019/7/12.
 */
@Entity(tableName = "t_content")
public class ContentParamEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    //任务Id
    @ColumnInfo(name = "task_id")
    @NonNull
    public int taskId;

    //巡查题id
    @ColumnInfo(name = "question_id")
    @NonNull
    public int questionId;

    //上传的图片
    @ColumnInfo(name = "image")
    @NonNull
    public List<String> image;

    //问题答案
    @ColumnInfo(name = "answer")
    @NonNull
    public List<String> answer;

    //备注
    @ColumnInfo(name = "remark")
    @NonNull
    public String remark;

    //备注
    @ColumnInfo(name = "failReason")
    @NonNull
    public List<List<String>> failReason;

    //创建人
    @ColumnInfo(name = "create_by")
    @NonNull
    public String createBy;


    public ContentParamEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<List<String>> getFailReason() {
        return failReason;
    }

    public void setFailReason(List<List<String>> failReason) {
        this.failReason = failReason;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}

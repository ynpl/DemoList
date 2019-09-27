package com.neufmer.ygfstore.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by WangXing on 2019/7/18.
 */
@Entity(tableName = "t_submit")
public class SubmitParamEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    //任务Id
    @ColumnInfo(name = "task_id")
    @NonNull
    public int taskId;

    //巡查人员签字图片
    @ColumnInfo(name = "inspector_sign")
    @NonNull
    public String inspectorSign;

    //法人签字图片
    @ColumnInfo(name = "contact_sign")
    @NonNull
    public String contactSign;

    //放弃原因
    @ColumnInfo(name = "abandon_reason")
    @NonNull
    public String abandonReason;

    //备注
    @ColumnInfo(name = "remark")
    @NonNull
    public String remark;

    //创建人
    @ColumnInfo(name = "create_by")
    @NonNull
    public String createBy;

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

    public String getInspectorSign() {
        return inspectorSign;
    }

    public void setInspectorSign(String inspectorSign) {
        this.inspectorSign = inspectorSign;
    }

    public String getContactSign() {
        return contactSign;
    }

    public void setContactSign(String contactSign) {
        this.contactSign = contactSign;
    }

    public String getAbandonReason() {
        return abandonReason;
    }

    public void setAbandonReason(String abandonReason) {
        this.abandonReason = abandonReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}

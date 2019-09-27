package com.neufmer.ygfstore.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.neufmer.ygfstore.db.entity.SubmitParamEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by WangXing on 2019/7/18.
 */
@Dao
public interface SubmitParamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveSubmitParam(SubmitParamEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateSubmitParam(SubmitParamEntity entity);

    @Query("SELECT id,task_id,inspector_sign,contact_sign,abandon_reason,remark,create_by FROM t_submit WHERE create_by = :userId AND task_id = :taskId")
    Single<SubmitParamEntity> querySubmitDetail(String userId, String taskId);
    @Query("SELECT id,task_id,inspector_sign,contact_sign,abandon_reason,remark,create_by FROM t_submit WHERE create_by = :userId")
    Single<List<SubmitParamEntity>> querySubmitListByUserId(String userId);

}

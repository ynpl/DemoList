package com.neufmer.ygfstore.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.neufmer.ygfstore.db.entity.ContentParamEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by WangXing on 2019/7/12.
 */
@Dao
public interface ContentParamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveContentParam(ContentParamEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateContentParam(ContentParamEntity entity);

    @Query("SELECT id,task_id,question_id,image,answer,remark,failReason,create_by FROM t_content WHERE create_by = :userId AND task_id = :taskId")
    Single<List<ContentParamEntity>> queryContentList(String userId, String taskId);

    @Query("SELECT id,task_id,question_id,image,answer,remark,failReason,create_by FROM t_content WHERE create_by = :userId AND task_id = :taskId AND question_id == :questionId")
    Single<ContentParamEntity> queryContentDetail(String userId, String taskId, String questionId);

    @Query("SELECT  id,task_id,question_id,image,answer,remark,failReason,create_by  FROM t_content WHERE create_by = :userId")
    Single<List<ContentParamEntity>> querContentList(String userId);

}

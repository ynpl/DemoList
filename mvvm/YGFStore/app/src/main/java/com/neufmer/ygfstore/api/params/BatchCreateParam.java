package com.neufmer.ygfstore.api.params;

import com.wangxing.code.mvvm.http.body.BaseRequestBody;

import java.util.List;

/**
 * Created by WangXing on 2019/7/3.
 */
public class BatchCreateParam extends BaseRequestBody {

    /**
     * startDate : 2019-06-30T16:00:00.000Z
     * finishDate : 2019-06-30T16:00:00.000Z
     * targetCompleteTimes : 4
     * targetStores : [1,2,3]
     * assignees : [1,2,4,8]
     * inspectionSheet : 1
     */

    private String startDate;
    private String finishDate;
    private int targetCompleteTimes;
    private String inspectionSheet;
    private List<Integer> targetStores;
    private List<Integer> assignees;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getTargetCompleteTimes() {
        return targetCompleteTimes;
    }

    public void setTargetCompleteTimes(int targetCompleteTimes) {
        this.targetCompleteTimes = targetCompleteTimes;
    }

    public String getInspectionSheet() {
        return inspectionSheet;
    }

    public void setInspectionSheet(String inspectionSheet) {
        this.inspectionSheet = inspectionSheet;
    }

    public List<Integer> getTargetStores() {
        return targetStores;
    }

    public void setTargetStores(List<Integer> targetStores) {
        this.targetStores = targetStores;
    }

    public List<Integer> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Integer> assignees) {
        this.assignees = assignees;
    }
}

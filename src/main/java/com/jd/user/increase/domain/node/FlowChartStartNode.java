package com.jd.user.increase.domain.node;

import com.jd.user.increase.domain.common.CommonCellAttr;

import java.util.Date;

/**
 * 开始节点组件，每个节点成为一个组件，开始控件中包含任务相关的基本配置
 * 记录graph项目的整个信息，包括项目的ID，运行时间等参数
 *
 * @author wangyongpeng11
 * @date 2021-01-08 14:28
 */
public class FlowChartStartNode extends CommonCellAttr {


    /** graph项目唯一标识*/
    private Long graphTaskId;

    /** graph项目名称：名称用于描述任务主要功能，并与其它任务进行区分 */
    private String graphTaskName;

    /** graph项目开始执行的时间 */
    private Date graphTaskStartTime;

    /**是否是测试项目: 1:测试， 2：线上 ...*/
    private Integer taskType;



    // -----------------------------getter and setter-----------------------------
    public Long getGraphTaskId() {
        return graphTaskId;
    }

    public void setGraphTaskId(Long graphTaskId) {
        this.graphTaskId = graphTaskId;
    }

    public String getGraphTaskName() {
        return graphTaskName;
    }

    public void setGraphTaskName(String graphTaskName) {
        this.graphTaskName = graphTaskName;
    }

    public Date getGraphTaskStartTime() {
        return graphTaskStartTime;
    }

    public void setGraphTaskStartTime(Date graphTaskStartTime) {
        this.graphTaskStartTime = graphTaskStartTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

}

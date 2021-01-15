package com.jd.user.increase.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 画布数据元素的标准数据格式
 * 对于画布逐个解析，将原始数据解析为List<GraphCellData>,其中普通流程节点正常有source和yTarget来组成链表，
 * 判断节点新增 isJudgeNode，judgeCondition，nTarget来处理特殊情况
 *
 * @author wangyongpeng11
 * @date 2020-12-23 19:56
 */
public class GraphNodeData {
    /** 数据库自增ID */
//    private Long id;
    /** graph元素ID */
    private String cellId;
    /** TYPE： rect, edge, judge */
    private String cellType;
    private String cellText;
    private String value1;
    private String value2;
    private String value3;
    /** sourceId， targetId指的是边的属性 */
    private String sourceId;
    private String targetId;

    /** previousNodeId， nextNodeId 指的是节点的属性，判断节点有多个下一节点  */
    private String previousNodeId;
    private String nextNodeId;
    /** 判断条件 */
    private Boolean judgeCondition;
    /** 考虑了分支节点的情况，Y代表流程节点和判断节点YES时下一个节点ID*/
    private String yNextNodeId;
    /** 考虑了分支节点的情况，N代表判断节点No时下一个节点的ID*/
    private String nNextNodeId;


    // getter and setter


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getCellText() {
        return cellText;
    }

    public void setCellText(String cellText) {
        this.cellText = cellText;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getPreviousNodeId() {
        return previousNodeId;
    }

    public void setPreviousNodeId(String previousNodeId) {
        this.previousNodeId = previousNodeId;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public Boolean getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(Boolean judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public String getyNextNodeId() {
        return yNextNodeId;
    }

    public void setyNextNodeId(String yNextNodeId) {
        this.yNextNodeId = yNextNodeId;
    }

    public String getnNextNodeId() {
        return nNextNodeId;
    }

    public void setnNextNodeId(String nNextNodeId) {
        this.nNextNodeId = nNextNodeId;
    }
    //    @Override
//    public String toString() {
//        return "GraphCellData{" +
//                "id='" + id + '\'' +
//                ", type='" + type + '\'' +
//                ", cellText='" + cellText + '\'' +
//                ", value1='" + value1 + '\'' +
//                ", value2='" + value2 + '\'' +
//                ", value3='" + value3 + '\'' +
//                ", sourceId='" + sourceId + '\'' +
//                ", targetId='" + targetId + '\'' +
//                ", previousNodeId='" + previousNodeId + '\'' +
//                ", nextNodeId='" + nextNodeId + '\'' +
//                '}';
//    }
}

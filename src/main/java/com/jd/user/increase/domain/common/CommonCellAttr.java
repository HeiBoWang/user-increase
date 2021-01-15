package com.jd.user.increase.domain.common;

/**
 * 节点的公用属性
 *
 * @author wangyongpeng11
 * @date 2021-01-11 15:01
 */
public class CommonCellAttr {
    /** 当前节点的唯一标识 */
    private String graphCellId;
    /** 组件的唯一功能标识： 开始节点功能标识 -1 */
    private String nodeFunctionId;
    /** functionName,调用的功能名称 */
    private String nodeFunctionName;

    /**节点组件类型名称： flow-chart-start
     * 流程节点-开始节点，每个节点都有不同测标识，说明它具有什么样的功能
     */
    private String nodeShapeType;
    /**
     * 节点左右链接桩端口的ID
     * right: 对应条件为yes
     * left: 对应条件为no
     */
    private String rightPortId;
    private String leftPortId;

    /** 上一个节点 GraphCellId */
    private String previousNodeId;

    /** 条件为true时， 下一个节点 GraphCellId */
    private String nextNodeId;

    /** 条件为false时， 下一个节点 GraphCellId */
    private String noNextNodeId;

    /** 每一个元素都有一个描述 */
    private String cellText;


    public String getGraphCellId() {
        return graphCellId;
    }

    public void setGraphCellId(String graphCellId) {
        this.graphCellId = graphCellId;
    }

    public String getNodeFunctionId() {
        return nodeFunctionId;
    }

    public void setNodeFunctionId(String nodeFunctionId) {
        this.nodeFunctionId = nodeFunctionId;
    }

    public String getNodeFunctionName() {
        return nodeFunctionName;
    }

    public void setNodeFunctionName(String nodeFunctionName) {
        this.nodeFunctionName = nodeFunctionName;
    }

    public String getNodeShapeType() {
        return nodeShapeType;
    }

    public void setNodeShapeType(String nodeShapeType) {
        this.nodeShapeType = nodeShapeType;
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

    public String getCellText() {
        return cellText;
    }

    public void setCellText(String cellText) {
        this.cellText = cellText;
    }

    public String getNoNextNodeId() {
        return noNextNodeId;
    }

    public void setNoNextNodeId(String noNextNodeId) {
        this.noNextNodeId = noNextNodeId;
    }

    public String getRightPortId() {
        return rightPortId;
    }

    public void setRightPortId(String rightPortId) {
        this.rightPortId = rightPortId;
    }

    public String getLeftPortId() {
        return leftPortId;
    }

    public void setLeftPortId(String leftPortId) {
        this.leftPortId = leftPortId;
    }
}

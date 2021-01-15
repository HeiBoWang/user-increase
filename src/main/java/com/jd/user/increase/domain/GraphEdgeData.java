package com.jd.user.increase.domain;

/**
 * graph中边的对象映射
 *
 * @author wangyongpeng11
 * @date 2021-01-05 16:46
 */
public class GraphEdgeData {
    /** id */
    private String cellId;
    /** sourceId， targetId指的是边的属性 */
    private String sourceNodeId;
    private String targetNodeId;

    /** 对应源节点的桩端口 */
    private String sourcePortId;
    private String targetPortId;


    // ------ getter ------
    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getSourceNodeId() {
        return sourceNodeId;
    }

    public void setSourceNodeId(String sourceNodeId) {
        this.sourceNodeId = sourceNodeId;
    }

    public String getTargetNodeId() {
        return targetNodeId;
    }

    public void setTargetNodeId(String targetNodeId) {
        this.targetNodeId = targetNodeId;
    }

    public String getSourcePortId() {
        return sourcePortId;
    }

    public void setSourcePortId(String sourcePortId) {
        this.sourcePortId = sourcePortId;
    }

    public String getTargetPortId() {
        return targetPortId;
    }

    public void setTargetPortId(String targetPortId) {
        this.targetPortId = targetPortId;
    }
}

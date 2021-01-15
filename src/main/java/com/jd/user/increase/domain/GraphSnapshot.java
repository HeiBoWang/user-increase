package com.jd.user.increase.domain;

import java.util.Date;

/**
 * 画布快照信息
 *
 * @author wangyongpeng11
 * @date 2020-12-28 14:43
 */
public class GraphSnapshot {
    private Long id;
    private String graphData;
    private String graphSnapshot;
    private String createUser;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGraphData() {
        return graphData;
    }

    public void setGraphData(String graphData) {
        this.graphData = graphData;
    }

    public String getGraphSnapshot() {
        return graphSnapshot;
    }

    public void setGraphSnapshot(String graphSnapshot) {
        this.graphSnapshot = graphSnapshot;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

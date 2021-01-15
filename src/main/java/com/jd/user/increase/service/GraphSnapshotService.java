package com.jd.user.increase.service;


import com.jd.user.increase.domain.GraphSnapshot;

import java.util.List;

/**
 * 请填写类的描述
 *
 * @author wangyongpeng11
 * @date 2020-12-25 11:43
 */
public interface GraphSnapshotService {
    /**
     * 增加一个graph项目
     */
    Integer insert(GraphSnapshot graphSnapshot);
    /**
     * 查询graph项目list
     */
    List<GraphSnapshot> queryList();
}

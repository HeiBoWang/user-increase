package com.jd.user.increase.dao;

import com.jd.user.increase.domain.GraphSnapshot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * graph_snapshot 表操作
 *
 * @author wangyongpeng11
 * @date 2020-12-25 11:58
 */
public interface GraphSnapshotDao {
    /**
     * 增加
     */
    Integer insert(GraphSnapshot graphSnapshot);

    /**
     * 查询列表
     */
    List<GraphSnapshot> queryList();

    /**
     * 根据ID查询一条信息
     */
    GraphSnapshot queryById(@Param("id") Long id);
}

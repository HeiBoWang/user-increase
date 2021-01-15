package com.jd.user.increase.service.impl;


import com.jd.user.increase.dao.GraphSnapshotDao;
import com.jd.user.increase.domain.GraphSnapshot;
import com.jd.user.increase.service.GraphSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请填写类的描述
 *
 * @author wangyongpeng11
 * @date 2020-12-25 11:43
 */
@Service
public class GraphSnapshotServiceImpl implements GraphSnapshotService {

    @Autowired
    private GraphSnapshotDao graphSnapshotDao;

    /**
     * 增加一个graph项目
     */
    @Override
    public Integer insert(GraphSnapshot graphSnapshot) {
        return graphSnapshotDao.insert(graphSnapshot);
    }

    /**
     * 查询graph项目list
     */
    @Override
    public List<GraphSnapshot> queryList() {
        return graphSnapshotDao.queryList();
    }
}

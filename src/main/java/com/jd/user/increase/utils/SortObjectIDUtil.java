package com.jd.user.increase.utils;

import com.jd.user.increase.domain.GraphNodeData;

import java.util.Comparator;

/**
 * GraphCellData对象数字排序，按照ID从小到大
 *
 * @author wangyongpeng11
 * @date 2020-12-24 14:32
 */

public class SortObjectIDUtil implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        // 自动生成的方法存根
        GraphNodeData triageResultBo1 =(GraphNodeData) o1;
        GraphNodeData triageResultBo2 =(GraphNodeData) o2;
        int flag = triageResultBo1.getCellId().compareTo(triageResultBo2.getCellId());
        return flag;

    }
}
package com.jd.user.increase.domain.node;

import com.jd.user.increase.domain.common.CommonCellAttr;

/**
 * 条件判断执行节点
 * 判断节点限制只有两个出端口
 *
 * @author wangyongpeng11
 * @date 2021-01-08 14:42
 */
public class FlowChartJudgeNode extends CommonCellAttr {
    /**
     * 判断条件表达式
     */
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}

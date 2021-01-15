package com.jd.user.increase.domain.node;

import com.jd.user.increase.domain.common.CommonCellAttr;

/**
 * 求和组件
 *
 * @author wangyongpeng11
 * @date 2021-01-11 15:08
 */
public class FlowChartSumNode extends CommonCellAttr {
    /** 参数A */
    private String argA;

    /** 参数B */
    private String argB;

    /** 组件返回结果 return result */
    private String returnResult;

    public String getArgA() {
        return argA;
    }

    public void setArgA(String argA) {
        this.argA = argA;
    }

    public String getArgB() {
        return argB;
    }

    public void setArgB(String argB) {
        this.argB = argB;
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }
}

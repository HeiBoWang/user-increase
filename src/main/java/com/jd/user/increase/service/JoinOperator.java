package com.jd.user.increase.service;

import com.ql.util.express.Operator;

/**
 * 请填写类的描述
 *
 * @author wangyongpeng11
 * @date 2021-01-12 16:25
 */
//定义一个继承自com.ql.util.express.Operator的操作符
public class JoinOperator extends Operator {
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if(opdata1 instanceof java.util.List){
            ((java.util.List)opdata1).add(opdata2);
            return opdata1;
        }else{
            java.util.List result = new java.util.ArrayList();
            result.add(opdata1);
            result.add(opdata2);
            return result;
        }
    }
}

package com.jd.user.increase.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.user.increase.common.HttpResult;
import com.jd.user.increase.domain.GraphEdgeData;
import com.jd.user.increase.domain.common.CommonCellAttr;
import com.jd.user.increase.domain.node.*;
import com.jd.user.increase.service.JoinOperator;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * todo previousNodeId 设置错误，设置的是本节点的
 * 此类负责解析画布，执行相关流程
 *
 * @author wangyongpeng11
 * @date 2021-01-08 14:12
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("graph")
@Slf4j
@RestController
public class ParseGraphController {
    /**
     * 解析画布中各个节点和边的数据
     */
    @RequestMapping("/executeGraph")
    public HttpResult executeGraph(@RequestBody String graphSnapshot) throws Exception {
        /**
         * 定义全局表达式变量
         */
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> expressRunnerContext = new DefaultContext<String, Object>();
        expressRunnerContext.put("a",50);


        // 存储边数据
        List<GraphEdgeData> graphEdgeDataList = new ArrayList<>();
        // 存储节点组件数据
        List objectNodeList = new ArrayList();
        List<String> stringNodeList = new ArrayList();

        // 存储节点的map, key: cellID + XXX, value: data对象
        Map nodeFactoryMap = new HashMap<String, Object>();

        JSONObject graphCellObject = JSON.parseObject(graphSnapshot);
        String graphCells =  graphCellObject.get("cells").toString();
        // 1. 将jsonBody解析成数组
        JSONArray jsonArray = JSONArray.parseArray(graphCells);
        // 遍历数组，先找出每一个node,赋值其id,type,等属性  然后再遍历边，让node串联起来
        for (int i = 0; i < jsonArray.size(); i++){
            // 3. 将数组中每个cell元素解析成对象，并取出对象中的data对象,
            JSONObject parseObject = JSON.parseObject(String.valueOf(jsonArray.get(i)));

            // 首先整理node节点，将字符串json string转化为object list 有非常多的类型组件，分别处理
            if (!"edge".equals(parseObject.get("shape"))){
                // 开始节点
                if ("flow-chart-start".equals(parseObject.get("shape"))){
                    // 此时 parseObject.get("data") 还是string,需要解析成对象
                    FlowChartStartNode startNode = JSON.parseObject(String.valueOf(parseObject.get("data")), FlowChartStartNode.class);
                    // 赋值id,shape, cellText
                    startNode.setGraphCellId((String) parseObject.get("id"));
                    startNode.setCellText(JSON.parseObject(
                            JSON.parseObject(parseObject.get("attrs").toString()).get("text").toString()
                    ).get("text").toString());

                    objectNodeList.add(startNode);
                    nodeFactoryMap.put(parseObject.get("id").toString(), startNode);
                // 加载数据组件
                }else if ("flow-chart-loadData".equals(parseObject.get("shape"))){
                    // 此时 parseObject.get("data") 还是string,需要解析成对象
                    LoadDataNode loadDataNode = JSON.parseObject(String.valueOf(parseObject.get("data")), LoadDataNode.class);
                    // 赋值id,shape, cellText
                    loadDataNode.setGraphCellId((String) parseObject.get("id"));
                    loadDataNode.setCellText(JSON.parseObject(
                            JSON.parseObject(parseObject.get("attrs").toString()).get("text").toString()
                    ).get("text").toString());

                    objectNodeList.add( loadDataNode);
                    nodeFactoryMap.put(parseObject.get("id").toString(), loadDataNode);

                    // 发送短信组件
                }else if ("flow-chart-sendSMS".equals(parseObject.get("shape"))){
                    // 此时 parseObject.get("data") 还是string,需要解析成对象
                    SendSMSComponent sendSMSComponent = JSON.parseObject(String.valueOf(parseObject.get("data")), SendSMSComponent.class);
                    // 赋值id,shape, cellText
                    sendSMSComponent.setGraphCellId((String) parseObject.get("id"));
                    sendSMSComponent.setCellText(JSON.parseObject(
                            JSON.parseObject(parseObject.get("attrs").toString()).get("text").toString()
                    ).get("text").toString());
                    objectNodeList.add(sendSMSComponent);
                    nodeFactoryMap.put(parseObject.get("id").toString(), sendSMSComponent);

                    // 判断组件
                }else if ("flow-chart-judge".equals(parseObject.get("shape"))){
                    // 此时 parseObject.get("data") 还是string,需要解析成对象
                    FlowChartJudgeNode judgeNode = JSON.parseObject(String.valueOf(parseObject.get("data")), FlowChartJudgeNode.class);
                    // 赋值id,shape, cellText
                    judgeNode.setGraphCellId((String) parseObject.get("id"));
                    judgeNode.setCellText(JSON.parseObject(
                            JSON.parseObject(parseObject.get("attrs").toString()).get("text").toString()
                    ).get("text").toString());
                    // 判断节点与其他节点不同， 需要用到端口的ID
                    judgeNode.setNodeShapeType("flow-chart-judge");
                    // 解析判断节点的左右端口
                    JSONArray portItems = JSONArray.parseArray(JSON.parseObject(parseObject.get("ports").toString()).get("items").toString());
                    for (Object portItem : portItems) {
                        JSONObject item = JSON.parseObject(portItem.toString());
                        // 拿出右节点
                        if ("right".equals(item.get("group"))){
                            judgeNode.setRightPortId(item.get("id").toString());
                        }
                        // 拿出左节点
                        if ("left".equals(item.get("group"))){
                            judgeNode.setLeftPortId(item.get("id").toString());
                        }
                    }
                    objectNodeList.add( judgeNode);
                    nodeFactoryMap.put(parseObject.get("id").toString() , judgeNode);
                // 发送邮件
                }else if ("flow-chart-sendEmail".equals(parseObject.get("shape"))){
                    // 此时 parseObject.get("data") 还是string,需要解析成对象
                    SendEmailComponent sendEmail = JSON.parseObject(String.valueOf(parseObject.get("data")), SendEmailComponent.class);
                    // 赋值id,shape, cellText
                    sendEmail.setGraphCellId((String) parseObject.get("id"));
                    sendEmail.setCellText(JSON.parseObject(
                            JSON.parseObject(parseObject.get("attrs").toString()).get("text").toString()
                    ).get("text").toString());
                    objectNodeList.add(sendEmail);
                    nodeFactoryMap.put(parseObject.get("id").toString() , sendEmail);
                }

            }else if ("edge".equals(parseObject.get("shape"))){
                // 整理边节点，将字符串json string转化为object list
                GraphEdgeData edgeData = new GraphEdgeData();
                edgeData.setCellId((String) parseObject.get("id"));
                // 设置source target
                edgeData.setSourceNodeId(JSON.parseObject(parseObject.get("source").toString()).get("cell").toString());
                edgeData.setTargetNodeId(JSON.parseObject(parseObject.get("target").toString()).get("cell").toString());
                // 保存节点的链接port ID
                edgeData.setSourcePortId(JSON.parseObject(parseObject.get("source").toString()).get("port").toString());
                edgeData.setTargetPortId(JSON.parseObject(parseObject.get("target").toString()).get("port").toString());
                graphEdgeDataList.add(edgeData);
            }
        }

        // 然后再遍历边节点List，将node节点赋值串联起来
        for (GraphEdgeData edgeData : graphEdgeDataList) {
            // 遍历节点集合
            for (int i = 0; i < objectNodeList.size(); i++) {
                // 节点不是判断节点，走该条件
                if (edgeData.getSourceNodeId().equals(((CommonCellAttr)objectNodeList.get(i)).getGraphCellId())){

                   // 是判断节点
                    if ("flow-chart-judge".equals(((CommonCellAttr)objectNodeList.get(i)).getNodeShapeType())){

                        // 是判断节，再判断边的sourcePortID和节点的rightPortId是不是相等，分别赋值right和left相关的后续ID
                        // 右节点
                        if (edgeData.getSourcePortId().equals(((CommonCellAttr) objectNodeList.get(i)).getRightPortId())){
                            ((CommonCellAttr) objectNodeList.get(i)).setNextNodeId(edgeData.getTargetNodeId());
                        }
                        // 左节点
                        if (edgeData.getSourcePortId().equals(((CommonCellAttr) objectNodeList.get(i)).getLeftPortId())){
                            ((CommonCellAttr) objectNodeList.get(i)).setNoNextNodeId(edgeData.getTargetNodeId());
                        }
                    }else if (!"flow-chart-judge".equals(((CommonCellAttr)objectNodeList.get(i)).getNodeShapeType())){
                        // 节点不是判断节点，走该条件
                        ((CommonCellAttr)objectNodeList.get(i)).setNextNodeId(edgeData.getTargetNodeId());
                    }
                    break;
                }
                // 设置每个节点组件的上一个节点字段
                if (edgeData.getTargetNodeId().equals( ((CommonCellAttr)objectNodeList.get(i)).getGraphCellId() )){
                    ((CommonCellAttr)objectNodeList.get(i)).setPreviousNodeId(edgeData.getSourceNodeId());
                    break;
                }
            log.info("i = {},object={}", i, JSON.toJSONString(objectNodeList.get(i)));
            }
        }

        /**
         * 串联起来之后，开始执行流程
         * 先拿到开始节点，再拿到下一个节点，拿到节点的前后顺序，按照顺序执行
         * 1. 拿到方法名和入参出参等，执行方法，给下一个节点继续执行
         *
         */

        // 用map保存链节点
        HashMap<String, String> nodeLinkedListMap = new HashMap<>();

        // 将对象类型的list转换为string类型的
        for (Object object : objectNodeList) {
            stringNodeList.add(JSON.toJSONString(object));
        }


        // 目的是为了找到开始节点和串联链表
        for (String object : stringNodeList) {
            // 类型转换还不如直接解析对象
            JSONObject jsonObject = JSON.parseObject(object);
            // 判断节点单独处理
            if ("flow-chart-judge".equals(jsonObject.get("nodeShapeType").toString())){
                nodeLinkedListMap.put(jsonObject.get("graphCellId").toString() + jsonObject.get("rightPortId").toString(), jsonObject.get("nextNodeId").toString());
                nodeLinkedListMap.put(jsonObject.get("graphCellId").toString() + jsonObject.get("leftPortId").toString(), jsonObject.get("noNextNodeId").toString());
            }
            // 其他节点，直接赋值
            if (!"flow-chart-judge".equals(jsonObject.get("nodeShapeType").toString())){
                nodeLinkedListMap.put(jsonObject.get("graphCellId").toString(), jsonObject.get("nextNodeId").toString());
            }

        }
        // 打印map
        for (Map.Entry<String, String> entry : nodeLinkedListMap.entrySet()) {
            log.info("key = {}, val = {}",entry.getKey(),entry.getValue());
        }

        // ---------------------------------------- 开始执行 ----------------------------------------
        // 保存下一个执行的节点的ID
        String currentNodeId = "";
        // 开始节点只执行一次flag
        Boolean isFirst= true;
        Boolean flag = true;
        Integer maxStep = 0;

        // 首先取开始节点执行,为了保证开始节点只执行一次，设置flag
        if (isFirst){
            for (String object : stringNodeList){
                JSONObject jsonObject = JSON.parseObject(object);
                if ("flow-chart-start".equals(jsonObject.get("nodeShapeType").toString())){
                    currentNodeId = jsonObject.get("graphCellId").toString();
                    log.info("开始执行：ID={}，{}方法................",jsonObject.get("graphCellId"),jsonObject.get("nodeFunctionName"));
                    String functionName = jsonObject.get("nodeFunctionName").toString();
                    break;
                }
            }
            isFirst = false;
        }


        while (flag){
            // 限制死循环，做多50次
            maxStep ++;
            if (maxStep > 20){
                flag= false;
            }

            // 然后依次执行后续节点
            log.info("currentNodeId={}", currentNodeId);
            String nextNodeId = nodeLinkedListMap.get(currentNodeId);
            // 执行到了最后一个节点，终止程序
            if (nextNodeId.equals("-1")){
                flag = false;
            }
            log.info("nextNodeId: {}", nextNodeId);
            for (String object : stringNodeList){
                JSONObject jsonObject = JSON.parseObject(object);
                if (jsonObject.get("graphCellId").equals(nextNodeId)){

                    log.info("开始执行：ID={}，{}方法................",jsonObject.get("graphCellId"),jsonObject.get("nodeFunctionName"));

                    // 如果是判断节点，
                    if("flow-chart-judge".equals(jsonObject.get("nodeShapeType").toString())){
                        String condition = jsonObject.get("condition").toString();

                        Boolean judgeResult = judgeCondition(runner, expressRunnerContext, condition);
                        if (judgeResult){
                            currentNodeId = jsonObject.get("graphCellId").toString() + jsonObject.get("rightPortId").toString();
                        }else {
                            currentNodeId = jsonObject.get("graphCellId").toString() + jsonObject.get("leftPortId").toString();
                        }
                        // 只要命中，后续的不再循环
                    }else {
                        currentNodeId = jsonObject.get("graphCellId").toString();
                        // 只要命中，后续的不再循环
                    }
                    break;
                }
            }
        }

        return new HttpResult(200,"success", objectNodeList);
    }
    /**
     * 判断条件表达式
     */
    public static Boolean judgeCondition(ExpressRunner runner, DefaultContext<String, Object> expressRunnerContext, String param) throws Exception {
        String express = param;
        log.info("express = {}",JSON.toJSONString(express));
        Object r = runner.execute(express, expressRunnerContext, null, true, false);
        log.info("rrrrrrrr = {}",JSON.toJSONString(r));
        return (Boolean) r;
    }

    /**
     * 表达式测试
     */
    public static void express() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.addOperator("join",new JoinOperator());
        Object r = runner.execute("1 join 2 join 3", context, null, false, false);
        log.info("rrrrrrrr = {}",JSON.toJSONString(r));

    }

}

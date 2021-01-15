package com.jd.user.increase.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.user.increase.common.HttpResult;
import com.jd.user.increase.dao.GraphSnapshotDao;
import com.jd.user.increase.domain.GraphNodeData;
import com.jd.user.increase.domain.GraphEdgeData;
import com.jd.user.increase.domain.GraphSnapshot;
import com.jd.user.increase.domain.Person;
import com.jd.user.increase.service.GraphSnapshotService;
import com.jd.user.increase.utils.SortObjectIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 本类重点知识： 前后端分离，端口不一致导致跨域问题： @CrossOrigin(origins = "*", maxAge = 3600)
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @Autowired
    private GraphSnapshotService graphSnapshotService;

    @Autowired
    private GraphSnapshotDao graphSnapshotDao;


    /**
     * 测试方法
     *
     * @return
     */
    @RequestMapping("/demo")
    public String hello(@RequestBody Person person) {
        log.info(person.toString());
        return "欢迎进入后台";
    }

    /**
     * 解析graph中data的方法
     *
     * @return
     */
    @RequestMapping("/demo2")
    public HttpResult hello2(@RequestBody String graphCells) throws InterruptedException {
        log.info(graphCells);
        ArrayList<GraphNodeData> cellDataList = new ArrayList<>();
        // 1. 将cells中原始的画布数据转换成为标准的list
        cellDataList = convertCells2Bean(graphCells);
        // 2. 遍历list，解析数据,根据node的ID大小顺序执行
        // 从小到大排序
        SortObjectIDUtil sortObjectIDUtil = new SortObjectIDUtil();
        Collections.sort(cellDataList, sortObjectIDUtil);
        // 依次遍历执行
        for (GraphNodeData item : cellDataList) {
            String cellId = StringUtils.isEmpty(item.getCellId())? "" : item.getCellId();
            // 第一步执行获取数据
            String data = getData();
            log.info("步骤1",data);
            // 第二部执行判断，如果是A执行步骤3，否则执行步骤4
            if ("A".equals(data)){
                String step3 = executeStep3();
                log.info(step3);
            }
            if ("B".equals(data)){
                String step4 = executeStep4();
                log.info(step4);
            }
        }
        return new HttpResult(200, "success", cellDataList);
    }

    /**
     * 保存graph方法
     */
    @RequestMapping("/saveGraph")
    public HttpResult saveGraph(@RequestBody String graphJson){
        log.info("graphCells={}",graphJson);
        String graphCells = JSON.parseObject(graphJson).get("cells").toString();

        // 定义成员变量
        GraphSnapshot graphSnapshot = new GraphSnapshot();
        ArrayList<GraphNodeData> cellDataList = new ArrayList<>();
        // 1. 将cells中原始的画布数据转换成为标准的list
//        cellDataList = convertCells2Bean(graphCells);
        // 2. 遍历list，解析数据,根据node的ID大小顺序执行
        // 从小到大排序
//        SortObjectIDUtil sortObjectIDUtil = new SortObjectIDUtil();
//        Collections.sort(cellDataList, sortObjectIDUtil);
        graphSnapshot.setGraphData(JSON.toJSONString(cellDataList));
        graphSnapshot.setGraphSnapshot(graphJson);
        graphSnapshot.setCreateUser("wangyongpeng11");
        Integer rowNum = graphSnapshotService.insert(graphSnapshot);
        return new HttpResult(200,"ok",rowNum);
    }

    /**
     * 查询graph列表的方法
     */
    @RequestMapping("/graphList")
    public HttpResult graphList(){
//        List<GraphSnapshot> snapshotList = graphSnapshotService.queryList();
        List<GraphSnapshot> snapshotList = new ArrayList<>();
        GraphSnapshot graphSnapshot = new GraphSnapshot();
        String res = "{\"cells\":[{\"position\":{\"x\":420,\"y\":160},\"size\":{\"width\":80,\"height\":42},\"attrs\":{\"text\":{\"text\":\"起始节点\"},\"body\":{\"rx\":24,\"ry\":24}},\"shape\":\"flow-chart-rect\",\"data\":{\"id\":\"node-100\",\"type\":\"node\",\"cellText\":\"矩形节点文本\",\"value1\":\"矩形节点文本value1\",\"value2\":\"矩形节点文本value2\",\"value3\":\"矩形节点文本value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"45726225-0a03-409e-8475-07da4b8533c5\"},{\"group\":\"right\",\"id\":\"06111939-bf01-48d9-9f54-6465d9d831c6\"},{\"group\":\"bottom\",\"id\":\"6541f8dc-e48b-4b8c-a105-2ab3a47f1f21\"},{\"group\":\"left\",\"id\":\"54781206-573f-4982-a21e-5fac1e0e8a60\"}]},\"id\":\"8650a303-3568-4ff2-9fac-2fd3ae7e6f2a\",\"zIndex\":1},{\"position\":{\"x\":420,\"y\":250},\"size\":{\"width\":80,\"height\":42},\"attrs\":{\"text\":{\"text\":\"流程节点\"}},\"shape\":\"flow-chart-rect\",\"data\":{\"id\":\"node-100\",\"type\":\"node\",\"cellText\":\"矩形节点文本\",\"value1\":\"矩形节点文本value1\",\"value2\":\"矩形节点文本value2\",\"value3\":\"矩形节点文本value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"d1346f43-969a-4201-af5d-d09b7ef79980\"},{\"group\":\"right\",\"id\":\"d561926a-3a24-449a-abb1-0c20bc89947e\"},{\"group\":\"bottom\",\"id\":\"0cbde5df-ef35-410e-b6c3-a6b1f5561e3f\"},{\"group\":\"left\",\"id\":\"2fceb955-f7af-41ac-ac02-5a2ea514544e\"}]},\"id\":\"7b6fd715-83e6-4053-8c2b-346e6a857bf3\",\"zIndex\":2},{\"shape\":\"edge\",\"attrs\":{\"line\":{\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"targetMarker\":{\"name\":\"classic\",\"size\":8}}},\"id\":\"00f3c401-8bad-46b9-b692-232aa011d4c5\",\"router\":{\"name\":\"manhattan\"},\"zIndex\":3,\"source\":{\"cell\":\"8650a303-3568-4ff2-9fac-2fd3ae7e6f2a\",\"port\":\"6541f8dc-e48b-4b8c-a105-2ab3a47f1f21\"},\"target\":{\"cell\":\"7b6fd715-83e6-4053-8c2b-346e6a857bf3\",\"port\":\"d1346f43-969a-4201-af5d-d09b7ef79980\"},\"labels\":[{\"attrs\":{\"label\":{\"text\":\"edge\"}}}]}]}\n";
        snapshotList = graphSnapshotDao.queryList();
        log.info("snapshotList = {}",snapshotList);
        return HttpResult.buildSuccessResult(snapshotList);
    }

    /**
     * 根据ID查询一条graph的方法
     */
    @RequestMapping("/queryById")
    public HttpResult queryById(@RequestBody String graphId){
        String id = JSON.parseObject(graphId).get("graphId").toString();
        GraphSnapshot graphSnapshot = new GraphSnapshot();
        graphSnapshot = graphSnapshotDao.queryById(Long.valueOf(id));
        return HttpResult.buildSuccessResult(graphSnapshot);
    }


    /**
     * 将cells中原始的画布数据转换成为标准的list
     * @param graphCells
     * @return
     */
    private ArrayList<GraphNodeData> convertCells2Bean(String graphCells) {
        ArrayList<GraphNodeData> cellDataList = new ArrayList<>();
        // 1. 将jsonBody解析成数组
        JSONArray jsonArray = JSONArray.parseArray(graphCells);
        // 2. 遍历数组
        for (int i = 0; i < jsonArray.size(); i++) {
            // 3. 将数组中每个cell元素解析成对象，并去除对象中的data对象,
            JSONObject jsonObjectString = JSON.parseObject(String.valueOf(jsonArray.get(i)));
            // 此时jsonObject还是string,需要解析成对象
            log.info("jsonObject = {}", jsonObjectString);
            JSONObject parseObject = JSON.parseObject(String.valueOf(jsonObjectString));
            if (!"edge".equals(parseObject.get("shape"))) {
                // 此时 parseObject.get("data") 还是string,需要解析成对象
                GraphNodeData cellData = JSON.parseObject(String.valueOf(parseObject.get("data")), GraphNodeData.class);
                cellDataList.add(cellData);
            }
        }
        log.info("list = {}",cellDataList);
        return cellDataList;
    }

    /**
     * 将cells中原始的画布数据转换成为标准的list
     * @param graphCells
     * @return
     */
    private ArrayList<GraphNodeData> convertCells2BeanV2(String graphCells) {
        ArrayList<GraphNodeData> cellDataList = new ArrayList<>();
        // 1. 将jsonBody解析成数组
        JSONArray jsonArray = JSONArray.parseArray(graphCells);
        // 2. 遍历数组
        for (int i = 0; i < jsonArray.size(); i++) {
            // 3. 将数组中每个cell元素解析成对象，并去除对象中的data对象,
            JSONObject jsonObjectString = JSON.parseObject(String.valueOf(jsonArray.get(i)));
            // 此时jsonObject还是string,需要解析成对象
            log.info("jsonObject = {}", jsonObjectString);
            JSONObject parseObject = JSON.parseObject(String.valueOf(jsonObjectString));
            if (!"edge".equals(parseObject.get("shape"))) {
                // 此时 parseObject.get("data") 还是string,需要解析成对象
                GraphNodeData cellData = JSON.parseObject(String.valueOf(parseObject.get("data")), GraphNodeData.class);
                cellDataList.add(cellData);
            }
        }
        log.info("list = {}",cellDataList);
        return cellDataList;
    }

    /**
     * 执行单一，没有判断节点的指令流程 :
     * 1. 拿到graph的原始数据
     * 2. 根据边的信息将每个节点的source,target,nextNode数据填充完整
     * 3.
     * @return
     */
    @RequestMapping("/run")
    public HttpResult runGraph(@RequestBody String graphSnapshot){
        // 存储节点数据
        List<GraphNodeData> graphNodeDataList = new ArrayList<>();
        // 存储边数据
        List<GraphEdgeData> graphEdgeDataList = new ArrayList<>();

//        graphSnapshot = "{\"cells\":[{\"position\":{\"x\":20,\"y\":-130},\"size\":{\"width\":80,\"height\":42},\"attrs\":{\"text\":{\"text\":\"start\"},\"body\":{\"fill\":\"#F70909\",\"rx\":24,\"ry\":24}},\"shape\":\"flow-chart-rect\",\"data\":{\"cellId\":\"node-100\",\"type\":\"node\",\"cellText\":\"起始节点文本\",\"value1\":\" i = 0\",\"value2\":\"j = 0\",\"value3\":\"起始节点value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\",\"id\":\"node-100\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"1d2f204a-6138-4280-a120-7c5b870549ad\"},{\"group\":\"right\",\"id\":\"276402a9-c175-4af5-a66e-66796151c278\"},{\"group\":\"bottom\",\"id\":\"33347108-be5a-42d6-9c75-b3bb2d18a069\"},{\"group\":\"left\",\"id\":\"bd6e56b4-55e0-46b9-9527-6bb1ddc5b549\"}]},\"id\":\"ae4f7117-c410-4486-b936-464f3db9edfd\",\"zIndex\":1},{\"position\":{\"x\":20,\"y\":-40},\"size\":{\"width\":80,\"height\":42},\"attrs\":{\"text\":{\"text\":\"step1:数据\"},\"body\":{\"fill\":\"#0DDD1DF7\"}},\"shape\":\"flow-chart-rect\",\"data\":{\"cellId\":\"node-100\",\"type\":\"node\",\"cellText\":\"流程节点\",\"value1\":\"i = 5\",\"value2\":\"j = 50\",\"value3\":\"流程节点value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\",\"id\":\"node-101\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"7ade9a4a-5d26-4bb0-a471-f443106167e5\"},{\"group\":\"right\",\"id\":\"2aaf772e-c228-4617-a3c2-7d81023797fc\"},{\"group\":\"bottom\",\"id\":\"7471cc77-6aa9-4665-80c7-d67377bb27f1\"},{\"group\":\"left\",\"id\":\"87419b03-ff19-40b3-afca-adceda3b0898\"}]},\"id\":\"ce1838bb-7cc8-4ee2-9080-113b10ac210a\",\"zIndex\":2},{\"shape\":\"edge\",\"attrs\":{\"line\":{\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"targetMarker\":{\"name\":\"classic\",\"size\":8}}},\"id\":\"eb04847a-97e2-4dad-a240-e940b13de727\",\"router\":{\"name\":\"manhattan\"},\"source\":{\"cell\":\"ae4f7117-c410-4486-b936-464f3db9edfd\",\"port\":\"33347108-be5a-42d6-9c75-b3bb2d18a069\"},\"target\":{\"cell\":\"ce1838bb-7cc8-4ee2-9080-113b10ac210a\",\"port\":\"7ade9a4a-5d26-4bb0-a471-f443106167e5\"},\"zIndex\":3},{\"position\":{\"x\":20,\"y\":50},\"size\":{\"width\":80,\"height\":42},\"attrs\":{\"text\":{\"text\":\"step2: 计算\"},\"body\":{\"fill\":\"#FF5F5FF0\"}},\"shape\":\"flow-chart-rect\",\"data\":{\"cellId\":\"node-100\",\"type\":\"node\",\"cellText\":\"流程节点\",\"value1\":\"x = i + j\",\"value2\":\"x = 55\",\"value3\":\"流程节点value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\",\"id\":\"node-101\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"7ade9a4a-5d26-4bb0-a471-f443106167e5\"},{\"group\":\"right\",\"id\":\"2aaf772e-c228-4617-a3c2-7d81023797fc\"},{\"group\":\"bottom\",\"id\":\"7471cc77-6aa9-4665-80c7-d67377bb27f1\"},{\"group\":\"left\",\"id\":\"87419b03-ff19-40b3-afca-adceda3b0898\"}]},\"id\":\"7cd3c8fe-b27c-47c3-a088-6ba33002e5ac\",\"zIndex\":4},{\"shape\":\"edge\",\"attrs\":{\"line\":{\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"targetMarker\":{\"name\":\"classic\",\"size\":8}}},\"id\":\"85d72d91-031a-4ecb-8fa7-13582c3ee9e1\",\"router\":{\"name\":\"manhattan\"},\"source\":{\"cell\":\"ce1838bb-7cc8-4ee2-9080-113b10ac210a\",\"port\":\"7471cc77-6aa9-4665-80c7-d67377bb27f1\"},\"target\":{\"cell\":\"7cd3c8fe-b27c-47c3-a088-6ba33002e5ac\",\"port\":\"7ade9a4a-5d26-4bb0-a471-f443106167e5\"},\"zIndex\":5},{\"position\":{\"x\":20,\"y\":142},\"size\":{\"width\":80,\"height\":42},\"attrs\":{\"text\":{\"text\":\"step3:存储\"},\"body\":{\"fill\":\"#A85FFF\"}},\"shape\":\"flow-chart-rect\",\"data\":{\"cellId\":\"node-100\",\"type\":\"node\",\"cellText\":\"流程节点\",\"value1\":\"存储i\",\"value2\":\"存储J\",\"value3\":\"流程节点value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\",\"id\":\"node-101\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"7ade9a4a-5d26-4bb0-a471-f443106167e5\"},{\"group\":\"right\",\"id\":\"2aaf772e-c228-4617-a3c2-7d81023797fc\"},{\"group\":\"bottom\",\"id\":\"7471cc77-6aa9-4665-80c7-d67377bb27f1\"},{\"group\":\"left\",\"id\":\"87419b03-ff19-40b3-afca-adceda3b0898\"}]},\"id\":\"e41dca4b-ed0c-491a-934f-1281359a443c\",\"zIndex\":6},{\"shape\":\"edge\",\"attrs\":{\"line\":{\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"targetMarker\":{\"name\":\"classic\",\"size\":8}}},\"id\":\"50fd2857-2f70-48e2-aa57-f18d125db0d0\",\"router\":{\"name\":\"manhattan\"},\"source\":{\"cell\":\"7cd3c8fe-b27c-47c3-a088-6ba33002e5ac\",\"port\":\"7471cc77-6aa9-4665-80c7-d67377bb27f1\"},\"target\":{\"cell\":\"e41dca4b-ed0c-491a-934f-1281359a443c\",\"port\":\"7ade9a4a-5d26-4bb0-a471-f443106167e5\"},\"zIndex\":7},{\"position\":{\"x\":25,\"y\":238},\"size\":{\"width\":70,\"height\":70},\"attrs\":{\"text\":{\"text\":\"end\"},\"body\":{\"fill\":\"#FF5F5F\",\"rx\":35,\"ry\":35}},\"shape\":\"flow-chart-rect\",\"data\":{\"cellId\":\"node-100\",\"type\":\"node\",\"cellText\":\"矩形节点文本\",\"value1\":\"end1\",\"value2\":\"end2\",\"value3\":\"矩形节点文本value3\",\"sourceId\":\"10\",\"targetId\":\"10\",\"previousNodeId\":\"10\",\"nextNodeId\":\"10\"},\"ports\":{\"groups\":{\"top\":{\"position\":\"top\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"right\":{\"position\":\"right\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"bottom\":{\"position\":\"bottom\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}},\"left\":{\"position\":\"left\",\"attrs\":{\"circle\":{\"r\":3,\"magnet\":true,\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"fill\":\"#fff\",\"style\":{\"visibility\":\"hidden\"}}}}},\"items\":[{\"group\":\"top\",\"id\":\"8ce8eb80-f6c5-41ed-8bad-cfc067a232c4\"},{\"group\":\"right\",\"id\":\"d47f1ecb-d5f7-432a-8343-924cc6c68f84\"},{\"group\":\"bottom\",\"id\":\"eb674d18-005d-4e1d-a535-73896fc714b4\"},{\"group\":\"left\",\"id\":\"0bb01f3f-48b8-4f00-bc05-682528214762\"}]},\"id\":\"6044928f-e9e8-4412-92c5-dd1c92d239b6\",\"zIndex\":8},{\"shape\":\"edge\",\"attrs\":{\"line\":{\"stroke\":\"#5F95FF\",\"strokeWidth\":1,\"targetMarker\":{\"name\":\"classic\",\"size\":8}}},\"id\":\"90d07072-cad3-4ba7-9734-cc1743d031a6\",\"router\":{\"name\":\"manhattan\"},\"source\":{\"cell\":\"e41dca4b-ed0c-491a-934f-1281359a443c\",\"port\":\"7471cc77-6aa9-4665-80c7-d67377bb27f1\"},\"target\":{\"cell\":\"6044928f-e9e8-4412-92c5-dd1c92d239b6\",\"port\":\"8ce8eb80-f6c5-41ed-8bad-cfc067a232c4\"},\"zIndex\":9}]}";
        JSONObject graphCellObject = JSON.parseObject(graphSnapshot);
        String graphCells =  graphCellObject.get("cells").toString();

        // 1. 将jsonBody解析成数组
        JSONArray jsonArray = JSONArray.parseArray(graphCells);

        // 2. 遍历数组, 找出start 节点
//        for (int i = 0; i < jsonArray.size(); i++) {
//            // 3. 将数组中每个cell元素解析成对象，并去除对象中的data对象,
//            JSONObject jsonObjectString = JSON.parseObject(String.valueOf(jsonArray.get(i)));
//            // 此时jsonObject还是string,需要解析成对象
//            log.info("jsonObject = {}", jsonObjectString);
//            JSONObject parseObject = JSON.parseObject(String.valueOf(jsonObjectString));
//            // 首先需要找出start节点
//            if ("flow-chart-start".equals(parseObject.get("shape"))){
//                // 此时 parseObject.get("data") 还是string,需要解析成对象
//                GraphCellData cellData = JSON.parseObject(String.valueOf(parseObject.get("data")), GraphCellData.class);
//                graphCellDataList.add(cellData);
//            }
//        }
        // 遍历数组，先找出每一个node,赋值其id,type,等属性  然后再遍历边，让node串联起来
        for (int i = 0; i < jsonArray.size(); i++){
            // 3. 将数组中每个cell元素解析成对象，并去除对象中的data对象,
            JSONObject jsonObjectString = JSON.parseObject(String.valueOf(jsonArray.get(i)));
            // 此时jsonObject还是string,需要解析成对象
            JSONObject parseObject = JSON.parseObject(String.valueOf(jsonObjectString));
            // 首先整理node节点
            if (!"edge".equals(parseObject.get("shape"))){
                // 此时 parseObject.get("data") 还是string,需要解析成对象
                GraphNodeData cellData = JSON.parseObject(String.valueOf(parseObject.get("data")), GraphNodeData.class);
                // 赋值id,shape, cellText
                cellData.setCellId((String) parseObject.get("id"));
                cellData.setCellType((String) parseObject.get("shape"));
                cellData.setCellText(JSON.parseObject(
                        JSON.parseObject(parseObject.get("attrs").toString()).get("text").toString()
                ).get("text").toString());

                graphNodeDataList.add(cellData);
            }else if ("edge".equals(parseObject.get("shape"))){
                // 整理边节点
                GraphEdgeData edgeData = new GraphEdgeData();
                edgeData.setCellId((String) parseObject.get("id"));
                // 设置source target
                edgeData.setSourceNodeId(JSON.parseObject(parseObject.get("source").toString()).get("cell").toString());
                edgeData.setTargetNodeId(JSON.parseObject(parseObject.get("target").toString()).get("cell").toString());
                graphEdgeDataList.add(edgeData);
            }
        }

        // 然后再遍历边节点List，将node节点赋值串联起来
        for (GraphEdgeData edgeData : graphEdgeDataList) {

            // 遍历节点集合
            for (int i = 0; i < graphNodeDataList.size(); i++){
                if (edgeData.getSourceNodeId().equals(graphNodeDataList.get(i).getCellId())){
                    graphNodeDataList.get(i).setyNextNodeId(edgeData.getTargetNodeId());
                    break;
                }
            }

        }

        return HttpResult.buildSuccessResult(graphNodeDataList);
//        return HttpResult.buildSuccessResult(graphEdgeDataList);
    }


    private String executeStep4() {
        return "A";
    }

    private String executeStep3() {
        return "A";
    }

    private String getData() {
        return "A";
    }




}




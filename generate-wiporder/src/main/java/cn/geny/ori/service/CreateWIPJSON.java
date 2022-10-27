package cn.geny.ori.service;

import cn.geny.ori.excel.ReadExcel;
import cn.geny.ori.mapper.*;
import cn.geny.ori.model.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreateWIPJSON {

    @Autowired
    private CtProductFacilityMapper ctProductFacilityMapper;

    @Autowired
    private CtProcessMapper ctProcessMapper;

    @Autowired
    private CtProcessOperationMapper ctProcessOperationMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private CtFacilityMapper ctFacilityMapper;


    @SneakyThrows
    public String createWIPJSON(String ProductNo) {


        String Facility = "431";
//        String Facility = ReadExcel.getChervonProductWorkShop(ProductNo);
        CtFacility ctFacility = ctFacilityMapper.selectOne(new QueryWrapper<CtFacility>().eq("FACILITY", Facility));
        String erpDepartment = ctFacility.getErpdepartment();

        CtProductFacility ctProductFacility = ctProductFacilityMapper.selectOne(new QueryWrapper<CtProductFacility>().eq("PRODUCTNO", ProductNo));
        BigDecimal productID = ctProductFacility.getProductid();
        List<CtProcess> ctProcessList = ctProcessMapper.selectList(new QueryWrapper<CtProcess>().eq("PRODUCTID", productID).eq("ACTIVE",1));

//        if (ctProcessList.size() != 1) {
//            throw new Exception("产品工艺数目存在问题：(" + ctProcessList.size() + ")");
//        }

        CtProcess ctProcess = ctProcessList.get(0);
//        Facility = ctProcess.getFacility();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date scheduledStartDate = new Date();
        Date scheduledEndDate = DateUtils.addWeeks(scheduledStartDate, 1);
        SimpleDateFormat perfixDate = new SimpleDateFormat("MMddHHmm");
        String perDateStr = "_" + perfixDate.format(scheduledStartDate);



        List<Resource> resourceList = resourceMapper.selectList(new QueryWrapper<Resource>().eq("MOBILITYFLAG", 1).eq("RESOURCETYPE", 3).likeRight("NAME", Facility));
        Collections.shuffle(resourceList);
//        String lineNo = resourceList.get(0).getName();

        List<CtProcessOperation> ctProcessOperations = ctProcessOperationMapper.selectList(new QueryWrapper<CtProcessOperation>().eq("PROCESSID", ctProcess.getProcessid()));

        String WipOrderNo = "PU_" + ProductNo + perDateStr;
        String WorkOrderNo = "Order_" + ProductNo + perDateStr;


        String startDateStr = simpleDateFormat.format(scheduledStartDate);
        String endDateStr = simpleDateFormat.format(scheduledEndDate);

        BigDecimal RoutingID = ctProcess.getProcessid();
        String APSTaskNo = "PU_APS_" + ProductNo + perDateStr;
        Set<String> operationCodes = ctProcessOperations.stream()
                .map(CtProcessOperation::getOperationcode)
                .collect(Collectors.toSet());



        @Cleanup
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/template.json"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String content = stringBuilder.toString();
        JSONObject jsonObject = JSON.parseObject(content);
        jsonObject.replace("taskId", UUID.randomUUID());
        jsonObject.replace("WipOrderNo", WipOrderNo);
        jsonObject.replace("WorkOrderNo", WorkOrderNo);
        jsonObject.replace("ProductNo", ProductNo);
        jsonObject.replace("Facility", Facility);
        jsonObject.replace("RoutingID", RoutingID.toString());
        jsonObject.replace("ScheduledStartDate", startDateStr);
        jsonObject.replace("ScheduledCompletionDate", endDateStr);
        JSONArray jsonArray = jsonObject.getObject("WipOperationList", JSONArray.class);
        JSONObject child = jsonArray.getJSONObject(0);

        JSONArray children = new JSONArray();

        for (String operation : operationCodes) {
            JSONObject item = (JSONObject) child.clone();
            item.replace("OprSequenceNo", operation);
            item.replace("OperationCode", operation);
            item.replace("WorkOrderNo", WorkOrderNo);
            item.replace("WipOrderNo", WipOrderNo);
            item.replace("APSTaskNo", APSTaskNo);
//            item.replace("Shift", resourceClass);
            item.replace("ScheduledStartDate", startDateStr);
            item.replace("ScheduledCompletionDate", endDateStr);
            item.replace("LineNo","431LTOO09");
            children.add(item);
        }

        jsonObject.replace("WipOperationList", children);

        @Cleanup
        FileWriter writer = new FileWriter("src/main/resources/json/" + ProductNo + ".json");
        writer.write(jsonObject.toJSONString());
        writer.flush();

        String handleAfterJson = jsonObject.toJSONString().replace("\"", "\\\"");

        @Cleanup
        FileWriter writerJson = new FileWriter("src/main/resources/tmp.json");
//        writer.write(jsonObject.toJSONString().replace("\"","\\\""));
        writerJson.write(jsonObject.toJSONString());
        writerJson.flush();
        return handleAfterJson;
    }
}
package cn.geny.ori.generate;

import cn.geny.ori.model.AbSerial;
import cn.geny.ori.model.NewPackageSerial;
import cn.geny.ori.model.OldPackageSerial;
import cn.geny.ori.model.WaitHandleProduct;
import cn.geny.ori.service.AbSerialService;
import cn.geny.ori.service.NewPackageSerialService;
import cn.geny.ori.service.OldPackageSerialService;
import cn.geny.ori.service.WaitHandleProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.api.AssertProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImportSerialToLocal {


    Logger logger = LoggerFactory.getLogger(ImportSerialToLocal.class);
    @Autowired
    private NewPackageSerialService newPackageSerialService;

    @Autowired
    private OldPackageSerialService oldPackageSerialService;

    @Autowired
    private WaitHandleProductService waitHandleProductService;

    @Autowired
    private AbSerialService abSerialService;
    public HashMap<String, String> getCommonSerialByAttach(Date start, Date end) {
        HashMap<String, String> commonPrefixRelAttachRemark = new HashMap<>();
        List<NewPackageSerial> list = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>()
                .groupBy("ATTACT_REMARK")
                .ge("CREATE_DATE", start)
                .lt("CREATE_DATE", end)
        );
        for (NewPackageSerial newPackageSerial : list) {
            Long orderNum = newPackageSerial.getOrderNum();
            String template = "#######";
            StringBuilder serialEnd = new StringBuilder(newPackageSerial.getSerialEnd().toString());
            String attachRemark = newPackageSerial.getAttactRemark();
            String[] beforeSplitAttachRemark = attachRemark.split("-");
            String lastAttachRemark = beforeSplitAttachRemark[beforeSplitAttachRemark.length - 1];
            Pattern p = Pattern.compile(serialEnd.toString());
            Matcher m = p.matcher(lastAttachRemark);
            int totalRepeatCount = 0;
            while (m.find()) {
                totalRepeatCount++;
            }
            if (totalRepeatCount != 1) {
                String productNo = newPackageSerial.getProductNo();
                NewPackageSerial maxValuePackageSerial = newPackageSerialService
                        .getOne(new QueryWrapper<NewPackageSerial>()
                                        .eq("product_no", productNo)
                                        .orderByDesc("SERIAL_END"),
                                false);
                String maxSerialEnd = maxValuePackageSerial.getSerialEnd().toString();
                String maxAttachRemark = maxValuePackageSerial.getAttactRemark();
                String[] maxBeforeSplitAttachRemark = maxAttachRemark.split("-");
                String maxLastAttachRemark = maxBeforeSplitAttachRemark[beforeSplitAttachRemark.length - 1];
                Pattern p1 = Pattern.compile("[A-Za-z0-9]*" + maxSerialEnd);
                Matcher m1 = p1.matcher(maxLastAttachRemark);
                int count = 0;
                while (m1.find()) {
                    template = m1.group(0);
                    count++;
                }
                if (count != 1) {
                    logger.error("通过最大值未能分割成功1--"+orderNum);
                    continue;
                }

                // 最大流水值过滤后不存在影响位数
                while (maxSerialEnd.length() > serialEnd.length()) {
                    serialEnd.insert(0, "0");
                }

                Pattern p2 = Pattern.compile("[A-Za-z0-9]{" + (template.length() - serialEnd.length()) + "}" + serialEnd);
                Matcher m2 = p2.matcher(lastAttachRemark);
                totalRepeatCount = 0;
                while (m2.find()) {
                    template = m2.group(0);
                    totalRepeatCount++;
                }
                if (totalRepeatCount != 1) {
                    logger.error("通过最大值未能分割成功2--"+orderNum);
                    continue;
                }
            } else {
                Pattern p1 = Pattern.compile("[A-Za-z0-9]*" + serialEnd);
                Matcher m1 = p1.matcher(lastAttachRemark);
                totalRepeatCount = 0;
                while (m1.find()) {
                    template = m1.group(0);
                    totalRepeatCount++;
                }
                if (totalRepeatCount != 1) {
                    logger.error("未能解析到共有前缀--"+orderNum);
                    continue;
                }
            }
            // 判断字符串中不存在多次当前最大序列号时截取前面的内容
            int commonMaxIndex = template.indexOf(serialEnd.toString(), (template.length() - serialEnd.length()));
            char[] chars = template.toCharArray();
//            int start = 0;
            int index = commonMaxIndex;
            while (index > 0) {
                if (!String.valueOf(chars[index]).matches("[A-Za-z0-9]")) {
                    index++;
                    break;
                }
                index--;
            }
            StringBuffer commonBuffer = new StringBuffer();
            while (index < commonMaxIndex) {
                commonBuffer.append(chars[index++]);
            }

            // 通过共同前缀及最大值最小值检查是否有非包含元素
            StringBuffer currentSerialBuffer = new StringBuffer(newPackageSerial.getSerialStart().toString());
            while (currentSerialBuffer.length() < serialEnd.length()) {
                currentSerialBuffer.insert(0, 0);
            }
            boolean condition = attachRemark.contains(commonBuffer.toString() + currentSerialBuffer)
                    && attachRemark.contains(commonBuffer.toString() + serialEnd);
            if (!condition) {
                logger.error("判断共有前缀及最大值最小值失败--"+orderNum);
                continue;
            }
            commonPrefixRelAttachRemark.put(newPackageSerial.getAttactRemark(), commonBuffer.toString());
        }
        return commonPrefixRelAttachRemark;
    }

    public void handleRangeDate(Date start, Date end) {
        HashMap<String, String> commonPrefixRelAttachRemark = getCommonSerialByAttach(start, end);
        assert false == true;
        commonPrefixRelAttachRemark.forEach((key, commonSerial) -> {
            NewPackageSerial maxPackageSerial = newPackageSerialService
                    .getOne(new QueryWrapper<NewPackageSerial>().eq("attact_remark", key).groupBy("attact_remark"));
            long ordernum = maxPackageSerial.getOrderNum();
            logger.info("开始插入----" + ordernum);
            Integer maxRelAttachRemark = maxPackageSerial.getSerialEnd();
            int step = 0;
            Pattern p = Pattern.compile(commonSerial + maxRelAttachRemark + "[A-G]");
            Matcher m = p.matcher(key);
            int totalRepeatCount = 0;
            while (m.find()) {
                totalRepeatCount++;
            }
            if (totalRepeatCount > 1) {
                NewPackageSerial packageSerial = newPackageSerialService
                        .getOne(new QueryWrapper<NewPackageSerial>().eq("attact_remark", key));
                int currentSerial = packageSerial.getSerialStart();
                int serialEnd = packageSerial.getSerialEnd();
                List<AbSerial> listAbSerial = new ArrayList<>();
                while (currentSerial <= serialEnd) {
                    StringBuffer currentSerialBuffer = new StringBuffer(String.valueOf(currentSerial));
                    int max = packageSerial.getSerialEnd();
                    int length = String.valueOf(max).length();
                    while (currentSerialBuffer.length() < length) {
                        currentSerialBuffer.insert(0, 0);
                    }
                    int startAlphabet = 0;
                    while (startAlphabet < totalRepeatCount) {
                        AbSerial abSerial = new AbSerial();
                        abSerial.setOrderNum(packageSerial.getOrderNum());
                        abSerial.setFullSerial(commonSerial + currentSerial + (char) ('A' + startAlphabet++));
                        listAbSerial.add(abSerial);
                    }
                    currentSerial++;
                }
                abSerialService.saveBatch(listAbSerial);
            } else {
                List<NewPackageSerial> needUpdData = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>().eq("ATTACT_REMARK", key).orderByAsc("order_num"));
                NewPackageSerial newPackageSerial = needUpdData.get(0);
                int totalSerialSize = newPackageSerial.getSerialEnd() - newPackageSerial.getSerialStart() + 1;
                if (totalSerialSize != needUpdData.size()) {
                    int repeatCount = needUpdData.size() / totalSerialSize;
                    for (NewPackageSerial packageSerial : needUpdData) {
                        int currentSerial = packageSerial.getSerialStart();
                        currentSerial = currentSerial + step / repeatCount;
                        StringBuffer currentSerialBuffer = new StringBuffer(String.valueOf(currentSerial));
                        int max = packageSerial.getSerialEnd();
                        int length = String.valueOf(max).length();
                        while (currentSerialBuffer.length() < length) {
                            currentSerialBuffer.insert(0, 0);
                        }
                        packageSerial.setFullSerial(commonSerial + currentSerialBuffer);
                        step++;
                    }
                } else {
                    Pattern p1 = Pattern.compile(commonSerial + maxRelAttachRemark + "X");
                    Matcher m1 = p1.matcher(key);
                    String addX = "";
                    int judgeTotalRepeatCount = 0;
                    while (m1.find()) {
                        judgeTotalRepeatCount++;
                    }
                    if (judgeTotalRepeatCount != 0) {
                        addX = "X";
                    }
                    for (NewPackageSerial packageSerial : needUpdData) {
                        int currentSerial = packageSerial.getSerialStart();
                        currentSerial = currentSerial + step;
                        StringBuffer currentSerialBuffer = new StringBuffer(String.valueOf(currentSerial));
                        int max = packageSerial.getSerialEnd();
                        int length = String.valueOf(max).length();
                        while (currentSerialBuffer.length() < length) {
                            currentSerialBuffer.insert(0, 0);
                        }
                        packageSerial.setFullSerial(commonSerial + currentSerialBuffer + addX);
                        step++;
                    }
                }
                newPackageSerialService.updateBatchById(needUpdData);
            }
        });
    }
}

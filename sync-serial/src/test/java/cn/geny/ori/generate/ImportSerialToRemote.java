package cn.geny.ori.generate;

import cn.geny.ori.model.AbSerial;
import cn.geny.ori.model.CtSerialProductBatchsn;
import cn.geny.ori.model.CtSerialSn;
import cn.geny.ori.model.NewPackageSerial;
import cn.geny.ori.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections4.ListUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImportSerialToRemote {


    Logger logger = LoggerFactory.getLogger(ImportSerialToRemote.class);
    @Autowired
    private NewPackageSerialService newPackageSerialService;

    @Autowired
    private CtSerialSnService ctSerialSnService;

    @Autowired
    private CtSerialProductBatchsnService ctSerialProductBatchsnService;


    @Autowired
    private AbSerialService abSerialService;

    public HashMap<String, String> getCommonSerialByAttach(Date start, Date end) {
//        List<Long> needUpd = new ArrayList<>();
//        needUpd.add(173241l);
//        needUpd.add(1703080l);
//        needUpd.add(5661167l);
//        needUpd.add(6827203l);
//        needUpd.add(5346200l);
        HashMap<String, String> commonPrefixRelAttachRemark = new HashMap<>();
        List<NewPackageSerial> list = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>()
                .groupBy("ATTACT_REMARK")
                        // // TODO: 2022/10/20 标记需处理元素
//                .eq("full_serial","1")
                .eq("order_num","3026539")
                .ge("CREATE_DATE", start)
                .lt("CREATE_DATE", end)
//                .in("ORDER_NUM",needUpd)
        );
//        Assertions.assertTrue(false);
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
                    logger.error("通过最大值未能分割成功1--" + orderNum);
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
                    logger.error("通过最大值未能分割成功2--" + orderNum);
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
                    logger.error("未能解析到共有前缀--" + orderNum);
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
                logger.error("判断共有前缀及最大值最小值失败--" + orderNum);
                continue;
            }
            commonPrefixRelAttachRemark.put(newPackageSerial.getAttactRemark(), commonBuffer.toString());
        }
        return commonPrefixRelAttachRemark;
    }

    public void insertDataToRemoteDB(String key, String commonSerial) {
        List<NewPackageSerial> packageSerials = newPackageSerialService
                .list(new QueryWrapper<NewPackageSerial>().eq("attact_remark", key)
                        .groupBy("product_no")
                        // // TODO: 2022/10/20 标记需处理元素
//                        .eq("full_serial","1"));
                        );
        if (Objects.isNull(packageSerials) || packageSerials.size() == 0) {
            logger.error("查询失败 where key = " + key);
        } else {
            for (NewPackageSerial newPackageSerial : packageSerials) {
                insertSingleProductSerialGroupByAttach(newPackageSerial, key, commonSerial);
            }
        }
    }

    public void insertSingleProductSerialGroupByAttach(NewPackageSerial maxPackageSerial, String key, String commonSerial) {
        long ordernum = maxPackageSerial.getOrderNum();
        logger.info("开始插入----" + ordernum);
        try {
            Date createDate = new Date();
            String wipOrderNo = key.split("/")[1];
            CtSerialProductBatchsn ctSerialProductBatchsn = new CtSerialProductBatchsn();
//            ctSerialProductBatchsn.setActive((short) 1);
            ctSerialProductBatchsn.setProductno(maxPackageSerial.getProductNo());
            ctSerialProductBatchsn.setErpsnstart(key);
//            ctSerialProductBatchsn.setErpsnend(key);
//            ctSerialProductBatchsn.setBatchtype(new BigDecimal("1"));
//            ctSerialProductBatchsn.setIsprint(new BigDecimal("0"));
//            ctSerialProductBatchsn.setRowversionstamp(new BigDecimal("1"));
//            ctSerialProductBatchsn.setStatus(new BigDecimal("1"));
            ctSerialProductBatchsn.setWiporderno("Old-"+wipOrderNo);
            ctSerialProductBatchsn.setSeqstart(maxPackageSerial.getSerialStart().toString());
            ctSerialProductBatchsn.setSeqend(maxPackageSerial.getSerialEnd().toString());
            ctSerialProductBatchsnService.save(ctSerialProductBatchsn);
//            ctSerialProductBatchsnService.getOne(new QueryWrapper<CtSerialProductBatchsn>().eq("ERPSNSTART",key),false);
            BigDecimal batchid = ctSerialProductBatchsn.getBatchid();
            Integer maxRelAttachRemark = maxPackageSerial.getSerialEnd();
            String erpsnend = ctSerialProductBatchsn.getErpsnend();
            int step = 0;
            Pattern p = Pattern.compile(commonSerial + maxRelAttachRemark + "[A-G]");
            Matcher m = p.matcher(key);
            int totalRepeatCount = 0;
            while (m.find()) {
                totalRepeatCount++;
            }
            if (totalRepeatCount > 1) {
                NewPackageSerial packageSerial = newPackageSerialService
                        .getOne(new QueryWrapper<NewPackageSerial>()
                                .eq("attact_remark", key)
                                .eq("PRODUCT_NO", maxPackageSerial.getProductNo()));
                int currentSerial = packageSerial.getSerialStart();
                int serialEnd = packageSerial.getSerialEnd();
                List<CtSerialSn> listAbSerial = new ArrayList<>();
                while (currentSerial <= serialEnd) {
                    StringBuffer currentSerialBuffer = new StringBuffer(String.valueOf(currentSerial));
                    int max = packageSerial.getSerialEnd();
                    int length = String.valueOf(max).length();
                    while (currentSerialBuffer.length() < length) {
                        currentSerialBuffer.insert(0, 0);
                    }
                    int startAlphabet = 0;
                    while (startAlphabet < totalRepeatCount) {
                        CtSerialSn ctSerialSn = new CtSerialSn();
                        ctSerialSn.setSn(commonSerial + currentSerial + (char) ('A' + startAlphabet++));
                        ctSerialSn.setActive((short) 1);
                        ctSerialSn.setSrc(wipOrderNo);
                        ctSerialSn.setSrctype("WIPORDER");
                        ctSerialSn.setRuletype("BARE_MACHINE");
                        ctSerialSn.setRefencetype("PRODUCTION");
                        ctSerialSn.setReferenceno(maxPackageSerial.getProductNo());
                        ctSerialSn.setCreatedon(createDate);
                        ctSerialSn.setLastarchivedby("0");
                        ctSerialSn.setCreatedby("0");
                        ctSerialSn.setActive((short) 1);
                        ctSerialSn.setRowversionstamp(new BigDecimal("1"));
                        ctSerialSn.setBatchid(batchid);
                        ctSerialSn.setStatus(new BigDecimal("1"));
                        ctSerialSn.setQrcode(ctSerialSn.getQrcode());
                        ctSerialSn.setQuantity(new BigDecimal(1 + ""));
                        ctSerialSn.setErpsn(erpsnend);
                        listAbSerial.add(ctSerialSn);
                    }
                    currentSerial++;
                }
                ctSerialSnService.saveBatch(listAbSerial);
            } else {
                List<NewPackageSerial> needUpdData = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>()
                        .eq("ATTACT_REMARK", key)
                        .eq("PRODUCT_NO", maxPackageSerial.getProductNo())
                        .orderByAsc("order_num"));
                List<CtSerialSn> needUpdDataCtSerialSN = new ArrayList<>();
                NewPackageSerial newPackageSerial = needUpdData.get(0);
                int totalSerialSize = newPackageSerial.getSerialEnd() - newPackageSerial.getSerialStart() + 1;
                if (totalSerialSize != needUpdData.size()) {
//                    int tmp = maxPackageSerial.getSerialStart();
//                    while (tmp<=maxPackageSerial.getSerialEnd()){
//                        StringBuffer currentSerialBuffer = new StringBuffer(String.valueOf(tmp++));
//                        int max = maxPackageSerial.getSerialEnd();
//                        int length = String.valueOf(max).length();
//                        while (currentSerialBuffer.length() < length) {
//                            currentSerialBuffer.insert(0, 0);
//                        }
//                        Integer ean13 = null;
//
//                        CtSerialSn ctSerialSn = new CtSerialSn();
//                        ctSerialSn.setSn(commonSerial + currentSerialBuffer.toString() + (ean13 == null ? "" : ean13.toString()));
//                        ctSerialSn.setSrc(wipOrderNo);
////                        ctSerialSn.setSrctype("WIPORDER");
////                        ctSerialSn.setRuletype("BARE_MACHINE");
////                        ctSerialSn.setRefencetype("PRODUCTION");
//                        ctSerialSn.setReferenceno(maxPackageSerial.getProductNo());
//                        ctSerialSn.setCreatedon(createDate);
////                        ctSerialSn.setLastarchivedby("0");
////                        ctSerialSn.setCreatedby("0");
////                        ctSerialSn.setActive((short) 1);
////                        ctSerialSn.setRowversionstamp(new BigDecimal("1"));
//                        ctSerialSn.setBatchid(batchid);
////                        ctSerialSn.setStatus(new BigDecimal("1"));
//                        ctSerialSn.setQrcode(ctSerialSn.getQrcode());
//                        ctSerialSn.setQuantity(new BigDecimal(0.5));
//                        ctSerialSn.setErpsn(erpsnend);
//                        needUpdDataCtSerialSN.add(ctSerialSn);
//                    }
//
//                    ctSerialSnService.saveBatch(needUpdDataCtSerialSN);
//                    Assertions.assertTrue(false);
                    int repeatCount = needUpdData.size() / totalSerialSize;
                    for (NewPackageSerial packageSerial : needUpdData) {
                        int currentSerial = packageSerial.getSerialStart();

                        if (step % repeatCount != 0) {
                            step++;
                            continue;
                        }
                        currentSerial = currentSerial + step / repeatCount;
                        StringBuffer currentSerialBuffer = new StringBuffer(String.valueOf(currentSerial));
                        int max = packageSerial.getSerialEnd();
                        int length = String.valueOf(max).length();
                        while (currentSerialBuffer.length() < length) {
                            currentSerialBuffer.insert(0, 0);
                        }
                        Integer ean13 = packageSerial.getEan13();

                        CtSerialSn ctSerialSn = new CtSerialSn();
                        ctSerialSn.setSn(commonSerial + currentSerial + (ean13 == null ? "" : ean13.toString()));
                        ctSerialSn.setSrc(wipOrderNo);
//                        ctSerialSn.setSrctype("WIPORDER");
//                        ctSerialSn.setRuletype("BARE_MACHINE");
//                        ctSerialSn.setRefencetype("PRODUCTION");
                        ctSerialSn.setReferenceno(maxPackageSerial.getProductNo());
                        ctSerialSn.setCreatedon(createDate);
//                        ctSerialSn.setLastarchivedby("0");
//                        ctSerialSn.setCreatedby("0");
//                        ctSerialSn.setActive((short) 1);
//                        ctSerialSn.setRowversionstamp(new BigDecimal("1"));
                        ctSerialSn.setBatchid(batchid);
//                        ctSerialSn.setStatus(new BigDecimal("1"));
                        ctSerialSn.setQrcode(ctSerialSn.getQrcode());
                        ctSerialSn.setQuantity(new BigDecimal(repeatCount));
                        ctSerialSn.setErpsn(erpsnend);
                        needUpdDataCtSerialSN.add(ctSerialSn);
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
                        Integer ean13 = packageSerial.getEan13();

                        CtSerialSn ctSerialSn = new CtSerialSn();
                        ctSerialSn.setSn(commonSerial + currentSerialBuffer + addX + (ean13 == null ? "" : ean13.toString()));
                        ctSerialSn.setSrc(wipOrderNo);
//                        ctSerialSn.setSrctype("WIPORDER");
//                        ctSerialSn.setRuletype("BARE_MACHINE");
//                        ctSerialSn.setRefencetype("PRODUCTION");
                        ctSerialSn.setReferenceno(maxPackageSerial.getProductNo());
                        ctSerialSn.setCreatedon(createDate);
//                        ctSerialSn.setLastarchivedby("0");
//                        ctSerialSn.setCreatedby("0");
                        ctSerialSn.setActive((short) 1);
//                        ctSerialSn.setRowversionstamp(new BigDecimal(1));
                        ctSerialSn.setBatchid(batchid);
//                        ctSerialSn.setStatus(new BigDecimal(1));
                        ctSerialSn.setQrcode(ctSerialSn.getQrcode());
                        ctSerialSn.setQuantity(new BigDecimal(1));
                        ctSerialSn.setErpsn(erpsnend);
                        needUpdDataCtSerialSN.add(ctSerialSn);
                        step++;
                    }
                }
                ctSerialSnService.saveBatch(needUpdDataCtSerialSN);
            }
        } catch (Exception e) {
            logger.error("插入失败----" + ordernum, e);
        }
    }

    public void handleRangeDate(Date start, Date end) {
        HashMap<String, String> commonPrefixRelAttachRemark = getCommonSerialByAttach(start, end);
        commonPrefixRelAttachRemark.forEach(this::insertDataToRemoteDB);
    }
}
package cn.geny.ori.generate;


import cn.geny.ori.model.*;
import cn.geny.ori.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@SpringBootTest
public class TestCase {


    @Autowired
    private GenerateToLocalService generateToLocalService;

    @Autowired
    private NewPackageSerialService newPackageSerialService;

    @Autowired
    private ImportSerialToLocal importSerialToLocal;

    @Autowired
    private ImportSerialToRemote importSerialToRemote;


    // 检查通过最大值分割后字符串有多段
    @Test
    public void checkExistDuplicateDB() {
        List<NewPackageSerial> list = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>().groupBy("ATTACT_REMARK"));
        for (NewPackageSerial newPackageSerial : list) {
            Long orderNum = newPackageSerial.getOrderNum();
            Integer serialEnd = newPackageSerial.getSerialEnd();
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
                System.out.println(orderNum);
            }
        }
    }


    // 检查是否存在最大值与最小值之间是否存在已被使用订单
    @Test
    public void checkDataIntegrity() {
        List<NewPackageSerial> list = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>().groupBy("ATTACT_REMARK"));
        for (NewPackageSerial newPackageSerial : list) {
            String attactRemark = newPackageSerial.getAttactRemark();
            List<NewPackageSerial> groupByAttachList = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>().eq("ATTACT_REMARK", attactRemark));
            int length = groupByAttachList.size();
            int reallyTotalLength = newPackageSerial.getSerialEnd() - newPackageSerial.getSerialStart() + 1;
            if (length != reallyTotalLength) {
                System.out.println(newPackageSerial.getOrderNum());
            }
        }
    }



    // 开始插入数据
    @Test
    public void checkCommonPrefixIsRight() throws ParseException, InterruptedException {

//        CtSerialProductBatchsn.IMPORT_FLAG = "import20220701";
//
//        Date starta = DateUtils.parseDate("2022/07/01", "yyyy/MM/dd");
//        Date enda = DateUtils.addDays(starta, 12);
//        Date endb = DateUtils.addDays(enda, 8);
//        Date endc = DateUtils.addDays(endb, 6);
//
//        Date start = endc;
//        Date end1 = DateUtils.addDays(start, 1);
//        Date end2 = DateUtils.addDays(end1, 1);
//        Date end3 = DateUtils.addMonths(starta, 1);

//        CtSerialProductBatchsn.IMPORT_FLAG = "import20220801";
//        Date starta = DateUtils.parseDate("2022/08/01", "yyyy/MM/dd");
//        Date enda = DateUtils.addDays(starta, 7);
//        Date endb = DateUtils.addDays(enda, 7);
//        Date endc = DateUtils.addDays(endb, 6);
//
//        Date start = endc;
//        Date end1 = DateUtils.addDays(start, 4);
//        Date end2 = DateUtils.addDays(end1, 3);
//        Date end3 = DateUtils.addMonths(starta, 1);
//
//        System.out.println(end1);
//        System.out.println(end2);
//        System.out.println(end3);

        CtSerialProductBatchsn.IMPORT_FLAG = "import20220801-err";

        Date start = DateUtils.parseDate("2022/08/01", "yyyy/MM/dd");
        Date end1 = DateUtils.addDays(DateUtils.addMonths(start, 1),14);
        Date end2 = DateUtils.addDays(end1, 4);
        Date end3 = DateUtils.addMonths(end2, 4);

//        importSerialToRemote.handleRangeDate(start,end3);

//        Queue<Integer> doneStatus = new ArrayDeque<>();
//
//        new Thread(()->{
//            importSerialToRemote.handleRangeDate(start,end1);
//            doneStatus.add(1);
//        }).start();
//
//        new Thread(()->{
//            importSerialToRemote.handleRangeDate(end1,end2);
//            doneStatus.add(2);
//        }).start();
//
//        new Thread(()->{
//            importSerialToRemote.handleRangeDate(end2,end3);
//            doneStatus.add(3);
//        }).start();
//
//        while (doneStatus.size()!=3){
//            Thread.sleep(10000l);
//        }
    }
}
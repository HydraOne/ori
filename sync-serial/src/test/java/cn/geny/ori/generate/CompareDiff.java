package cn.geny.ori.generate;

import cn.geny.ori.model.Element;
import cn.geny.ori.model.NewPackageSerial;
import cn.geny.ori.model.NewPackageSerialBak;
import cn.geny.ori.model.PackageSerial;
import cn.geny.ori.service.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class CompareDiff {

    Logger logger = LoggerFactory.getLogger(CompareDiff.class);
    @Autowired
    private NewPackageSerialService newPackageSerialService;

    @Autowired
    private CtSerialSnService ctSerialSnService;

    @Autowired
    private CtSerialProductBatchsnService ctSerialProductBatchsnService;

    @Autowired
    private NewPackageSerialBakService newPackageSerialBakService;

    @Autowired
    private AbSerialService abSerialService;


    @Test
    public void compare() throws Exception {
        Date start = DateUtils.parseDate("2022/09/01", "yyyy/MM/dd");
        List<NewPackageSerial> list = newPackageSerialService.list(new QueryWrapper<NewPackageSerial>()
                .ge("create_date", start)
                .groupBy("attact_remark")
                .groupBy("product_no"));

        Set<Element> elements = new HashSet<>();
        list.forEach(item -> {
            Element element = new Element(item.getProductNo(), item.getAttactRemark());
            elements.add(element);
        });


        Date start1 = DateUtils.parseDate("2022/09/01", "yyyy/MM/dd");
        List<NewPackageSerialBak> lists = newPackageSerialBakService.list(new QueryWrapper<NewPackageSerialBak>()
                .ge("create_date", start)
                .groupBy("attact_remark")
                .groupBy("product_no"));

        List<Element> needUpd = new ArrayList<>();

        lists.forEach(item -> {
            Element element = new Element(item.getProductNo(), item.getAttactRemark());
            if (!elements.contains(element)) {
                needUpd.add(element);
            }
        });

        NewPackageSerialBak newPackageSerialBak = new NewPackageSerialBak();
        newPackageSerialBak.setFullSerial("1");

        needUpd.forEach(item -> {
                    newPackageSerialBakService.update(newPackageSerialBak, new QueryWrapper<NewPackageSerialBak>()
                            .eq("attact_remark",item.getAttachRemark())
                            .eq("product_no",item.getProductNo())
                    );
                }
        );

        Assertions.assertTrue(false);
    }

}

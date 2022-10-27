package cn.geny.ori.generate;


import cn.geny.ori.service.CreateWIPJSON;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@SpringBootTest
public class TestCase {

    @Autowired
    private CreateWIPJSON createWIPJSON;

    @Test
    public void testCase() throws Exception{

//        F810021022
//        0260065001
        System.setProperty("java.awt.headless","false");
        StringSelection stringSelection = new StringSelection(createWIPJSON.createWIPJSON("0260101002"));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection,null);
//        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/files/车间对应.xlsx"));
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        int lastRowNum = sheet.getLastRowNum();//查询一共有多少行
//        for (int i = 1; i < lastRowNum; i++) {
//            XSSFRow row = sheet.getRow(i);
//            String productNo = row.getCell(0).getStringCellValue();
//            try {
//                createWIPJSON.createWIPJSON(productNo);
//            }catch (Exception e){
//                row.createCell(10).setCellValue(e.getMessage());
//            }
//        }
//        @Cleanup
//        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/结果.xlsx");
//        workbook.write(fileOutputStream);
    }


    @Test
    public void getCurrentWeek() throws Exception{

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
        System.out.println(simpleDateFormat.format(new Date()));
    }

}
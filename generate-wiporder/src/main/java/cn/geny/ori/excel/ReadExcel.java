package cn.geny.ori.excel;

import cn.geny.ori.model.FacilityBinder;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ReadExcel {

    static Map<FacilityBinder, String> chervonWorkShop;

    static Map<String, FacilityBinder> chervonProductWorkShop;


    static {
        chervonWorkShop = createChervonWorkShop();
        chervonProductWorkShop = createChervonProductWorkShop();
    }



    @SneakyThrows
    public Map<FacilityBinder, String> createChervonWorkShop() {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/files/泉峰基础数据采集模板-V3.1.xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(7);
        int lastRowNum = sheet.getLastRowNum();//查询一共有多少行
        HashMap<FacilityBinder, String> map = new HashMap<>();
        for (int i = 3; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            String numericCellValue = null;
            CellType cellType = row.getCell(0).getCellType();
            if (cellType.getCode() == 1) {
                numericCellValue = row.getCell(0).getStringCellValue();
            } else {
                numericCellValue = String.valueOf(Math.round(row.getCell(0).getNumericCellValue()));
            }
            String workShopNo = row.getCell(1).getStringCellValue();
            String departmentNo = row.getCell(4).getStringCellValue();
            map.put(new FacilityBinder(workShopNo, departmentNo), numericCellValue);
        }
        return map;
    }

    @SneakyThrows
    public Map<String, FacilityBinder> createChervonProductWorkShop() {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/files/车间对应.xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();//查询一共有多少行
        HashMap<String, FacilityBinder> map = new HashMap<>();
        for (int i = 1; i < lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            String productNo = row.getCell(0).getStringCellValue();
            String workShopNo = row.getCell(4).getStringCellValue();
            String departmentNo = row.getCell(5).getStringCellValue();
            map.put(productNo, new FacilityBinder(workShopNo, departmentNo));
        }
        return map;
    }

    public String getChervonProductWorkShop(String productNo) {
        return chervonWorkShop.get(chervonProductWorkShop.get(productNo));
    }

}
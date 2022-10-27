package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物料扩展表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "CT_PRODUCT_FACILITY")
public class CtProductFacility {
    public static final String COL_PRODUCTID = "PRODUCTID";
    public static final String COL_ERPDEPARTMENT = "ERPDEPARTMENT";
    public static final String COL_MODELNO = "MODELNO";
    public static final String COL_ITEMID = "ITEMID";
    public static final String COL_PRODUCTNO = "PRODUCTNO";
    public static final String COL_TEXTID = "TEXTID";
    public static final String COL_PRIMARY_UOM = "PRIMARY_UOM";
    public static final String COL_LONG_DESCRIPTION = "LONG_DESCRIPTION";
    public static final String COL_REVISION = "REVISION";
    public static final String COL_PART_MATURITY = "PART_MATURITY";
    public static final String COL_MATURITY_DESC = "MATURITY_DESC";
    public static final String COL_ERPMODEL = "ERPMODEL";
    public static final String COL_ITEM_STATUS = "ITEM_STATUS";
    public static final String COL_ATTRIBUTE1 = "ATTRIBUTE1";
    public static final String COL_ATTRIBUTE2 = "ATTRIBUTE2";
    public static final String COL_ATTRIBUTE3 = "ATTRIBUTE3";
    public static final String COL_ATTRIBUTE4 = "ATTRIBUTE4";
    public static final String COL_ATTRIBUTE5 = "ATTRIBUTE5";
    public static final String COL_ATTRIBUTE6 = "ATTRIBUTE6";
    public static final String COL_ATTRIBUTE7 = "ATTRIBUTE7";
    public static final String COL_ATTRIBUTE8 = "ATTRIBUTE8";
    public static final String COL_ATTRIBUTE9 = "ATTRIBUTE9";
    public static final String COL_ATTRIBUTE10 = "ATTRIBUTE10";
    public static final String COL_ATTRIBUTE11 = "ATTRIBUTE11";
    public static final String COL_ATTRIBUTE12 = "ATTRIBUTE12";
    public static final String COL_ATTRIBUTE13 = "ATTRIBUTE13";
    public static final String COL_ATTRIBUTE14 = "ATTRIBUTE14";
    public static final String COL_ATTRIBUTE15 = "ATTRIBUTE15";
    public static final String COL_ATTRIBUTE16 = "ATTRIBUTE16";
    public static final String COL_ATTRIBUTE17 = "ATTRIBUTE17";
    public static final String COL_ATTRIBUTE18 = "ATTRIBUTE18";
    public static final String COL_ATTRIBUTE19 = "ATTRIBUTE19";
    public static final String COL_ATTRIBUTE20 = "ATTRIBUTE20";
    public static final String COL_PLANNER_CODE = "PLANNER_CODE";
    public static final String COL_BUYER = "BUYER";
    public static final String COL_PRODUCT_CATEGORY1 = "PRODUCT_CATEGORY1";
    public static final String COL_PRODUCT_CATEGORY2 = "PRODUCT_CATEGORY2";
    public static final String COL_PRODUCT_CATEGORY3 = "PRODUCT_CATEGORY3";
    public static final String COL_PRODUCT_CATEGORY1_DESC_ZH = "PRODUCT_CATEGORY1_DESC_ZH";
    public static final String COL_PRODUCT_CATEGORY2_DESC_ZH = "PRODUCT_CATEGORY2_DESC_ZH";
    public static final String COL_PRODUCT_CATEGORY3_DESC_ZH = "PRODUCT_CATEGORY3_DESC_ZH";
    public static final String COL_PRODUCT_CATEGORY1_DESC = "PRODUCT_CATEGORY1_DESC";
    public static final String COL_PRODUCT_CATEGORY2_DESC = "PRODUCT_CATEGORY2_DESC";
    public static final String COL_PRODUCT_CATEGORY3_DESC = "PRODUCT_CATEGORY3_DESC";
    public static final String COL_F_PACKAGEING = "F_PACKAGEING";
    public static final String COL_F_LENGTH = "F_LENGTH";
    public static final String COL_F_WIDETH = "F_WIDETH";
    public static final String COL_F_HIGHT = "F_HIGHT";
    public static final String COL_S_LENGTH = "S_LENGTH";
    public static final String COL_S_WIDETH = "S_WIDETH";
    public static final String COL_S_HIGHT = "S_HIGHT";
    public static final String COL_S_WEIGHT = "S_WEIGHT";
    public static final String COL_T_LENGTH = "T_LENGTH";
    public static final String COL_T_WIDETH = "T_WIDETH";
    public static final String COL_T_HIGHT = "T_HIGHT";
    public static final String COL_S_METHOD = "S_METHOD";
    public static final String COL_T_METHOD = "T_METHOD";
    public static final String COL_S_PACKAGEING = "S_PACKAGEING";
    public static final String COL_T_PACKAGEING = "T_PACKAGEING";
    public static final String COL_TRACKINGCODE = "TRACKINGCODE";
    public static final String COL_PLANMETHOD = "PLANMETHOD";
    public static final String COL_SERIALCHANGE = "SERIALCHANGE";
    public static final String COL_BRAND = "BRAND";
    public static final String COL_PRODUCTTYPE = "PRODUCTTYPE";
    public static final String COL_SUPPLY_TYPE = "SUPPLY_TYPE";
    public static final String COL_WIP_SUPPLY_SUBINVENTORY = "WIP_SUPPLY_SUBINVENTORY";
    public static final String COL_SUPPLY_SUBINVENTORY_LOCATOR = "SUPPLY_SUBINVENTORY_LOCATOR";
    public static final String COL_RESULT = "RESULT";
    public static final String COL_MESSAGE = "MESSAGE";
    public static final String COL_OUTSOURCING_FLAG = "OUTSOURCING_FLAG";
    public static final String COL_REFERENCEID = "REFERENCEID";
    public static final String COL_LASTUPDATEON = "LASTUPDATEON";
    public static final String COL_LASTUPDATEDBY = "LASTUPDATEDBY";
    public static final String COL_CREATEDON = "CREATEDON";
    public static final String COL_CREATEDBY = "CREATEDBY";
    public static final String COL_ACTIVE = "ACTIVE";
    public static final String COL_LASTDELETEON = "LASTDELETEON";
    public static final String COL_LASTDELETEDBY = "LASTDELETEDBY";
    public static final String COL_LASTREACTIVATEON = "LASTREACTIVATEON";
    public static final String COL_LASTREACTIVATEDBY = "LASTREACTIVATEDBY";
    public static final String COL_ARCHIVEID = "ARCHIVEID";
    public static final String COL_LASTARCHIVEON = "LASTARCHIVEON";
    public static final String COL_LASTARCHIVEDBY = "LASTARCHIVEDBY";
    public static final String COL_LASTRESTOREON = "LASTRESTOREON";
    public static final String COL_LASTRESTOREDBY = "LASTRESTOREDBY";
    public static final String COL_ROWVERSIONSTAMP = "ROWVERSIONSTAMP";
    public static final String COL_SERIALTRACKINGCODE = "SERIALTRACKINGCODE";
    public static final String COL_LOTTRACKINGCODE = "LOTTRACKINGCODE";
    public static final String COL_CONTAINERQUANTITY = "CONTAINERQUANTITY";
    public static final String COL_PALLETQUANTITY = "PALLETQUANTITY";
    public static final String COL_PALLETCONTAINERQUANTITY = "PALLETCONTAINERQUANTITY";
    public static final String COL_SOURCE = "SOURCE";
    /**
     * MES物料ID
     */
    @TableField(value = "PRODUCTID")
    private BigDecimal productid;

    /**
     * 生产组织
     */
    @TableField(value = "ERPDEPARTMENT")
    private String erpdepartment;

    /**
     * 产品型号
     */
    @TableField(value = "MODELNO")
    private String modelno;

    /**
     * ERP物料ID
     */
    @TableField(value = "ITEMID")
    private BigDecimal itemid;

    /**
     * 物料编码
     */
    @TableField(value = "PRODUCTNO")
    private String productno;

    /**
     * 物料名称
     */
    @TableField(value = "TEXTID")
    private String textid;

    /**
     * 单位
     */
    @TableField(value = "PRIMARY_UOM")
    private String primaryUom;

    /**
     * 长描述，所有Spec属性汇总
     */
    @TableField(value = "LONG_DESCRIPTION")
    private String longDescription;

    /**
     * 版本
     */
    @TableField(value = "REVISION")
    private String revision;

    /**
     * 成熟度
     */
    @TableField(value = "PART_MATURITY")
    private String partMaturity;

    /**
     * 成熟度说明，长度可以增加
     */
    @TableField(value = "MATURITY_DESC")
    private String maturityDesc;

    /**
     * 型号
     */
    @TableField(value = "ERPMODEL")
    private String erpmodel;

    /**
     * 物料状态
     */
    @TableField(value = "ITEM_STATUS")
    private String itemStatus;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE1")
    private String attribute1;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE2")
    private String attribute2;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE3")
    private String attribute3;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE4")
    private String attribute4;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE5")
    private String attribute5;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE6")
    private String attribute6;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE7")
    private String attribute7;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE8")
    private String attribute8;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE9")
    private String attribute9;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE10")
    private String attribute10;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE11")
    private String attribute11;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE12")
    private String attribute12;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE13")
    private String attribute13;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE14")
    private String attribute14;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE15")
    private String attribute15;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE16")
    private String attribute16;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE17")
    private String attribute17;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE18")
    private String attribute18;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE19")
    private String attribute19;

    /**
     * 预留字段
     */
    @TableField(value = "ATTRIBUTE20")
    private String attribute20;

    /**
     * 计划员
     */
    @TableField(value = "PLANNER_CODE")
    private String plannerCode;

    /**
     * 采购员
     */
    @TableField(value = "BUYER")
    private String buyer;

    /**
     * 类别1
     */
    @TableField(value = "PRODUCT_CATEGORY1")
    private String productCategory1;

    /**
     * 类别2
     */
    @TableField(value = "PRODUCT_CATEGORY2")
    private String productCategory2;

    /**
     * 类别3
     */
    @TableField(value = "PRODUCT_CATEGORY3")
    private String productCategory3;

    /**
     * 类别1描述
     */
    @TableField(value = "PRODUCT_CATEGORY1_DESC_ZH")
    private String productCategory1DescZh;

    /**
     * 类别2描述
     */
    @TableField(value = "PRODUCT_CATEGORY2_DESC_ZH")
    private String productCategory2DescZh;

    /**
     * 类别2描述
     */
    @TableField(value = "PRODUCT_CATEGORY3_DESC_ZH")
    private String productCategory3DescZh;

    /**
     * 类别1描述
     */
    @TableField(value = "PRODUCT_CATEGORY1_DESC")
    private String productCategory1Desc;

    /**
     * 类别2描述
     */
    @TableField(value = "PRODUCT_CATEGORY2_DESC")
    private String productCategory2Desc;

    /**
     * 类别2描述
     */
    @TableField(value = "PRODUCT_CATEGORY3_DESC")
    private String productCategory3Desc;

    /**
     * 一级包装数量
     */
    @TableField(value = "F_PACKAGEING")
    private BigDecimal fPackageing;

    /**
     * 一级包装长
     */
    @TableField(value = "F_LENGTH")
    private BigDecimal fLength;

    /**
     * 一级包装宽
     */
    @TableField(value = "F_WIDETH")
    private BigDecimal fWideth;

    /**
     * 一级包装高
     */
    @TableField(value = "F_HIGHT")
    private BigDecimal fHight;

    /**
     * 二级包装长
     */
    @TableField(value = "S_LENGTH")
    private BigDecimal sLength;

    /**
     * 二级包装宽
     */
    @TableField(value = "S_WIDETH")
    private BigDecimal sWideth;

    /**
     * 二级包装高
     */
    @TableField(value = "S_HIGHT")
    private BigDecimal sHight;

    /**
     * 二级包装重
     */
    @TableField(value = "S_WEIGHT")
    private BigDecimal sWeight;

    /**
     * 三级包装长
     */
    @TableField(value = "T_LENGTH")
    private BigDecimal tLength;

    /**
     * 三级包装宽
     */
    @TableField(value = "T_WIDETH")
    private BigDecimal tWideth;

    /**
     * 三级包装高
     */
    @TableField(value = "T_HIGHT")
    private BigDecimal tHight;

    /**
     * 二级包装方式
     */
    @TableField(value = "S_METHOD")
    private String sMethod;

    /**
     * 三级包装方式
     */
    @TableField(value = "T_METHOD")
    private String tMethod;

    /**
     * 二级包装数量
     */
    @TableField(value = "S_PACKAGEING")
    private BigDecimal sPackageing;

    /**
     * 三级包装数量
     */
    @TableField(value = "T_PACKAGEING")
    private BigDecimal tPackageing;

    /**
     * 跟踪方式
     */
    @TableField(value = "TRACKINGCODE")
    private BigDecimal trackingcode;

    /**
     * 计划方法
     */
    @TableField(value = "PLANMETHOD")
    private String planmethod;

    /**
     * 序列号变化物料
     */
    @TableField(value = "SERIALCHANGE")
    private String serialchange;

    /**
     * 品牌
     */
    @TableField(value = "BRAND")
    private String brand;

    /**
     * 成品、子装配件、虚拟件、外购件、保税件（进口件）
     */
    @TableField(value = "PRODUCTTYPE")
    private String producttype;

    /**
     * 供应类型
     */
    @TableField(value = "SUPPLY_TYPE")
    private String supplyType;

    /**
     * 供应子库存
     */
    @TableField(value = "WIP_SUPPLY_SUBINVENTORY")
    private String wipSupplySubinventory;

    /**
     * 供应子库货位
     */
    @TableField(value = "SUPPLY_SUBINVENTORY_LOCATOR")
    private String supplySubinventoryLocator;

    /**
     * 传输结果
     */
    @TableField(value = "\"RESULT\"")
    private BigDecimal result;

    /**
     * 说明
     */
    @TableField(value = "MESSAGE")
    private String message;

    /**
     * 外协 Y/N
     */
    @TableField(value = "OUTSOURCING_FLAG")
    private String outsourcingFlag;

    @TableField(value = "REFERENCEID")
    private BigDecimal referenceid;

    @TableField(value = "LASTUPDATEON")
    private Date lastupdateon;

    @TableField(value = "LASTUPDATEDBY")
    private String lastupdatedby;

    @TableField(value = "CREATEDON")
    private Date createdon;

    @TableField(value = "CREATEDBY")
    private String createdby;

    @TableField(value = "ACTIVE")
    private Short active;

    @TableField(value = "LASTDELETEON")
    private Date lastdeleteon;

    @TableField(value = "LASTDELETEDBY")
    private String lastdeletedby;

    @TableField(value = "LASTREACTIVATEON")
    private Date lastreactivateon;

    @TableField(value = "LASTREACTIVATEDBY")
    private String lastreactivatedby;

    @TableField(value = "ARCHIVEID")
    private BigDecimal archiveid;

    @TableField(value = "LASTARCHIVEON")
    private Date lastarchiveon;

    @TableField(value = "LASTARCHIVEDBY")
    private String lastarchivedby;

    @TableField(value = "LASTRESTOREON")
    private Date lastrestoreon;

    @TableField(value = "LASTRESTOREDBY")
    private String lastrestoredby;

    @TableField(value = "ROWVERSIONSTAMP")
    private BigDecimal rowversionstamp;

    @TableField(value = "SERIALTRACKINGCODE")
    private BigDecimal serialtrackingcode;

    @TableField(value = "LOTTRACKINGCODE")
    private BigDecimal lottrackingcode;

    @TableField(value = "CONTAINERQUANTITY")
    private BigDecimal containerquantity;

    @TableField(value = "PALLETQUANTITY")
    private BigDecimal palletquantity;

    @TableField(value = "PALLETCONTAINERQUANTITY")
    private BigDecimal palletcontainerquantity;

    @TableField(value = "\"SOURCE\"")
    private String source;
}
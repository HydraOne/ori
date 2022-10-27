package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
@TableName(value = "CT_SERIAL_PRODUCT_BATCHSN")
public class CtSerialProductBatchsn {
    public static String IMPORT_FLAG;

    @TableId(value = "BATCHID", type = IdType.AUTO)
    private BigDecimal batchid;

    @TableField(value = "FACILITY")
    private String facility;

    @TableField(value = "PACKAGERULEID")
    private BigDecimal packageruleid;

    @TableField(value = "WIPORDERNO")
    private String wiporderno;

    /**
     * 批次类型：0 无订单，1 有订单，2 MES码
     */
    @TableField(value = "BATCHTYPE")
    private BigDecimal batchtype = new BigDecimal(1);

    @TableField(value = "PRODUCTNO")
    private String productno;

    @TableField(value = "PRODUCEDATE")
    private Date producedate;

    @TableField(value = "BATCHNUM")
    private BigDecimal batchnum;

    @TableField(value = "SNSTART")
    private String snstart;

    @TableField(value = "SNEND")
    private String snend;

    @TableField(value = "ISPRINT")
    private BigDecimal isprint = new BigDecimal(0);

    @TableField(value = "REMARK")
    private String remark;

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
    private Short active = 1;

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
    private BigDecimal rowversionstamp = new BigDecimal("1");

    @TableField(value = "SNPREFIX")
    private String snprefix;

    @TableField(value = "RULENO")
    private String ruleno = IMPORT_FLAG;

    @TableField(value = "ERPSNSTART")
    private String erpsnstart;

    @TableField(value = "ERPSNEND")
    private String erpsnend = UUID.randomUUID().toString();

    @TableField(value = "SEQSTART")
    private String seqstart;

    @TableField(value = "SEQEND")
    private String seqend;

    @TableField(value = "SEQCOUNT")
    private BigDecimal seqcount;

    @TableField(value = "TOERP")
    private BigDecimal toerp;

    @TableField(value = "\"STATUS\"")
    private BigDecimal status = new BigDecimal(1);

    @TableField(value = "QUANTITYPER")
    private BigDecimal quantityper;

    @TableField(value = "BASE")
    private String base;

    @TableField(value = "TXTBASE")
    private String txtbase;

    @TableField(value = "BOMTYPE")
    private String bomtype;

    @TableField(value = "SUBBOMTYPE")
    private String subbomtype;

    @TableField(value = "PARENTPRODUCTID")
    private BigDecimal parentproductid;

    @TableField(value = "SAMEWITH")
    private String samewith;

    @TableField(value = "PARENTPRODUCTNO")
    private String parentproductno;

    @TableField(value = "SNPOSITION")
    private BigDecimal snposition;

    /**
     * 校验位位置
     */
    @TableField(value = "CHECKPOSITION")
    private BigDecimal checkposition;

    /**
     * 订单数量
     */
    @TableField(value = "QUANTITYORDER")
    private BigDecimal quantityorder;

    /**
     * 放量
     */
    @TableField(value = "RESERVED")
    private BigDecimal reserved;

    /**
     * 替换物料编码
     */
    @TableField(value = "REPLACEDPRODUCTNO")
    private String replacedproductno;

    /**
     * 是否德国年
     */
    @TableField(value = "ISGERMANYYEAR")
    private BigDecimal isgermanyyear;

    /**
     * 文本基准日期基准
     */
    @TableField(value = "CODEBASE")
    private String codebase;

    /**
     * GUID
     */
    @TableField(value = "GUIDBATCH")
    private String guidbatch;
}
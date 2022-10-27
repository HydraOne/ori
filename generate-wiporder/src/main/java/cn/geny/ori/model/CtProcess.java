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
    * 工艺扩展表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "CT_PROCESS")
public class CtProcess {
    /**
     * 工艺ID
     */
    @TableId(value = "PROCESSID", type = IdType.INPUT)
    private BigDecimal processid;

    /**
     * 物料ID
     */
    @TableField(value = "PRODUCTID")
    private BigDecimal productid;

    /**
     * BOM替代项
     */
    @TableField(value = "BOMNUMBER")
    private String bomnumber;

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

    /**
     * 事业部
     */
    @TableField(value = "DIVISION")
    private String division;

    @TableField(value = "PLANER")
    private String planer;

    @TableField(value = "PLANERNAME")
    private String planername;

    @TableField(value = "ERPORG")
    private String erporg;

    @TableField(value = "FACILITY")
    private String facility;
}
package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 生成序列号与车间表
 */
@Data
@TableName(value = "CT_SERIAL_MES")
public class CtSerialMes {
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 公司
     */
    @TableField(value = "FACILITY")
    private String facility;

    /**
     * 事业部
     */
    @TableField(value = "DEPARTMENT")
    private String department;

    /**
     * 车间
     */
    @TableField(value = "WORKSHOP")
    private String workshop;

    @TableField(value = "RULENO")
    private String ruleno;

    /**
     * 固定值
     */
    @TableField(value = "FIXEDVALUE")
    private String fixedvalue;

    /**
     * 模板编码
     */
    @TableField(value = "TEMPLATENO")
    private String templateno;

    @TableField(value = "FUID")
    private String fuid;

    @TableField(value = "TEXTID")
    private BigDecimal textid;

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
}
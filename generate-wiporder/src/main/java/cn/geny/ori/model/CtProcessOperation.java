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
    * 工艺工序扩展表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "CT_PROCESS_OPERATION")
public class CtProcessOperation {
    /**
     * 工艺工序ID
     */
    @TableId(value = "PROCESSOPERATIONID", type = IdType.INPUT)
    private BigDecimal processoperationid;

    /**
     * 工艺ID
     */
    @TableField(value = "PROCESSID")
    private BigDecimal processid;

    @TableField(value = "OPERATIONCODE")
    private String operationcode;

    /**
     * 产能
     */
    @TableField(value = "CAPACITY")
    private BigDecimal capacity;

    /**
     * 人力
     */
    @TableField(value = "MANPOWER")
    private BigDecimal manpower;

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

    @TableField(value = "OPERATIONNO")
    private String operationno;

    @TableField(value = "OPRSEQUENCENO")
    private String oprsequenceno;

    @TableField(value = "TEXTID")
    private BigDecimal textid;

    @TableField(value = "ISREPORT")
    private BigDecimal isreport;
}
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "CT_FACILITY")
public class CtFacility {
    @TableId(value = "ID",type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 存储FACILITY
     */
    @TableField(value = "FACILITY")
    private String facility;

    /**
     * 关联ERP_DEPARTMENT
     */
    @TableField(value = "ERPDEPARTMENTID")
    private BigDecimal erpdepartmentid;

    /**
     * ERP 部门代码
     */
    @TableField(value = "ERPDEPARTMENT")
    private String erpdepartment;

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

    @TableField(value = "DASHBOARDBUSINESSOBJECTID")
    private BigDecimal dashboardbusinessobjectid;

    @TableField(value = "COUNTRYOFBIRTH")
    private String countryofbirth;

    @TableField(value = "DATEOFBIRTH")
    private Date dateofbirth;

    /**
     * 车间名称扩展
     */
    @TableField(value = "FACILITYNAME")
    private String facilityname;

    @TableField(value = "COMPANY")
    private String company;

    @TableField(value = "DIVISION")
    private String division;

    @TableField(value = "TEXTID")
    private BigDecimal textid;

    /**
     * 完工子库
     */
    @TableField(value = "COMPLETESUB")
    private String completesub;
}
package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "CT_SERIAL_SN")
public class CtSerialSn {
    @TableId(value = "SNID",type = IdType.ASSIGN_ID)
    private BigDecimal snid;

    @TableField(value = "FACILITY")
    private String facility;

    @TableField(value = "DEPARTMENT")
    private String department;

    @TableField(value = "SN")
    private String sn;

    @TableField(value = "SRC")
    private String src;

    @TableField(value = "SRCTYPE")
    private String srctype = "WIPORDER";

    @TableField(value = "RULEID")
    private BigDecimal ruleid;

    @TableField(value = "RULETYPE")
    private String ruletype = "BARE_MACHINE";

    @TableField(value = "REFENCETYPE")
    private String refencetype = "PRODUCTION";

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
    private String createdby = "0";

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
    private String lastarchivedby = "0";

    @TableField(value = "LASTRESTOREON")
    private Date lastrestoreon;

    @TableField(value = "LASTRESTOREDBY")
    private String lastrestoredby;

    @TableField(value = "ROWVERSIONSTAMP")
    private BigDecimal rowversionstamp = new BigDecimal(1);

    @TableField(value = "REFERENCENO")
    private String referenceno;

    @TableField(value = "ISRESERVED")
    private BigDecimal isreserved;

    @TableField(value = "QUANTITY")
    private BigDecimal quantity;

    @TableField(value = "ERPSN")
    private String erpsn;

    @TableField(value = "SEQ")
    private String seq;

    @TableField(value = "BATCHID")
    private BigDecimal batchid;

    @TableField(value = "\"STATUS\"")
    private BigDecimal status = new BigDecimal(1);

    /**
     * 二维码
     */
    @TableField(value = "QRCODE")
    private String qrcode;

    /**
     * guid
     */
    @TableField(value = "GUIDSN")
    private String guidsn;
}
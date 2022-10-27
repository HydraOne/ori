package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "CHERVON_MESEX.FUS_MES_ITEM_SN_V@MESEX.NJ.CHERVON.COM")
public class OldPackageSerial {

    @TableField(value = "物料")
    private String productNo;

    @TableField(value = "物料名称")
    private String productName;

    @TableField(value = "客户")
    private String customer;

    @TableField(value = "型号")
    private String model;

    @TableField(value = "序列开始")
    private Integer serialStart;

    @TableField(value = "序列截止")
    private Integer serialEnd;

    @TableField(value = "PO接收人附注")
    private String attactRemark;

    @TableField(value = "是否放量")
    private String isDischarge;

    @TableField(value = "创建日期")
    private Date createDate;

    @TableField(value = "EAN13校验码")
    private Integer ean13;

    @TableField(value = "个性化序列")
    private String indiviSerial;

    @TableField(value = "德国周")
    private Integer germanWeek;
}
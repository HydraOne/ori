package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "package_serial")
public class PackageSerial {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "PRODUCT_NO")
    private String productNo;

    @TableField(value = "PRODUCT_NAME")
    private String productName;

    @TableField(value = "CUSTOMER")
    private String customer;

    @TableField(value = "MODEL")
    private String model;

    @TableField(value = "SERIAL_START")
    private Integer serialStart;

    @TableField(value = "SERIAL_END")
    private Integer serialEnd;

    @TableField(value = "ATTACT_REMARK")
    private String attactRemark;

    @TableField(value = "IS_DISCHARGE")
    private String isDischarge;

    @TableField(value = "CREATE_DATE")
    private String createDate;

    @TableField(value = "EAN13")
    private Integer ean13;

    @TableField(value = "INDIVI_SERIAL")
    private String indiviSerial;

    @TableField(value = "GERMAN_WEEK")
    private Integer germanWeek;

    @TableField(value = "FULL_SERIAL")
    private String fullSerial;
}
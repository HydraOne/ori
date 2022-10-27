package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "ab_serial")
public class AbSerial {
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    @TableField(value = "ORDER_NUM")
    private Long orderNum;

    @TableField(value = "FULL_SERIAL")
    private String fullSerial;
}
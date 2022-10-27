package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "wait_handle_product")
public class WaitHandleProduct {
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "PRODUCT_NO")
    private String productNo;

    @TableField(value = "STASTUS")
    private String stastus;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @TableField(value = "BATCHID")
    private Long batchid;
}
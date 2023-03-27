package com.ey.eshop.inventory.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("inventory_log")
public class InventoryLogDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 来源类型
     *
     * @see com.ey.eshop.inventory.enums.InventoryLogSourceType
     */
    private Integer sourceType;
    /**
     * 来源ID
     */
    private Long sourceId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 记录数量
     */
    private Double logCount;

    private Date createTime;
    private Date modifyTime;
}

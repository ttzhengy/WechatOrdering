package com.yat.wechatorderingsystem.entity;

import com.yat.wechatorderingsystem.enums.OrderStatusEnums;
import com.yat.wechatorderingsystem.enums.PayStatusEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderMaster {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = 0;
    private Integer payStatus = 0;
    // private Integer orderStatus = OrderStatusEnums.NEW.getStatus();
    // private Integer payStatus = PayStatusEnums.WAIT.getStatus();
}

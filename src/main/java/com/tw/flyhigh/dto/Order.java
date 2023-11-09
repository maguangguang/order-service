package com.tw.flyhigh.dto;

import com.tw.flyhigh.entity.PassengerEntity;
import com.tw.flyhigh.entity.TicketOrderEntity;
import com.tw.flyhigh.entity.TicketOrderEventEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel("机票订单信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @ApiModelProperty("用户Id")
    Long userId;
    @ApiModelProperty("乘机人信息")
    Long id;
    @ApiModelProperty("乘机人信息")
    private List<Passenger> passengerList;
    @ApiModelProperty("乘机人信息")
    private List<OrderEvent> orderEventList;
    @ApiModelProperty("航班编号")
    private String flight;
    @ApiModelProperty("座舱类型")
    private String classType;
    @ApiModelProperty("联系人电话")
    private String contactMobile;
    @ApiModelProperty("联系人姓名")
    private String contactName;
    @ApiModelProperty("订单状态")
    private String status;
    @ApiModelProperty("创建时间")
    LocalDateTime createdAt;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Passenger {
        @ApiModelProperty("乘客Id")
        private Long id;
        @ApiModelProperty("用户名")
        private String name;
        @ApiModelProperty("年龄段")
        private String ageType;
        @ApiModelProperty("身份证")
        private String identificationNumber;
        @ApiModelProperty("手机号")
        private String mobile;
        @ApiModelProperty("票价")
        private Integer price;
        @ApiModelProperty("购买行李托运重量")
        private Integer baggageWeight;
        @ApiModelProperty("保险Id")
        private String insuranceId;
        @ApiModelProperty("保险名称")
        private String insuranceName;
        @ApiModelProperty("保险费用")
        private Integer insurancePrice;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderEvent {
        @ApiModelProperty("订单Id")
        private Long id;
        @ApiModelProperty("状态")
        private String status;
        @ApiModelProperty("创建时间")
        private LocalDateTime createdAt;
    }

    public static Order from(TicketOrderEntity ticketOrderEntity) {
        Order order = new Order();
        BeanUtils.copyProperties(ticketOrderEntity, order);
        return order;
    }

    public static List<Passenger> transferPassenger(List<PassengerEntity> passengerEntities) {
        return passengerEntities.stream().map(passengerEntity -> {
            Order.Passenger passenger = Order.Passenger.builder().build();
            BeanUtils.copyProperties(passengerEntity, passenger);
            return passenger;
        }).collect(Collectors.toList());
    }

    public static List<OrderEvent> transferOrderEvent(List<TicketOrderEventEntity> orderEventEntities) {
        return orderEventEntities.stream().map(orderEventEntity -> {
            Order.OrderEvent orderEvent = Order.OrderEvent.builder().build();
            BeanUtils.copyProperties(orderEventEntity, orderEvent);
            return orderEvent;
        }).collect(Collectors.toList());
    }
}

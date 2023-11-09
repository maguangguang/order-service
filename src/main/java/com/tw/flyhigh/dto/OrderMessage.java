package com.tw.flyhigh.dto;

import com.tw.flyhigh.entity.PassengerEntity;
import com.tw.flyhigh.entity.TicketOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage {
    Long id;
    Long userId;
    private List<Passenger> passengerList;
    private String flight;
    private String classType;
    private String contactMobile;
    private String contactName;
    private String status;
    LocalDateTime lastUpdated;
    LocalDateTime createdAt;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Passenger {
        private String name;
        private String ageType;
        private String identificationNumber;
        private String mobile;
        private Integer price;
        private Integer baggageWeight;
        private String insuranceId;
        private String insuranceName;
        private Integer insurancePrice;
    }


    public static OrderMessage from(TicketOrderEntity ticketOrderEntity) {
        OrderMessage orderDto = new OrderMessage();
        BeanUtils.copyProperties(ticketOrderEntity, orderDto);
        return orderDto;
    }

    public static List<OrderMessage.Passenger> transferPassenger(List<PassengerEntity> passengerEntities) {
        return passengerEntities.stream().map(passengerEntity -> {
            OrderMessage.Passenger passenger = OrderMessage.Passenger.builder().build();
            BeanUtils.copyProperties(passengerEntity, passenger);
            return passenger;
        }).collect(Collectors.toList());
    }
}

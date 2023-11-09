package com.tw.flyhigh;

import com.tw.flyhigh.common.util.DateTimeUtil;
import com.tw.flyhigh.dto.CreateOrderDto;
import com.tw.flyhigh.dto.Order;
import com.tw.flyhigh.entity.PassengerEntity;
import com.tw.flyhigh.entity.PlaneTicketEntity;
import com.tw.flyhigh.entity.TicketOrderEntity;
import com.tw.flyhigh.entity.TicketOrderEventEntity;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

public class OrderFixture {
    public static LocalDateTime createTime = LocalDateTime.now();

    public static CreateOrderDto getCreateOrderDto() {
        return CreateOrderDto.builder()
                             .contactMobile("13888888888")
                             .contactName("张三")
                             .classType("FIRST")
                             .flight("MU2151")
                             .userId(12L)
                             .flightDate("2022-03-10")
                             .passengerDtoList(newArrayList(getPassengerDto()))
                             .build();
    }

    public static CreateOrderDto getCreateOrderDtoWithInvalidIdentityNumber() {
        return CreateOrderDto.builder()
                             .contactMobile("13888888888")
                             .contactName("张三")
                             .classType("FIRST")
                             .flight("MU2151")
                             .userId(12L)
                             .flightDate("2022-03-10")
                             .passengerDtoList(newArrayList(getPassengerDtoWithInvalidIdentityNumber()))
                             .build();
    }

    private static CreateOrderDto.PassengerDto getPassengerDtoWithInvalidIdentityNumber() {
        CreateOrderDto.PassengerDto passengerDto = getPassengerDto();
        passengerDto.setIdentificationNumber("12345678");
        return passengerDto;
    }

    public static CreateOrderDto.PassengerDto getPassengerDto() {
        return CreateOrderDto.PassengerDto.builder()
                                          .name("李四")
                                          .ageType("老人")
                                          .baggageWeight(30)
                                          .identificationNumber("610502200001015432")
                                          .insuranceId("666")
                                          .insuranceName("一路顺风")
                                          .insurancePrice(20)
                                          .mobile("13866668888")
                                          .price(200)
                                          .build();
    }

    public static Order getOrder() {
        return Order.builder()
                    .contactMobile("13888888888")
                    .contactName("张三")
                    .classType("FIRST")
                    .flight("MU2151")
                    .userId(12L)
                    .status("CREATED")
                    .orderEventList(newArrayList(getOrderEvent()))
                    .passengerList(newArrayList(getPassenger()))
                    .build();
    }

    public static Order.OrderEvent getOrderEvent() {
        return Order.OrderEvent.builder()
                               .status("CREATED")
                               .build();
    }

    public static Order.Passenger getPassenger() {
        return Order.Passenger.builder()
                              .name("李四")
                              .ageType("老人")
                              .baggageWeight(30)
                              .identificationNumber("610502200001015432")
                              .insuranceId("666")
                              .insuranceName("一路顺风")
                              .insurancePrice(20)
                              .mobile("13866668888")
                              .price(200)
                              .build();
    }

    public static TicketOrderEntity getOrderEntity() {
        return TicketOrderEntity.builder()
                                .contactMobile("13888888888")
                                .contactName("张三")
                                .classType("FIRST")
                                .flight("MU2151")
                                .userId(12L)
                                .passengers(Collections.singletonList(getPassengerEntity()))
                                .events(Collections.singletonList(getOrderEventEntity()))
                                .build();
    }

    public static PassengerEntity getPassengerEntity() {
        return PassengerEntity.builder()
                              .id(1234L)
                              .name("李四")
                              .ageType("老人")
                              .baggageWeight(30)
                              .identificationNumber("610502200001015432")
                              .insuranceId("666")
                              .insuranceName("一路顺风")
                              .insurancePrice(20)
                              .mobile("13866668888")
                              .price(200)
                              .build();
    }

    public static PlaneTicketEntity getTicketEntity() throws ParseException {
        return PlaneTicketEntity.builder()
                                .ticketNo("2022030101234567890")
                                .eTicketNo("112233445566")
                                .boardingDate(DateTimeUtil.formatToDate("20200222120222"))
                                .boardingTime("20200222120222")
                                .destination("上海")
                                .flight("MU2151")
                                .gate("H55")
                                .price(200)
                                .seatType("K")
                                .seat("33")
                                .sn(250)
                                .build();
    }

    public static TicketOrderEventEntity getOrderEventEntity() {
        return TicketOrderEventEntity.builder()
                                     .status("CREATED")
                                     .build();
    }
}

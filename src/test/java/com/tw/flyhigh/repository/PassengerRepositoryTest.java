package com.tw.flyhigh.repository;

import com.tw.flyhigh.entity.PassengerEntity;
import com.tw.flyhigh.entity.TicketOrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PassengerRepositoryTest {
    @Autowired
    private TicketOrderRepository orderRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    @Test
    public void should_save_and_find_success() {
        TicketOrderEntity ticketOrderEntity = TicketOrderEntity.builder()
            .contactMobile("13888888888")
            .contactName("张三")
            .classType("FIRST")
            .flight("MU2151")
            .userId(123L)
            .build();

        orderRepository.save(ticketOrderEntity);

        PassengerEntity entity = passengerRepository.save(PassengerEntity.builder()
            .name("李四")
            .ageType("老人")
            .baggageWeight(30)
            .identificationNumber("610502200001015432")
            .insuranceId("666")
            .insuranceName("一路顺风")
            .insurancePrice(20)
            .mobile("13866668888")
            .price(200)
            .order(ticketOrderEntity)
            .build());

        Optional<PassengerEntity> passengerEntityOptional = passengerRepository.findById(entity.getId());
        Assertions.assertTrue(passengerEntityOptional.isPresent());
        PassengerEntity passengerEntity = passengerEntityOptional.get();
        Assertions.assertEquals("老人", passengerEntity.getAgeType());
        Assertions.assertEquals("610502200001015432", passengerEntity.getIdentificationNumber());
        Assertions.assertEquals("李四", passengerEntity.getName());
        Assertions.assertEquals("13866668888", passengerEntity.getMobile());
        Assertions.assertEquals("666", passengerEntity.getInsuranceId());
        Assertions.assertEquals("一路顺风", passengerEntity.getInsuranceName());
        Assertions.assertEquals(20, passengerEntity.getInsurancePrice());
        Assertions.assertEquals(30, passengerEntity.getBaggageWeight());
        Assertions.assertEquals(200, passengerEntity.getPrice());
    }
}

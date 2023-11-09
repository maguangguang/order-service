package com.tw.flyhigh.repository;

import com.tw.flyhigh.entity.TicketOrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private TicketOrderRepository orderRepository;

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

        List<TicketOrderEntity> orderList = orderRepository.findByUserId(123L);
        Assertions.assertFalse(CollectionUtils.isEmpty(orderList));
        TicketOrderEntity order = orderList.get(0);
        Assertions.assertEquals("张三", order.getContactName());
        Assertions.assertEquals("MU2151", order.getFlight());
        Assertions.assertEquals("FIRST", order.getClassType());
        Assertions.assertEquals(123L, order.getUserId());
        Assertions.assertEquals("13888888888", order.getContactMobile());
    }

}

package com.tw.flyhigh.repository;

import com.tw.flyhigh.entity.TicketOrderEntity;
import com.tw.flyhigh.entity.TicketOrderEventEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TicketOrderEventRepositoryTest {
    @Autowired
    private TicketOrderRepository orderRepository;
    @Autowired
    private TicketOrderEventRepository ticketOrderEventRepository;

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
        TicketOrderEventEntity entity = ticketOrderEventRepository.save(TicketOrderEventEntity.builder()
            .order(ticketOrderEntity)
            .status("CREATED")
            .build());

//        List<OrderEventEntity> orderList = orderEventRepository.findByOrderIdOrderByCreatedAtDesc(123L);
//        Assertions.assertFalse(CollectionUtils.isEmpty(orderList));
//        OrderEventEntity orderEvent = orderList.get(0);
        Assertions.assertEquals(1L, entity.getId());
        Assertions.assertEquals("CREATED", entity.getStatus());
    }
}

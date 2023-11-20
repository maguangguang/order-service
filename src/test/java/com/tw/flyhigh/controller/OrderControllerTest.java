package com.tw.flyhigh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.flyhigh.OrderFixture;
import com.tw.flyhigh.common.ExceptionHandlerAdvice;
import com.tw.flyhigh.common.exception.BusinessException;
import com.tw.flyhigh.common.exception.NoMoreSeatException;
import com.tw.flyhigh.service.impl.OrderServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderServiceImpl orderServiceImpl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void should_create_order_successful() throws Exception {
        when(this.orderServiceImpl.createOrder(OrderFixture.getCreateOrderDto())).thenReturn(OrderFixture.getOrder());

        MockMvcBuilders.standaloneSetup(this.orderController).build()
            .perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderFixture.getCreateOrderDto())))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("flight").value("MU2151"))
            .andExpect(jsonPath("classType").value("FIRST"))
            .andExpect(jsonPath("contactMobile").value("13888888888"))
            .andExpect(jsonPath("contactName").value("张三"))
            .andExpect(jsonPath("$.passengerList[0].name").value("李四"))
            .andExpect(jsonPath("$.passengerList[0].ageType").value("老人"))
            .andExpect(jsonPath("$.passengerList[0].mobile").value("13866668888"))
            .andExpect(jsonPath("$.passengerList[0].insuranceId").value("666"))
            .andExpect(jsonPath("$.passengerList[0].insuranceName").value("一路顺风"))
            .andExpect(jsonPath("$.passengerList[0].insurancePrice").value(20))
            .andExpect(jsonPath("$.passengerList[0].identificationNumber").value("610502200001015432"))
            .andExpect(jsonPath("$.passengerList[0].price").value(200))
            .andExpect(jsonPath("$.orderEventList[0].status").value("CREATED"))
            .andExpect(jsonPath("userId").value(12L));
    }

    @Test
    void should_create_order_failed_when_no_more_seat() throws Exception {
        doThrow(NoMoreSeatException.class).when(this.orderServiceImpl).createOrder(OrderFixture.getCreateOrderDto());

        MockMvcBuilders.standaloneSetup(this.orderController).setControllerAdvice(ExceptionHandlerAdvice.class).build()
            .perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderFixture.getCreateOrderDto())))
            .andExpect(status().isNotFound())
            .andExpect(content().string("机票已售罄"));
    }

    @Test
    void should_create_order_failed_when_identity_id_is_valid() throws Exception {
        MockMvcBuilders.standaloneSetup(this.orderController).setControllerAdvice(ExceptionHandlerAdvice.class).build()
            .perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderFixture.getCreateOrderDtoWithInvalidIdentityNumber())))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("code").value("10001"))
            .andExpect(jsonPath("message").value("身份证号码格式有误"));
    }

    @Test
    void should_throw_exception_when_server_has_problem() throws Exception {
        given(orderServiceImpl.createOrder(any())).willThrow(BusinessException.class);

        MockMvcBuilders.standaloneSetup(this.orderController).setControllerAdvice(ExceptionHandlerAdvice.class).build()
            .perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderFixture.getCreateOrderDto())))
            .andDo(print())
            .andExpect(status().isInternalServerError())
            .andExpect(content().string("服务异常，请稍后再试"));
    }

    @Test
    void should_throw_exception_when_call_dependent_server_failed() throws Exception {
        given(orderServiceImpl.createOrder(any())).willThrow(FeignException.FeignServerException.class);

        MockMvcBuilders.standaloneSetup(this.orderController).setControllerAdvice(ExceptionHandlerAdvice.class).build()
            .perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderFixture.getCreateOrderDto())))
            .andDo(print())
            .andExpect(status().isInternalServerError())
            .andExpect(content().string("服务异常，请稍后再试"));
    }

    //AC1
    @Test
    void should_cancel_order_successfully() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                                  .perform(post("/orders/{orderId}/cancellation", "123456"))
                                                  .andExpect(status().isNotFound())
                                                  .andReturn();
        
        assertEquals("订单未找到", result.getResponse().getContentAsString());
        }
        
        //AC2
        @Test
        void should_return_not_found_when_order_not_exists() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                          .perform(post("/orders/{orderId}/cancellation", "123456"))
                                          .andExpect(status().isNotFound())
                                          .andReturn();
        
        assertEquals("订单未找到", result.getResponse().getContentAsString());
        }
        
        //AC3
        @Test
        void should_return_service_error_when_exception_occurs() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                          .perform(post("/orders/{orderId}/cancellation", "123456"))
                                          .andExpect(status().isInternalServerError())
                                          .andReturn();
        
        assertEquals("服务器错误", result.getResponse().getContentAsString());
        }
    }

    //AC2
    @Test
    void should_return_not_found_when_order_not_exists() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                          .perform(post("/orders/{orderId}/cancellation", "123456"))
                                          .andExpect(status().isNotFound())
                                          .andReturn();

        assertEquals("订单未找到", result.getResponse().getContentAsString());
    }

    //AC3
    @Test
    void should_return_service_error_when_exception_occurs() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                          .perform(post("/orders/{orderId}/cancellation", "123456"))
                                          .andExpect(status().isInternalServerError())
                                          .andReturn();

        assertEquals("服务器错误", result.getResponse().getContentAsString());
    }
}
    //AC2
    @Test
    void should_return_not_found_when_order_not_exists() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                          .perform(post("/orders/{orderId}/cancellation", "123456"))
                                          .andExpect(status().isNotFound())
                                          .andReturn();

        assertEquals("订单未找到", result.getResponse().getContentAsString());
    }
    //AC3
    @Test
    void should_return_service_error_when_exception_occurs() throws Exception {
        MvcResult result = MockMvcBuilders.standaloneSetup(this.orderController).build()
                                          .perform(post("/orders/{orderId}/cancellation", "123456"))
                                          .andExpect(status().isInternalServerError())
                                          .andReturn();

        assertEquals("服务器错误", result.getResponse().getContentAsString());
    }


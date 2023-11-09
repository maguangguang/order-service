package com.tw.flyhigh.integration.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.tw.flyhigh.dto.ReleaseSeatRequest;
import com.tw.flyhigh.dto.ReserveSeatRequest;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest
class PriceSeatManagerClientTest {

    @RegisterExtension
    static WireMockExtension PRICE_SEAT_MANAGER = WireMockExtension.newInstance()
        .options(WireMockConfiguration.wireMockConfig().port(8088))
        .build();

    @Autowired
    private PriceSeatManagerClient priceSeatManagerClient;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void call_price_seat_manager_server_to_reserve_seat_successful() throws JsonProcessingException {
        ReserveSeatRequest seatRequest = ReserveSeatRequest.builder().classType("FIRST").flight("MU2151").number(2).build();

        String request = mapper.writeValueAsString(seatRequest);
        PRICE_SEAT_MANAGER.stubFor(post(urlPathEqualTo("/seats/reservation")).withRequestBody(equalTo(request))
            .willReturn(aResponse().withBody("true").withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)));

        boolean success = priceSeatManagerClient.reserveSeat(seatRequest);
        Assertions.assertTrue(success);
    }

    @Test
    public void call_price_seat_manager_server_to_reserve_seat_successful_and_return_false() throws JsonProcessingException {
        ReserveSeatRequest seatRequest = ReserveSeatRequest.builder().classType("FIRST").flight("MU2151").number(2).build();

        String request = mapper.writeValueAsString(seatRequest);
        PRICE_SEAT_MANAGER.stubFor(post(urlPathEqualTo("/seats/reservation")).withRequestBody(equalTo(request))
            .willReturn(aResponse().withBody("false").withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)));

        boolean success = priceSeatManagerClient.reserveSeat(seatRequest);
        Assertions.assertFalse(success);
    }

    @Test
    public void should_throw_exception_when_call_price_seat_manager_server_to_reserve_seat_failed() throws JsonProcessingException {
        ReserveSeatRequest seatRequest = ReserveSeatRequest.builder().classType("FIRST").flight("MU2151").number(2).build();

        String request = mapper.writeValueAsString(seatRequest);
        PRICE_SEAT_MANAGER.stubFor(post(urlPathEqualTo("/seats/reservation")).withRequestBody(equalTo(request))
            .willReturn(aResponse().withStatus(500)));

        Assertions.assertThrows(FeignException.FeignServerException.class, () -> priceSeatManagerClient.reserveSeat(seatRequest));
    }

    @Test
    public void call_price_seat_manager_server_to_release_seat_successful() throws JsonProcessingException {
        ReleaseSeatRequest seatRequest = ReleaseSeatRequest.builder().classType("FIRST").flight("MU2151").number(2).build();

        String request = mapper.writeValueAsString(seatRequest);
        PRICE_SEAT_MANAGER.stubFor(post(urlPathEqualTo("/seats/release")).withRequestBody(equalTo(request))
            .willReturn(aResponse().withBody("true").withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)));

        boolean success = priceSeatManagerClient.releaseSeat(seatRequest);
        Assertions.assertTrue(success);
    }

    @Test
    public void call_price_seat_manager_server_to_release_seat_successful_and_return_false() throws JsonProcessingException {
        ReleaseSeatRequest seatRequest = ReleaseSeatRequest.builder().classType("FIRST").flight("MU2151").number(2).build();

        String request = mapper.writeValueAsString(seatRequest);
        PRICE_SEAT_MANAGER.stubFor(post(urlPathEqualTo("/seats/release")).withRequestBody(equalTo(request))
            .willReturn(aResponse().withBody("false").withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)));

        boolean success = priceSeatManagerClient.releaseSeat(seatRequest);
        Assertions.assertFalse(success);
    }

    @Test
    public void should_throw_exception_when_call_price_seat_manager_server_to_release_seat_failed() throws JsonProcessingException {
        ReleaseSeatRequest seatRequest = ReleaseSeatRequest.builder().classType("FIRST").flight("MU2151").number(2).build();

        String request = mapper.writeValueAsString(seatRequest);
        PRICE_SEAT_MANAGER.stubFor(post(urlPathEqualTo("/seats/release")).withRequestBody(equalTo(request))
            .willReturn(aResponse().withStatus(500)));

        Assertions.assertThrows(FeignException.FeignServerException.class, () -> priceSeatManagerClient.releaseSeat(seatRequest));
    }
}

package com.tw.flyhigh.integration.client;

import com.tw.flyhigh.dto.FlightDetail;
import com.tw.flyhigh.dto.ReleaseSeatRequest;
import com.tw.flyhigh.dto.ReserveSeatRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${third.price-seat-manager.url}/seats", name = "priceSeatManagerClient")
public interface PriceSeatManagerClient {

    @GetMapping(path = "/{flight}", produces = MediaType.APPLICATION_JSON_VALUE)
    FlightDetail getFlightDetail(@PathVariable("flight") String flight);

    @PostMapping(path = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean reserveSeat(@RequestBody ReserveSeatRequest reserveSeatRequest);

    @PostMapping(path = "/release", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean releaseSeat(@RequestBody ReleaseSeatRequest releaseSeatRequest);
}

package com.tw.flyhigh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseSeatRequest {
    private String flight;
    private String classType;
    private int number;
}

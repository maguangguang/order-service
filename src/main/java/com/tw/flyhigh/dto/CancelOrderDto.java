package com.tw.flyhigh.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiModel("取消机票订单")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderDto {
    @ApiModelProperty("订单Id")
    @NotNull
    @Valid
    private Long orderId;
    @ApiModelProperty("用户Id")
    @NotNull
    Long userId;
}

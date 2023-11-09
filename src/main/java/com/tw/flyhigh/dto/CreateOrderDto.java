package com.tw.flyhigh.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@ApiModel("创建机票订单")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    @ApiModelProperty("乘机人信息")
    @NotNull
    @Valid
    private List<PassengerDto> passengerDtoList;
    @ApiModelProperty("舱位类型")
    @NotNull
    @Valid
    private String classType;
    @ApiModelProperty("航班日期")
    @NotNull
    private String flightDate;
    @ApiModelProperty("用户Id")
    @NotNull
    private Long userId;
    @ApiModelProperty("航班编号")
    @NotNull
    private String flight;
    @ApiModelProperty("联系人电话")
    @NotNull
    private String contactMobile;
    @ApiModelProperty("联系人姓名")
    @NotNull
    private String contactName;

    @ApiModel("乘机人信息")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PassengerDto {
        @ApiModelProperty("用户名")
        @NotNull
        private String name;
        @ApiModelProperty("年龄段")
        @NotNull
        private String ageType;
        @ApiModelProperty("身份证")
        @NotNull
        @Pattern(regexp = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", message = "身份证号码格式有误")
        private String identificationNumber;
        @ApiModelProperty("手机号")
        @NotNull
        @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "手机号码格式有误")
        private String mobile;
        @ApiModelProperty("票价")
        @NotNull
        private Integer price;
        @ApiModelProperty("付费行李重量(kg)")
        @NotNull
        private Integer baggageWeight;
        @ApiModelProperty("保险Id")
        @Nullable
        private String insuranceId;
        @ApiModelProperty("保险名称")
        @Nullable
        private String insuranceName;
        @ApiModelProperty("保险费用")
        @Nullable
        private Integer insurancePrice;
    }
}

package com.tw.flyhigh.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionBody {
    private String code;
    private String message;
}

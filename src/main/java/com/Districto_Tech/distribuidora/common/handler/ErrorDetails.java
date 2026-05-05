package com.Districto_Tech.distribuidora.common.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private String code;
    private String message;
    private LocalDateTime timestamp;

}

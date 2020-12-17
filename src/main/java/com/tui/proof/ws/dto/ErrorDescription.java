package com.tui.proof.ws.dto;

import lombok.Value;

@Value
public class ErrorDescription {
    String cause;
    String message;
}

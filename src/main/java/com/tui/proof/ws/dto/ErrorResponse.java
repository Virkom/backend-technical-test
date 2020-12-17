package com.tui.proof.ws.dto;

import lombok.Value;
import java.util.List;

@Value
public class ErrorResponse {
    List<ErrorDescription> errors;
}

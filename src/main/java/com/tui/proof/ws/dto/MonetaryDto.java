package com.tui.proof.ws.dto;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class MonetaryDto {
    BigDecimal amount;
    String currency;
}

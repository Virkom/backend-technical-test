package com.tui.proof.ws.dto;

import lombok.Value;

@Value
public class AuthenticationRequest {
    String username;
    String password;
}

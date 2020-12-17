package com.tui.proof.ws.service;

import com.tui.proof.ws.dto.AuthenticationRequest;
import com.tui.proof.ws.dto.AuthenticationResponse;

/**
 * Authentication service
 */
public interface AuthenticationService {

    /**
     * Method for user authentication
     * @param request authentication request
     * @return authentication response
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);
}

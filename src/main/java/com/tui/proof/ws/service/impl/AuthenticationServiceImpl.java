package com.tui.proof.ws.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.tui.proof.ws.dto.AuthenticationRequest;
import com.tui.proof.ws.dto.AuthenticationResponse;
import com.tui.proof.ws.exception.UserLoginException;
import com.tui.proof.ws.security.JwtOperations;
import com.tui.proof.ws.service.AuthenticationService;
import com.tui.proof.ws.service.MessageService;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final MessageService messages;
    private final JwtOperations jwtOperations;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()
                            )
             );
        } catch (BadCredentialsException e) {
            throw new UserLoginException(messages.getMessage("error.user.auth.invalid_credentials"), e);
        }

        User user = (User) authentication.getPrincipal();
        return new AuthenticationResponse(jwtOperations.generateAccessToken(user));
    }
}

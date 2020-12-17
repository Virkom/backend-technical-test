package com.tui.proof.ws.service.impl;

import lombok.AllArgsConstructor;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.tui.proof.ws.service.MessageService;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String msg, Object... params) {
        return messageSource.getMessage(msg, params, Locale.US);
    }
}

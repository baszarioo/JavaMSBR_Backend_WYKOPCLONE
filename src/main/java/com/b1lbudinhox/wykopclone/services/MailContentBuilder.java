package com.b1lbudinhox.wykopclone.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
@Slf4j
public class MailContentBuilder {
    private final TemplateEngine templateEngine;
    public String build(String message, String username, String recipient) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("username", username);
        context.setVariable("recipient", recipient);
        return templateEngine.process("registrationConfirmationMailTemplate", context);
    }
}

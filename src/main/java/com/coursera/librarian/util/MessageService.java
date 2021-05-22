package com.coursera.librarian.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String getMessage(String messageKey,Object... parameters){
       return messageSource.getMessage(messageKey,parameters, LocaleContextHolder.getLocale());
    }

}

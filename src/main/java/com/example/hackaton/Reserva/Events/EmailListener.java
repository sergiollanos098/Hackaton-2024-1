package com.example.hackaton.Reserva.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void handleHelloEmailEvent(HelloEmailEvent event) {
        emailService.sendSimpleMessage(event.getEmail(), "Confirmación de Reserva",
                "Tu reserva ha sido confirmada.");
    }
}

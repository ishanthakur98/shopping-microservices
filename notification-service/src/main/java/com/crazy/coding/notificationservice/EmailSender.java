package com.crazy.coding.notificationservice;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    public void sendEmail(String message) {
        System.out.println("Order placed successfully - order no = "+message);
        System.out.println("Email sent");
    }
}

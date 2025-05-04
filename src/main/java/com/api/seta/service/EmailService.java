package com.api.seta.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void newAccount(String to, String token) {

    String text = "Clique no link para completar seu cadastro: http://localhost:8080/account/confirm?token=" + token;
    
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@seta.com");
    message.setTo(to);
    message.setSubject("Confirmação de cadastro");
    message.setText(text);
    mailSender.send(message);
  }
}

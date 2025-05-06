package com.api.seta.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.seta.model.NewAccountRequest;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void newAccountMail(NewAccountRequest register) {
    String text = "Clique no link para completar seu cadastro: http://localhost:8080/account/confirm?token=" + register.getToken();
    
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@seta.com");
    message.setTo(register.getEmail());
    message.setSubject("Confirmação de cadastro");
    message.setText(text);
    mailSender.send(message);
  }

  public void resendNewAccountMail(NewAccountRequest register) {
    String text = "Você possui uma solicitação de cadastro pendente. Clique no link para finalizar: http://localhost:8080/account/confirm?token=" + register.getToken();
    
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@seta.com");
    message.setTo(register.getEmail());
    message.setSubject("Finalize seu cadastro");
    message.setText(text);
    mailSender.send(message);
  }
}

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
    String url = "http://localhost:8080/new-account/" + register.getToken() + "/" + register.getEmail();
    String text = "Clique no link para completar seu cadastro: " + url;
    
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@seta.com");
    message.setTo(register.getEmail());
    message.setSubject("Confirmação de cadastro");
    message.setText(text);
    mailSender.send(message);
  }

  public void resendNewAccountMail(NewAccountRequest register) {
    String url = "http://localhost:8080/new-account/" + register.getToken() + "/" + register.getEmail();
    String text = "Você possui uma solicitação de cadastro pendente. Clique no link para finalizar: " + url;
    
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@seta.com");
    message.setTo(register.getEmail());
    message.setSubject("Finalize seu cadastro");
    message.setText(text);
    mailSender.send(message);
  }
}

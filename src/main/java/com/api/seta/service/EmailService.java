package com.api.seta.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.api.seta.model.NewAccountRequest;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void newAccountMail(NewAccountRequest register) {
    String link = "http://localhost:8081/register-confirm/" + register.getToken() + "/" + register.getEmail();
    String html = "<p>Clique no botão abaixo para completar seu cadastro:</p>" +
                  "<a href=\"" + link + "\" " +
                  "style=\"display:inline-block;padding:10px 20px;background-color:#008937;color:white;text-decoration:none;border-radius:5px;\">" +
                  "Confirmar Cadastro</a>";

    sendHtmlEmail(register.getEmail(), "Confirmação de cadastro", html);
  }

  public void resendNewAccountMail(NewAccountRequest register) {
    String link = "http://localhost:8081/register-confirm/" + register.getToken() + "/" + register.getEmail();
    String html = "<p>Você possui uma solicitação de cadastro pendente. Clique no botão abaixo para finalizar:</p>" +
                  "<a href=\"" + link + "\" " +
                  "style=\"display:inline-block;padding:10px 20px;background-color:#008937;color:white;text-decoration:none;border-radius:5px;\">" +
                  "Finalizar Cadastro</a>";

    sendHtmlEmail(register.getEmail(), "Finalize seu cadastro", html);
  }

  private void sendHtmlEmail(String to, String subject, String html) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom("noreply@seta.com");
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(html, true);

      mailSender.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
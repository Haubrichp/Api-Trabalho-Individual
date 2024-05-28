package br.com.serratec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    // Método para enviar email
    public void sendMail(String to, String subject, String name) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Configurando o contexto do Thymeleaf
        Context context = new Context();
        context.setVariable("name", name);

        // Processando o template de email
        String htmlContent = templateEngine.process("emailTemplate", context);

        // Configurando os detalhes do email
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        // Adicionando imagem inline
        helper.addInline("logo.png", new ClassPathResource("static/images/logo.png"));

        // Enviando o email
        mailSender.send(message);
    }
}

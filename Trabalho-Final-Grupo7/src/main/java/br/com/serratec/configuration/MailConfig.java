package br.com.serratec.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Configuration
public class MailConfig {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendMail(String to, String subject, String templateName, Context context) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("felipe.mauriz@aluno.senai.br");
        helper.setTo(to);
        helper.setSubject(subject);

       
        String htmlContent = templateEngine.process(templateName, context);
        helper.setText(htmlContent, true);

                ClassPathResource image = new ClassPathResource("static/imagem.png");
        helper.addInline("logoImage", image);

        mailSender.send(message);
    }
}

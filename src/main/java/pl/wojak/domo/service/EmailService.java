package pl.wojak.domo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    public String nadawca;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, @Value("${spring.mail.from}") String nadawca) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.nadawca = nadawca;
    }

    public void wyslijEmail(String temat, String tresc, String adresat) {

        String content = przygotujMaila(tresc);

        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(adresat);
            helper.setFrom(nadawca);
            helper.setSubject(temat);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now()+ "Wysyła mail do: " + adresat);
        javaMailSender.send(mail);
    }


    public String przygotujMaila(String tresc) {
        Context context = new Context();
        context.setVariable("tresc", tresc);
        String content = templateEngine.process("template", context);
        return content;
    }
}

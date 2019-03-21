package pl.wojak.domo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
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

    @Value("${spring.mail.properties.mail.smtp.replyTo}")
    public String odpowiedzDo;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    public String nadawca;


    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, @Value("${spring.mail.properties.mail.smtp.from}") String nadawca) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.nadawca = nadawca;
    }

    public void wyslijEmail(String temat, String tresc, String adresat, String kodLokalu) {

        String content = przygotujContentMaila(tresc);

        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(adresat);
            helper.setFrom(nadawca);
            helper.setReplyTo(odpowiedzDo);
            helper.setSubject(temat);
            helper.setText(content, true);
        } catch (MessagingException e) {
            System.out.println("To jest mój błąd wiadomości: " + e);
        } catch (Exception e) {
            System.out.println("To jest ogólny błąd przy tworzeniu wiadomości: " + e);
        }
        System.out.println(LocalDateTime.now() + " Wysyła mail do: " + kodLokalu + " email: " + adresat);
        try {
//            javaMailSender.send(mail);
        } catch (MailAuthenticationException e) {
            System.out.println("To jest błąd MailAuthenticationException: " + e);
//            javax.mail.AuthenticationFailedException: 535 blad autoryzacji, niepoprawny login lub haslo / auth failure
        } catch (MailSendException e) {
            System.out.println("To jest błąd MailSendException: " + e);
        } catch (MailException e) {
            System.out.println("To jest błąd MailException: " + e);
        }
    }


    public String przygotujContentMaila(String tresc) {
        Context context = new Context();
        context.setVariable("tresc", tresc);
        String content = templateEngine.process("template", context);
        return content;
    }
}

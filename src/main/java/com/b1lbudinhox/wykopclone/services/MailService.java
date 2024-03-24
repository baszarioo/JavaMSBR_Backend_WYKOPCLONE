package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.exceptions.SpringAppException;
import com.b1lbudinhox.wykopclone.models.NotificaitonMail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;
    @Async
    void sendMail(NotificaitonMail notificaitonMail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("wykopwypokclone@springbootemail.com");
            messageHelper.setTo(notificaitonMail.getRecipient());
            messageHelper.setSubject(notificaitonMail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificaitonMail.getMailBody(), notificaitonMail.getUsername(), notificaitonMail.getRecipient()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occured when sending mail", e);
            throw new SpringAppException("Exception occured when mailing "
                    + notificaitonMail.getRecipient(), e);
        }
    }
}

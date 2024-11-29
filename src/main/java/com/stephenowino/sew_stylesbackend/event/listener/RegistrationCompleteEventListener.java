package com.stephenowino.sew_stylesbackend.event.listener;


import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
        @Component
        @RequiredArgsConstructor
        public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

                private final UserService userService;
                private final JavaMailSender mailSender;

                @Override
                public void onApplicationEvent(RegistrationCompleteEvent event) {
                        // 1. Get the newly registered user
                        User user = event.getUser();

                        // 2. Create a verification token for the user
                        String verificationToken = UUID.randomUUID().toString();

                        // 3. Save the verification token for the user
                        userService.saveUserVerificationToken(user, verificationToken);

                        // 4. Build the verification URL
                        String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;

                        // 5. Send the email
                        try {
                                sendVerificationEmail(user, url);
                                log.info("Verification email sent to {} with URL: {}", user.getEmail(), url);
                        } catch (MessagingException | UnsupportedEncodingException e) {
                                log.error("Failed to send verification email to {}", user.getEmail(), e);
                        }
                }

                private void sendVerificationEmail(User user, String url) throws MessagingException, UnsupportedEncodingException {
                        String subject = "Email Verification";
                        String senderName = "User Registration Portal Service";
                        String mailContent = """
                <p>Hi, %s,</p>
                <p>Thank you for registering with us. Please follow the link below to complete your registration:</p>
                <p><a href="%s">Verify your email to activate your account</a></p>
                <p>Thank you,<br>User Registration Portal Service</p>
                """.formatted(user.getFirstName(), url);

                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

                        messageHelper.setFrom("dailycodework@gmail.com", senderName);
                        messageHelper.setTo(user.getEmail());
                        messageHelper.setSubject(subject);
                        messageHelper.setText(mailContent, true);

                        mailSender.send(message);
                }
        }



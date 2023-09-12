package com.sim.management.service.impl;

import com.sim.management.service.EmailService;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender mailSender;

}

package com.intakhab.springsecuritytutorial.Service;

import com.intakhab.springsecuritytutorial.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sendRegistrationMail(Email emailDetails) {
        try {
            System.out.println(emailDetails);
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getMsgBody());

            javaMailSender.send(mailMessage);
            return "Email sent successfully";
        }catch (Exception e){
            return "There is an error to send the email";
        }
    }
}

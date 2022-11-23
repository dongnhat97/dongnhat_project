//package com.java.common.mail;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//@Slf4j
//public class MailSenderImpl implements IMailSender {
//    private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderImpl.class);
//   @Autowired
//   private JavaMailSender mailSender;
//
//    @Override
//    public void send(String email, String content) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//            mimeMessageHelper.setFrom("dongnhatht@gmail.com");
//            mimeMessageHelper.setText(content,true);
//            mimeMessageHelper.setSubject("Confirm email from school");
//            mimeMessageHelper.setTo(email);
//        } catch (MessagingException e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//    @Override
//    public String buildEmail(String link) {
//         return "<h3 style=\"text-align: center\">Welcome</h3>\n" +
//                "<p>C0421G1 xin gửi lời chào vì đã đăng ký trở thành một giáo viên ở Trường THPT Mầm Non</p>\n" +
//                "<p>Hãy click vào link "+ link  +"  để active account của bạn</p>";
//    }
//}
//

package org.cacanhdaden.quanlythuoc.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


public class EmailSendingUtil {
    public static boolean sendEmail(Session session, String toEmail, String subject, String body){
        boolean flag = false;
        try {
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("macsin233@gmail.com", "Hệ thống quản lý thuốc"));
            msg.setReplyTo(InternetAddress.parse("macsin233@gmail.com", false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String sendAuthOTPEmail(String toEmail) {
        final String fromEmail = "macsin233@gmail.com";
        final String password = "aoju pnox huds fhga ";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        Random rand = new Random();
        int num = 100000 + rand.nextInt(900000);

        boolean isSend =  EmailSendingUtil.sendEmail(session, toEmail,"Yêu cầu gửi mã OTP", "Xin chào! \nĐây là mã OTP sử dụng cho chức năng tương ứng trên hệ thống! \nMã OTP là " + String.valueOf(num));

        if (!isSend) {
            return null;
        } else {
            return String.valueOf(num);
        }
    }
}

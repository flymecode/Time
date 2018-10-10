package com.time.utils;

import com.time.pojo.User;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailUitls {
    private static final String FROM = "1272193309@qq.com";
    private static final String AUTHORIZATION = "mgybibazaphvgdbf";
    public static void sendAccountActivateEmail(User user) throws MessagingException {
        Session session = getSession();

        MimeMessage message = new MimeMessage(session);


            message.setSubject("激活账号");
            message.setSentDate(new Date());
            //setFrom 表示用哪个邮箱发送邮件
            message.setFrom(new InternetAddress(FROM));
            /**
             * RecipientType.TO||BCC||CC
             *     TO表示主要接收人
             *     BCC表示秘密抄送人
             *     CC表示抄送人
             * InternetAddress  接收者的邮箱地址
             */
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setContent("<a target='_BLANK' href='"+GenerateLinkUtils.generateActivateLink(user)+"'>"+user.getUserName()+"先生/女士您好，请点击此链接激活账号"+GenerateLinkUtils.generateActivateLink(user)
			+"</a>","text/html;charset=utf-8");
            Transport.send(message);



    }

    /**
     * 获取一个邮件的实例
     * @return
     */
    public static Session getSession() {

        Properties props = new Properties();
        /**
         * 指定发送邮箱的邮箱协议
         */
        props.setProperty("mail.transport.protocol", "smtp");
        /**
         * 指定SMTP服务器
         */
        props.setProperty("mail.smtp.host","smtp.qq.com");
        /**
         * stmp是发信邮件服务器，端口是25
         */
        props.setProperty("mail.smtp.port", "25");
        /**
         * 指定是否需要SMTP验证
         */
        props.setProperty("mail.smtp.auth","true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(FROM, AUTHORIZATION);
            }
        });
        return session;
    }

}

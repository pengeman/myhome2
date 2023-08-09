package org.pengwt.myhome.myhome2.service;

/**
 * 用于接收和发送邮件
 */
public interface HomeMail {
    javax.mail.Message[] recieve(String host, String username, String pwd, String folder);
    javax.mail.Message recieve(String host, String username, String pwd, String folder, String subject);

    void send(String host, String username, javax.mail.Message message);
}

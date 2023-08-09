package org.pengwt.myhome.myhome2.serviceimpl;

import org.pengwt.myhome.myhome2.service.HomeMail;

import javax.mail.Message;

public class HomeMailimp implements HomeMail {
    @Override
    public Message[] recieve(String host, String username, String pwd, String folder) {
        return new Message[0];
    }

    @Override
    public Message recieve(String host, String username, String pwd, String folder, String subject) {
        return null;
    }

    @Override
    public void send(String host, String username, Message message) {

    }
}

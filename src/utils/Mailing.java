/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author Thrall
 */
public class Mailing {
    public static void send(String from,String pwd,String to,String sub,String msg){
    //Propriétés
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.socketFactory.port", "465");
    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.port", "465");
    //Session
    Session s;
        s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pwd);
                    }
                });
    //composer le message
    try {
      MimeMessage m = new MimeMessage(s);
      m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
      m.setSubject(sub);
      m.setText(msg);
      //envoyer le message
      Transport.send(m);
      System.out.println("Message envoyé avec succès");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}

    


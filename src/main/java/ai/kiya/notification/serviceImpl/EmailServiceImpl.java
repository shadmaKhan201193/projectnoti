package ai.kiya.notification.serviceImpl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ai.kiya.notification.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${email.user.name}")
	private String userName;
	
	@Value("${email.password}")
	private String password;
	
	@Value("${email.server.host}")
	private String serverHost;
	
	@Value("${email.server.port}")
	private String serverPort;
	
	@Override
	public void sendEmail(String emailId, String subject, String message) { 
		MimeMessage msg = getMimeMsgInstance();

		try {
			msg.setFrom(userName);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendEmail(List<String> to, List<String> cc, List<String> bcc, String subject, String message) {
		MimeMessage msg = getMimeMsgInstance();
		try {
			msg.setFrom(userName);
			if(to != null) {
				for (String email : to) {
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				}
			}
			if(cc != null) {
				for (String email : cc) {
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
				}
			}
			if(bcc != null) {
				for (String email : bcc) {
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(email));
				}
			}
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
	}

	private MimeMessage getMimeMsgInstance() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", serverHost);
		properties.put("mail.smtp.port", serverPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		return msg;
	}

}
package ai.kiya.notification.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ai.kiya.notification.entity.EmailDefination;
import ai.kiya.notification.entity.EmailDefinationId;
import ai.kiya.notification.repo.EmailDefinationRepo;

@Service
public class EmailService {

	@Autowired
	private EmailDefinationRepo processDefinitionRepo;

	private Logger log = LoggerFactory.getLogger(EmailService.class);

	public void sendNotificationMessage(String to, String[] cc, String[] bcc, String subject, String message,
			Map<String, String> attachments) throws javax.mail.MessagingException, IOException {
		String host = "smtp.office365.com";
		String from = "ankit.dedhiya@kiya.ai";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ankit.dedhiya@kiya.ai", "ad$aug2023");
			}
		});
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		Multipart multipart = new MimeMultipart();

		helper.setFrom(from);
		if (to != null && StringUtils.hasText(to)) {
			if (!to.contains("kiya.ai")) {
				log.debug("Email ID not part of kiya.ai");
				return;
			}
			helper.setTo(new InternetAddress(to));
		}
		if (cc != null) {
			for (String ccAdd : cc) {
				if (ccAdd == null || !StringUtils.hasText(ccAdd) || !ccAdd.contains("kiya.ai") ) {
					continue;
				}
				helper.addCc(ccAdd);
			}
		}
		if (bcc != null) {
			for (String ccAdd : bcc) {
				if (ccAdd == null || !StringUtils.hasText(ccAdd)  || !ccAdd.contains("kiya.ai") ) {
					continue;
				}
				helper.addBcc(ccAdd);
			}
		}
		
		if (attachments != null && !attachments.isEmpty()) {
			for (Map.Entry<String, String> entry : attachments.entrySet()) {
				String attachmentName = entry.getKey();
				String attachmentData = entry.getValue();
				byte[] decodedData = Base64.getDecoder().decode(attachmentData);

				//MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				String filename = attachmentName;
				try {
					FileOutputStream fos = new FileOutputStream(filename);
					fos.write(decodedData);
					fos.close();
					log.debug("File created: " + attachmentName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				DataSource source = new ByteArrayDataSource(decodedData, "application/octet-stream");
				//attachmentBodyPart.setDataHandler(new DataHandler(source));
				//attachmentBodyPart.setFileName(attachmentName);
				//multipart.addBodyPart(attachmentBodyPart);
				helper.addAttachment(attachmentName, source);
				//msg.setContent(multipart);
				
			}
		}
		helper.setSubject(subject);
		helper.setText("<html><body>" + message + "</html></body>", true);
		Transport.send(msg);
		log.debug("Email sent to {} for subject {}", to, subject);

		if (attachments != null && !attachments.isEmpty()) {
			for (Map.Entry<String, String> entry : attachments.entrySet()) {
				String attachmentName = entry.getKey();
				String filename = attachmentName;
				File fileToDelete = new File(filename);
				if (fileToDelete.exists()) {
					if (fileToDelete.delete()) {
						log.debug("File deleted: " + attachmentName);
					} else {
						log.debug("Failed to delete the file: " + attachmentName);
					}
				} else {
					log.debug("File not found: " + attachmentName);
				}
			}
		}
	}

	public EmailDefination getProcessDefinition(Integer tenantId, Integer processId) {
		Optional<EmailDefination> optionalProcessDefinition = processDefinitionRepo
				.findById(new EmailDefinationId(tenantId, processId));
		if (optionalProcessDefinition.isPresent()) {
			return optionalProcessDefinition.get();
		} else {
			return null;
		}
	}

}

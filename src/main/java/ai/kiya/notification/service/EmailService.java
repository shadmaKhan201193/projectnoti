package ai.kiya.notification.service;

import java.util.List;

public interface EmailService {

	public void sendEmail(String emailId, String subject, String message);

	public void sendEmail(List<String> to, List<String> cc, List<String> bcc, String subject, String message);
}

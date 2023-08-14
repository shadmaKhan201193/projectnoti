package ai.kiya.notification.serviceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.kiya.notification.constants.NotificationConstants;
import ai.kiya.notification.entity.EmailDetailsMst;
import ai.kiya.notification.entity.EmailRecipientsMst;
import ai.kiya.notification.helper.EmailService;
import ai.kiya.notification.repo.EmailDetailsMstRepo;
import ai.kiya.notification.repo.EmailGroupingRepo;
import ai.kiya.notification.repo.EmailRecipientsMstRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationHelperServiceImpl {

	@Autowired
	private EmailDetailsMstRepo emailDetailsMstRepo;

	@Autowired
	private EmailRecipientsMstRepo emailRecipientsMstRepo;

	@Autowired
	private EmailGroupingRepo emailGroupingRepo;

	@Autowired
	private EmailService emailService;

	public EmailDetailsMst sendNotification(Map<String, Object> inputMap) throws IOException {

		EmailDetailsMst mst = emailDetailsMstRepo.getRecordForProcess((String) inputMap.get("notifyType"),
				(Integer) inputMap.get("tenantId"), (String) inputMap.get("typeId"));

		if (mst != null) {

			log.info("Received request for email with data : ", inputMap);
			log.info("" + mst.getMailBody().contains(System.lineSeparator()));
			mst.setMailBody(mst.getMailBody().replace("\r\n", "<br>"));
			List<EmailRecipientsMst> recipients = emailRecipientsMstRepo.getAllRecordsForType(
					mst.getEmailDetailsKey().getTenantId(), mst.getEmailDetailsKey().getNotifyType(),
					mst.getEmailDetailsKey().getTypeId());
			for (EmailRecipientsMst recipient : recipients) {

				if (recipient.getUserRole() != null && recipient.getUserIds() == null) {

					List<Map<String, String>> users = emailGroupingRepo
							.getUsersForRole((Integer) inputMap.get("tenantId"), recipient.getUserRole());

					log.debug("Notification generation for a role {} without specific users.", recipient.getUserRole());
					for (Map<String, String> user : users) {
						triggerIndividualEmail(mst, user.get("emailId"), user.get("loginId"), inputMap);
					}
				} else if (recipient.getUserIds() != null && recipient.getUserIds() == " ") {
					String emailId = emailGroupingRepo.getEmailForUser((Integer) inputMap.get("tenantId"),
							(String) recipient.getUserIds());
					triggerIndividualEmail(mst, emailId, (String) recipient.getUserIds(), inputMap);
				} else if (recipient.getAdditionalEmailId() != null) {
					Arrays.asList(recipient.getAdditionalEmailId().split(";")).forEach(recipientEmail -> {
						try {
							triggerIndividualEmail(mst, recipientEmail, NotificationConstants.USER_ALL, inputMap);
						} catch (IOException e) {

							e.printStackTrace();
						}
					});
				}
			}
		
		} else {
			log.error("EmailDetailsMst is null for the given inputMap: " + inputMap);
		}
		return mst;

	}

	private void triggerIndividualEmail(EmailDetailsMst processData, String emailId, String userId,
			Map<String, Object> inputMap) throws IOException {
		try {
			String[] cc = processData.getCc() != null ? processData.getCc().split(";") : null;
			String[] bcc = processData.getBcc() != null ? processData.getBcc().split(";") : null;
			String subject = replaceDynamicText(processData.getSubjectLine(), userId, inputMap);
			String message = replaceDynamicText(processData.getMailBody(), userId, inputMap);
			Map<String, String> attachments = (Map<String, String>) inputMap.get("attachments");
			emailService.sendNotificationMessage(emailId, cc, bcc, subject, message, attachments);
		} catch (MessagingException e) {
			log.error("Failed to send email for type Id ");
			e.printStackTrace();
		}
	}

	private String replaceDynamicText(String text, String userId, Map<String, Object> params) {

		if (text == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (text.contains("${userId}")) {
			if (userId != null && !userId.equals(NotificationConstants.USER_ALL)) {
				text = text.replace("${userId}",
						emailGroupingRepo.getNameForUser((Integer) params.get("tenantId"), userId));
			} else {
				text = text.replace("${userId}", "User");
			}
		}
		if (text.contains("${operationDate}")) {
			if (userId != null && !userId.equals(NotificationConstants.USER_ALL)) {
				text = text.replace("${operationDate}", sdf
						.format(emailGroupingRepo.getOperationalDateForUser((Integer) params.get("tenantId"), userId)));
			} else {
				text = text.replace("${operationDate}", "");
			}
		}
		if (text.contains("${systemDate}")) {
			text = text.replace("${systemDate}", sdf.format(new Date()));
		}

		// Dynamically replace all pending variable with value from map
		String regex = "\\$\\{([^\\}]+)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String matchParam = matcher.group(1);
			if (params.containsKey(matchParam)) {
				Object value = params.get(matchParam);
				if (value instanceof Integer) {
					text = text.replace("${" + matchParam + "}", String.valueOf((Integer) value));
				} else if (value instanceof String) {
					text = text.replace("${" + matchParam + "}", (String) value);
				} else if (value instanceof Date) {

					Date date = (Date) value;
					String dateStr = sdf.format(date);
					text = text.replace("${" + matchParam + "}", dateStr);

				} else if (value instanceof Double) {

					Double doubleValue = (Double) value;
					String doubleStr = String.valueOf(doubleValue);
					text = text.replace("${" + matchParam + "}", doubleStr);
				} else if (value instanceof Map) {

					Map<String, byte[]> attachmentsMap = (Map<String, byte[]>) params.get(matchParam);
					if (attachmentsMap.isEmpty()) {

						text = text.replace("${" + matchParam + "}", "");
					} else {

						StringBuilder attachmentsStr = new StringBuilder();
						for (Map.Entry<?, ?> entry : attachmentsMap.entrySet()) {
							String attachmentName = String.valueOf(entry.getKey());
							byte[] attachmentContent = (byte[]) entry.getValue();

							attachmentsStr.append("Attachment Name: ").append(attachmentName).append("\n");
						}
						text = text.replace("${" + matchParam + "}", attachmentsStr.toString());
					}
				}

				else {
					log.error("Unknown param type encountered: " + value.getClass());
				}
			} else {
				text = text.replace("${" + matchParam + "}", "");
			}
		}

		return text;

//		 String[] parts = text.split(",");
//	        StringBuilder newTextBuilder = new StringBuilder();
//	        for (String part : parts) {
//	            newTextBuilder.append(part.trim()).append("<br>"); 
//	        }
//	        return newTextBuilder.toString();

	}

	
}

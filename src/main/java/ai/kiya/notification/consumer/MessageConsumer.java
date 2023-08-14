package ai.kiya.notification.consumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.kiya.notification.config.AppConstant;
import ai.kiya.notification.dto.NotificationVO;
import ai.kiya.notification.repo.MasterNotificationRepo;
import ai.kiya.notification.service.MasterNotificationService;

@Component
public class MessageConsumer {
	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MasterNotificationService masterNotificationService;

	@Autowired
	MasterNotificationRepo masterNotificationRepo;

	@JmsListener(destination = AppConstant.mail_queue, containerFactory = "queueListenerFactory")
	public void ListenerQueueMail(String jsonNotification) throws IOException, MessagingException {
		NotificationVO notificationVO = objectMapper.readValue(jsonNotification.getBytes(), NotificationVO.class);
		try {
			masterNotificationService.sendNotification(notificationVO, AppConstant.NOTIFICATION_TYPE_EMAIL);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@JmsListener(destination = AppConstant.sms_queue, containerFactory = "queueListenerFactory")
	public void ListenerQueueMessage(String jsonNotification) throws IOException, MessagingException {
		NotificationVO notificationVO = objectMapper.readValue(jsonNotification.getBytes(), NotificationVO.class);
		try {
			masterNotificationService.sendNotification(notificationVO, AppConstant.NOTIFICATION_TYPE_SMS);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@JmsListener(destination = AppConstant.mail_sms_topic, containerFactory = "topicListenerFactory")
	public void ListenerMail(String jsonNotification) throws IOException, MessagingException {
		NotificationVO notificationVO = objectMapper.readValue(jsonNotification.getBytes(), NotificationVO.class);
		try {
			masterNotificationService.sendNotification(notificationVO, AppConstant.NOTIFICATION_TYPE_EMAIL_SMS);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@JmsListener(destination = AppConstant.app_notification_topic, containerFactory = "topicListenerFactory")
	public void ListenerForSave(String jsonNotification) throws IOException, MessagingException {
		NotificationVO notificationVO = objectMapper.readValue(jsonNotification.getBytes(), NotificationVO.class);
		masterNotificationService.sendNotification(notificationVO, AppConstant.NOTIFICATION_TYPE_APP);
	}

}

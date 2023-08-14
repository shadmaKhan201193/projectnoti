package ai.kiya.notification.service;

import org.springframework.stereotype.Service;


public interface NotificationHelperService {
	
	public void sendProcessNotification(Integer processId, Integer tenantId, String eventType, String errorMsg);

}

package ai.kiya.notification.service;

import java.util.List;

import ai.kiya.notification.dto.NotificationVO;
import ai.kiya.notification.entity.MasterNotification;
import ai.kiya.notification.entity.NotificationMst;

public interface MasterNotificationService {

	public NotificationMst getByNotificationId(Integer notificationId);
	public List <NotificationMst> getByReadYN(Boolean readYN);
	
	public void sendNotification(NotificationVO notificationVO, String type);
	List<NotificationMst> getAll();
	
	
	 List<Object[]> getAllLoanLimitAndEmail(String prdAcctId);
	
	
}

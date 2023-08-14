package ai.kiya.notification.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.kiya.notification.entity.NotificationMst;

@Repository
public interface MasterNotificationRepo extends JpaRepository<NotificationMst,Integer>{

	NotificationMst findByNotificationId(Integer notificationId);

	List<NotificationMst> findByReadYN(Boolean notificationId);

	
	
}

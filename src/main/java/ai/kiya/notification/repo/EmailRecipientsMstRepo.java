package ai.kiya.notification.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ai.kiya.notification.entity.EmailRecipientsMst;
import ai.kiya.notification.entity.EmailRecipientsKey;

@Repository
public interface EmailRecipientsMstRepo extends JpaRepository<EmailRecipientsMst, EmailRecipientsKey> {

	
	@Query(value = "select * from D008105 with (nolock) where tenantId = ? and notifyType = ? and typeId = ? and authStatus = 'A' and isActive = 1 ", nativeQuery = true)
	List<EmailRecipientsMst> getAllRecordsForType(Integer tenantId, String notifyType, String typeId);

}

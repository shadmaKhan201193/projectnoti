package ai.kiya.notification.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ai.kiya.notification.entity.EmailDetailsMst;
import ai.kiya.notification.entity.EmailDetailsKey;

@Repository
public interface EmailDetailsMstRepo extends JpaRepository<EmailDetailsMst, EmailDetailsKey>{

	@Query(value = "select * from D008005 with (nolock) where notifyType = ? and authStatus = 'A' and isActive = 1 ",  nativeQuery = true)
	EmailDetailsMst getAllReportRecords(String notifyType);
	
	@Query(value = "select * from  D008005 with (nolock) where notifyType = ? and tenantId = ? and typeId = ? and authStatus = 'A' and isActive = 1 ",  nativeQuery = true)
	EmailDetailsMst getRecordForProcess(String notifyType, Integer tenantId, String typeId);
		
	@Query(value = "select * from D008005 with (nolock) where tenantId = ? and authStatus = 'A' and isActive = 1 ",  nativeQuery = true)
	List<EmailDetailsMst> getAllRecords(Integer tenantId);
	
	@Query(value = "select * from D008005 with (nolock) where notifyType = ? and typeId = ? and authStatus = 'A' and isActive = 1 ",  nativeQuery = true)
	EmailDetailsMst getAllRecordsForEmail(String notifyType, String typeId);
	
	
}

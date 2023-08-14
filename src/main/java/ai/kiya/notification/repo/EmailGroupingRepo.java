package ai.kiya.notification.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ai.kiya.notification.entity.EmailGrouping;
import ai.kiya.notification.entity.EmailGroupingId;

@Repository
public interface EmailGroupingRepo extends JpaRepository<EmailGrouping, EmailGroupingId> {


	@Query(value = "select emailId from D002001 with (nolock) where tenantId = ? and loginId = ? and isActive = 1 and authStatus = 'A' ", nativeQuery = true)
	String getEmailForUser(Integer tenantId, String userId);
	
	@Query(value = "select userFName from D002001 with (nolock) where tenantId = ? and loginId = ? and isActive = 1 and authStatus = 'A' ", nativeQuery = true)
	String getNameForUser(Integer tenantId, String userId);
	
	@Query(value = "select bod from D001003 with (nolock) where tenantId = :tenantId and authStatus = 'A' and isActive = 1 and branchCode = (select userBaseBranchCode from D002001 with (nolock) where loginId = :userId and authStatus = 'A' and isActive = 1 and tenantId = :tenantId )", nativeQuery = true)
	public Date getOperationalDateForUser(Integer tenantId, String userId);
	
	
	@Query(value = "select loginId,emailId from D002001 with (nolock) where tenantId = ? and roleCode = ? and isActive = 1 and authStatus = 'A' ", nativeQuery = true)
	List<Map<String, String>> getUsersForRole(Integer tenantId, String roleCode);

}

package ai.kiya.notification.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.kiya.notification.entity.NotificationMst;

public interface LoanRepository extends JpaRepository<NotificationMst, Long> {
	 
    @Query(value = "SELECT D030242.loanLimitdate, D009011.emailId " +
                   "FROM D030242 " +
                   "JOIN D030042 ON D030242.prdAcctId = D030042.prdAcctId " +
                   "JOIN D009022 ON D030242.prdAcctId = D009022.prdAcctId " +
                   "JOIN D009011 ON D009022.memberCode = D009011.memberCode " +
                   "WHERE D030242.prdAcctId = :prdAcctId ", nativeQuery = true)
    List<Object[]> getLoanLimitAndEmail(String prdAcctId);
}

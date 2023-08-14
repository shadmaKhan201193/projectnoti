package ai.kiya.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.kiya.notification.entity.EmailDefination;
import ai.kiya.notification.entity.EmailDefinationId;

@Repository
public interface EmailDefinationRepo extends JpaRepository<EmailDefination, EmailDefinationId> {

	
}

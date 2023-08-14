package ai.kiya.notification.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class EmailRecipientsMst {

	@EmbeddedId
	private EmailRecipientsKey emailRecipientsKey = new EmailRecipientsKey();
	
	private String additionalEmailId;
	private String authStatus;
	private Integer isActive;
	private String time;
	private String userIds;
    private String userRole;
    
    
	public EmailRecipientsKey getEmailRecipientsKey() {
		return emailRecipientsKey;
	}
	public void setEmailRecipientsKey(EmailRecipientsKey emailRecipientsKey) {
		this.emailRecipientsKey = emailRecipientsKey;
	}
	public String getAdditionalEmailId() {
		return additionalEmailId;
	}
	public void setAdditionalEmailId(String additionalEmailId) {
		this.additionalEmailId = additionalEmailId;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public int hashCode() {
		return Objects.hash(additionalEmailId, authStatus, emailRecipientsKey, isActive, time, userIds, userRole);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailRecipientsMst other = (EmailRecipientsMst) obj;
		return Objects.equals(additionalEmailId, other.additionalEmailId)
				&& Objects.equals(authStatus, other.authStatus)
				&& Objects.equals(emailRecipientsKey, other.emailRecipientsKey)
				&& Objects.equals(isActive, other.isActive) && Objects.equals(time, other.time)
				&& Objects.equals(userIds, other.userIds) && Objects.equals(userRole, other.userRole);
	}
	
	
	
}

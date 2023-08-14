package ai.kiya.notification.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
//@Getter
//@Setter
public class NotificationMst  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
	@SequenceGenerator(name = "my_sequence", sequenceName = "name_of_existing_sequence")
	private Integer notificationId;
	
	
	
	private String role;

	@Column(nullable = false)
	private Boolean readYN;
	
	@Column(nullable=false)
	private Date notificationCreationTd;

	private String appName;
	private String mailId;
	private String sourceUserId;
	private String targetUserId;
	private String ccEmailId;
	private String message;
	
	private String url;
	private String stringJson;
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getReadYN() {
		return readYN;
	}
	public void setReadYN(Boolean readYN) {
		this.readYN = readYN;
	}
	public Date getNotificationCreationTd() {
		return notificationCreationTd;
	}
	public void setNotificationCreationTd(Date notificationCreationTd) {
		this.notificationCreationTd = notificationCreationTd;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getSourceUserId() {
		return sourceUserId;
	}
	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
	public String getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	public String getCcEmailId() {
		return ccEmailId;
	}
	public void setCcEmailId(String ccEmailId) {
		this.ccEmailId = ccEmailId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStringJson() {
		return stringJson;
	}
	public void setStringJson(String stringJson) {
		this.stringJson = stringJson;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

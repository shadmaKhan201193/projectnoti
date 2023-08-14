package ai.kiya.notification.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity	
public class MasterNotification  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer notificationId;

	private String role="";

	@Column(nullable = false)
	private Boolean readYN;
	
	@Column(nullable=false)
	private Date datetime;

	
	private String appName;
	private String mailId;
	private String sourceId;
	
	private String ccEmailId;
	
	private String stringJson;
	
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
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
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public Boolean getReadYN() {
		return readYN;
	}
	public void setReadYN(Boolean readYN) {
		this.readYN = readYN;
	}
	public String getStringJson() {
		return stringJson;
	}
	public void setStringJson(String stringJson) {
		this.stringJson = stringJson;
	}
	public String getCcEmailId() {
		return ccEmailId;
	}
	public void setCcEmailId(String ccEmailId) {
		this.ccEmailId = ccEmailId;
	}
	
	
	
	
	

}

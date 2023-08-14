package ai.kiya.notification.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EmailDetailsMst {

	@EmbeddedId
	private EmailDetailsKey EmailDetailsKey = new EmailDetailsKey();

	private String authStatus;
	private String cc;
	private String bcc;
	private Integer isActive;
	private String mailBody;
	private Integer status;
	private String subjectLine;

	public EmailDetailsKey getEmailDetailsKey() {
		return EmailDetailsKey;
	}

	public void setEmailDetailsKey(EmailDetailsKey emailDetailsKey) {
		EmailDetailsKey = emailDetailsKey;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(EmailDetailsKey, authStatus, bcc, cc, isActive, mailBody, status, subjectLine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailDetailsMst other = (EmailDetailsMst) obj;
		return Objects.equals(EmailDetailsKey, other.EmailDetailsKey) && Objects.equals(authStatus, other.authStatus)
				&& Objects.equals(bcc, other.bcc) && Objects.equals(cc, other.cc)
				&& Objects.equals(isActive, other.isActive) && Objects.equals(mailBody, other.mailBody)
				&& Objects.equals(status, other.status) && Objects.equals(subjectLine, other.subjectLine);
	}

}

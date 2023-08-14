package ai.kiya.notification.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "D008002")
public class EmailGrouping {

	@EmbeddedId
	private EmailGroupingId EmailGroupingId = new EmailGroupingId();
	private Integer sequence;


	public EmailGroupingId getEmailGroupingId() {
		return EmailGroupingId;
	}

	public void setEmailGroupingId(EmailGroupingId emailGroupingId) {
		EmailGroupingId = emailGroupingId;
	}

	

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(EmailGroupingId, sequence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailGrouping other = (EmailGrouping) obj;
		return Objects.equals(EmailGroupingId, other.EmailGroupingId) && Objects.equals(sequence, other.sequence);
	}



}

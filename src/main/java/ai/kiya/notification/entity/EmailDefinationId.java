package ai.kiya.notification.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class EmailDefinationId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer tenantId;
	private Integer processId;
	
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public EmailDefinationId(Integer tenantId, Integer processId) {
		super();
		this.tenantId = tenantId;
		this.processId = processId;
	}
	public EmailDefinationId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(processId, tenantId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailDefinationId other = (EmailDefinationId) obj;
		return Objects.equals(processId, other.processId) && Objects.equals(tenantId, other.tenantId);
	}
	
	
}

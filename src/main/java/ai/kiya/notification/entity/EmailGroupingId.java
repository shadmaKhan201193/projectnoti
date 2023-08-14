package ai.kiya.notification.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class EmailGroupingId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer tenantId;
	private String groupName;
	private Integer processId;
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	@Override
	public String toString() {
		return "ProcessGroupingId [tenantId=" + tenantId + ", groupName=" + groupName + ", processId=" + processId
				+ "]";
	}
	public EmailGroupingId(Integer tenantId, String groupName, Integer processId) {
		super();
		this.tenantId = tenantId;
		this.groupName = groupName;
		this.processId = processId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(groupName, processId, tenantId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailGroupingId other = (EmailGroupingId) obj;
		return Objects.equals(groupName, other.groupName) && Objects.equals(processId, other.processId)
				&& Objects.equals(tenantId, other.tenantId);
	}
	public EmailGroupingId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

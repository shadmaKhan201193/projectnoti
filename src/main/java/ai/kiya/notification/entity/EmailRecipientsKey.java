package ai.kiya.notification.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class EmailRecipientsKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer tenantId;
	private String notifyType;
	private String typeId;
	private Long srNo;
	
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Long getSrNo() {
		return srNo;
	}
	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(notifyType, srNo, tenantId, typeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailRecipientsKey other = (EmailRecipientsKey) obj;
		return Objects.equals(notifyType, other.notifyType) && Objects.equals(srNo, other.srNo)
				&& Objects.equals(tenantId, other.tenantId) && Objects.equals(typeId, other.typeId);
	}

	
	
}

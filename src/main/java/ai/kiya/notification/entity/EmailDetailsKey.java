package ai.kiya.notification.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class EmailDetailsKey implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer tenantId;
	private String notifyType;
	private String typeId;
	
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
	@Override
	public int hashCode() {
		return Objects.hash(notifyType, tenantId, typeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailDetailsKey other = (EmailDetailsKey) obj;
		return Objects.equals(notifyType, other.notifyType) && Objects.equals(tenantId, other.tenantId)
				&& Objects.equals(typeId, other.typeId);
	}
	
	
	
}

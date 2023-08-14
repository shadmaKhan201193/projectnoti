package ai.kiya.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserMst {

	private String roleCode = "";
	private String userId = "";
	private Integer tenantId = 0;
	private String loginId = "";
	private String telephone = "";
	private String mobileNo1 = "";
	private String mobileNo2 = "";
	private String faxNum = "";
	private String userCode = "";
	private String userSalutation = "";
	private String userFName = "";
	private String userMName = "";
	private String userLName = "";
	private String userDisplayName = "";
	private String userBaseBranchCode = "";
	private String emailId = "";
	private String employeeId = "";
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobileNo1() {
		return mobileNo1;
	}
	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}
	public String getMobileNo2() {
		return mobileNo2;
	}
	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserSalutation() {
		return userSalutation;
	}
	public void setUserSalutation(String userSalutation) {
		this.userSalutation = userSalutation;
	}
	public String getUserFName() {
		return userFName;
	}
	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}
	public String getUserMName() {
		return userMName;
	}
	public void setUserMName(String userMName) {
		this.userMName = userMName;
	}
	public String getUserLName() {
		return userLName;
	}
	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	public String getUserBaseBranchCode() {
		return userBaseBranchCode;
	}
	public void setUserBaseBranchCode(String userBaseBranchCode) {
		this.userBaseBranchCode = userBaseBranchCode;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	

}

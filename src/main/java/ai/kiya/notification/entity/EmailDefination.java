package ai.kiya.notification.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Entity(name = "D008001")
public class EmailDefination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	EmailDefinationId emailDefinationId = new EmailDefinationId();

	private String processName;
	private String frequency;
	private String processStage;
	private String scheduled;
	private String executionTime;
	private String notificationType;
	private String triggerQueue;
	private String triggerTable;
	private String triggerApi;
	private String processApi;
	private String branchWise;
	private Boolean paused;
	private Date lastModifiedDate;
	private Date lastModifiedTime;
	private Integer noOfThreads;
	private Integer spExecutionYN;
	private String spName;
	private String inputParam1;
	private String inputParam2;
	private String inputParam3;
	private String inputParam4;
	private String inputParam5;

	public EmailDefinationId getEmailDefinationId() {
		return emailDefinationId;
	}

	public void setEmailDefinationId(EmailDefinationId emailDefinationId) {
		this.emailDefinationId = emailDefinationId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getProcessStage() {
		return processStage;
	}

	public void setProcessStage(String processStage) {
		this.processStage = processStage;
	}

	public String getScheduled() {
		return scheduled;
	}

	public void setScheduled(String scheduled) {
		this.scheduled = scheduled;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getTriggerQueue() {
		return triggerQueue;
	}

	public void setTriggerQueue(String triggerQueue) {
		this.triggerQueue = triggerQueue;
	}

	public String getTriggerTable() {
		return triggerTable;
	}

	public void setTriggerTable(String triggerTable) {
		this.triggerTable = triggerTable;
	}

	public String getTriggerApi() {
		return triggerApi;
	}

	public void setTriggerApi(String triggerApi) {
		this.triggerApi = triggerApi;
	}

	public String getProcessApi() {
		return processApi;
	}

	public void setProcessApi(String processApi) {
		this.processApi = processApi;
	}

	public String getBranchWise() {
		return branchWise;
	}

	public void setBranchWise(String branchWise) {
		this.branchWise = branchWise;
	}

	public Boolean getPaused() {
		return paused;
	}

	public void setPaused(Boolean paused) {
		this.paused = paused;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Integer getNoOfThreads() {
		return noOfThreads;
	}

	public void setNoOfThreads(Integer noOfThreads) {
		this.noOfThreads = noOfThreads;
	}

	public Integer getSpExecutionYN() {
		return spExecutionYN;
	}

	public void setSpExecutionYN(Integer spExecutionYN) {
		this.spExecutionYN = spExecutionYN;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getInputParam1() {
		return inputParam1;
	}

	public void setInputParam1(String inputParam1) {
		this.inputParam1 = inputParam1;
	}

	public String getInputParam2() {
		return inputParam2;
	}

	public void setInputParam2(String inputParam2) {
		this.inputParam2 = inputParam2;
	}

	public String getInputParam3() {
		return inputParam3;
	}

	public void setInputParam3(String inputParam3) {
		this.inputParam3 = inputParam3;
	}

	public String getInputParam4() {
		return inputParam4;
	}

	public void setInputParam4(String inputParam4) {
		this.inputParam4 = inputParam4;
	}

	public String getInputParam5() {
		return inputParam5;
	}

	public void setInputParam5(String inputParam5) {
		this.inputParam5 = inputParam5;
	}

	public EmailDefination() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmailDefination [emailDefinationId=" + emailDefinationId + ", processName=" + processName
				+ ", frequency=" + frequency + ", processStage=" + processStage + ", scheduled=" + scheduled
				+ ", executionTime=" + executionTime + ", notificationType=" + notificationType + ", triggerQueue="
				+ triggerQueue + ", triggerTable=" + triggerTable + ", triggerApi=" + triggerApi + ", processApi="
				+ processApi + ", branchWise=" + branchWise + ", paused=" + paused + ", lastModifiedDate="
				+ lastModifiedDate + ", lastModifiedTime=" + lastModifiedTime + ", noOfThreads=" + noOfThreads
				+ ", spExecutionYN=" + spExecutionYN + ", spName=" + spName + ", inputParam1=" + inputParam1
				+ ", inputParam2=" + inputParam2 + ", inputParam3=" + inputParam3 + ", inputParam4=" + inputParam4
				+ ", inputParam5=" + inputParam5 + "]";
	}

	public EmailDefination(EmailDefinationId emailDefinationId, String processName, String frequency,
			String processStage, String scheduled, String executionTime, String notificationType, String triggerQueue,
			String triggerTable, String triggerApi, String processApi, String branchWise, Boolean paused,
			Date lastModifiedDate, Date lastModifiedTime, Integer noOfThreads, Integer spExecutionYN, String spName,
			String inputParam1, String inputParam2, String inputParam3, String inputParam4, String inputParam5) {
		super();
		this.emailDefinationId = emailDefinationId;
		this.processName = processName;
		this.frequency = frequency;
		this.processStage = processStage;
		this.scheduled = scheduled;
		this.executionTime = executionTime;
		this.notificationType = notificationType;
		this.triggerQueue = triggerQueue;
		this.triggerTable = triggerTable;
		this.triggerApi = triggerApi;
		this.processApi = processApi;
		this.branchWise = branchWise;
		this.paused = paused;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedTime = lastModifiedTime;
		this.noOfThreads = noOfThreads;
		this.spExecutionYN = spExecutionYN;
		this.spName = spName;
		this.inputParam1 = inputParam1;
		this.inputParam2 = inputParam2;
		this.inputParam3 = inputParam3;
		this.inputParam4 = inputParam4;
		this.inputParam5 = inputParam5;
	}

}

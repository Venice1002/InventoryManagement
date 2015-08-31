package cn.edu.zucc.inventorymanagement.model;

	/**
	*	*@author yangsj
	*/

	public class Worker{
	private int workerId;
	private String workerName;
	private String workerPwd;
	private int workerPermission;
	private String workerDepartment;
	private String workerPhone;

	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}
	public String getWorkerName(){
		return this.workerName;
	}
	public void setWorkerName(String workerName){
		this.workerName=workerName;
	}
	public String getWorkerPwd(){
		return this.workerPwd;
	}
	public void setWorkerPwd(String workerPwd){
		this.workerPwd=workerPwd;
	}
	public int getWorkerPermission(){
		return this.workerPermission;
	}
	public void setWorkerPermission(int workerPermission){
		this.workerPermission=workerPermission;
	}
	public String getWorkerDepartment(){
		return this.workerDepartment;
	}
	public void setWorkerDepartment(String workerDepartment){
		this.workerDepartment=workerDepartment;
	}
	public String getWorkerPhone(){
		return this.workerPhone;
	}
	public void setWorkerPhone(String workerPhone){
		this.workerPhone=workerPhone;
	}

}
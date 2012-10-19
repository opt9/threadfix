package com.denimgroup.threadfix.data.entities;

public enum Permission {
	CAN_GENERATE_REPORTS("CAN_GENERATE_REPORTS"),
	CAN_GENERATE_WAF_RULES("CAN_GENERATE_WAF_RULES"),
	CAN_MANAGE_API_KEYS("CAN_MANAGE_API_KEYS"),
	CAN_MANAGE_APPLICATIONS("CAN_MANAGE_APPLICATIONS"),
	CAN_MANAGE_GROUPS("CAN_MANAGE_GROUPS"),
	CAN_MANAGE_REMOTE_PROVIDERS("CAN_MANAGE_REMOTE_PROVIDERS"),
	CAN_MANAGE_ROLES("CAN_MANAGE_ROLES"),
	CAN_MANAGE_TEAMS("CAN_MANAGE_TEAMS"),
	CAN_MANAGE_USERS("CAN_MANAGE_USERS"),
	CAN_MANAGE_WAFS("CAN_MANAGE_WAFS"),
	CAN_MODIFY_VULNERABILITIES("CAN_MODIFY_VULNERABILITIES"),
	CAN_SUBMIT_DEFECTS("CAN_SUBMIT_DEFECTS"),
	CAN_UPLOAD_SCANS("CAN_UPLOAD_SCANS"),
	CAN_VIEW_ERROR_LOGS("CAN_VIEW_ERROR_LOGS"),
	CAN_VIEW_JOB_STATUSES("CAN_VIEW_JOB_STATUSES");

	private String text;
	
	public String getText() { 
		return this.text; 
	}

	Permission(String text) { 
		this.text = text; 
	}

}

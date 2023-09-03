package com.jiraservice.JavaToJira.payload;


public class IssueBody {
	String key;
	String summary;
	String description;
	String issueTypeName;
	
	
	
	public IssueBody() {
		super();
	}
	
	public IssueBody(String key, String summary, String description, String issueTypeName) {
		super();
		this.key = key;
		this.summary = summary;
		this.description = description;
		this.issueTypeName = issueTypeName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getIssueTypeName() {
		return issueTypeName;
	}
	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}
	
}

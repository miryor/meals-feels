package org.sampledsu.common.models;

public class ConcordiaMeta {
	
	private String id;
	private String timestamp;
	public ConcordiaMeta(String id, String timestamp) {
		super();
		this.id = id;
		this.timestamp = timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}

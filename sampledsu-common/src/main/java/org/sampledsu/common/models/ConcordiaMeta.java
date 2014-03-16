package org.sampledsu.common.models;

/**
 * Actually should be DsuMeta as this is meant to represent the meta data
 * for our object in DSU.
 * Expectation is that Jersey will format this in JSON correctly
 * please see com.sun.jersey.api.json.POJOMappingFeature
 * @author royrim
 *
 */
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

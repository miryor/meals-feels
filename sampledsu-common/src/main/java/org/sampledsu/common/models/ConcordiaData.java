package org.sampledsu.common.models;

/**
 * Actually this should be labeled DsuData,
 * its meant to contain the meta data and actual data of an object
 * we are trying to return in a DSU compatible format.
 * Expectation is that Jersey will format this in JSON correctly
 * please see com.sun.jersey.api.json.POJOMappingFeature
 * @author royrim
 *
 */
public class ConcordiaData<ConcordiaMeta,T> {
	
	private ConcordiaMeta meta;
	private T data;
	public ConcordiaData(ConcordiaMeta meta, T data) {
		super();
		this.meta = meta;
		this.data = data;
	}
	public ConcordiaMeta getMeta() {
		return meta;
	}
	public void setMeta(ConcordiaMeta meta) {
		this.meta = meta;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}

package org.sampledsu.common.models;

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

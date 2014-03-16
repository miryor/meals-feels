package org.sampledsu.common.models;

public class ConcordiaField {
	private String name;
	private String type;
	private boolean optional;
	private String doc;
	public ConcordiaField(String name, String type, boolean optional, String doc) {
		super();
		this.name = name;
		this.type = type;
		this.optional = optional;
		this.doc = doc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
}

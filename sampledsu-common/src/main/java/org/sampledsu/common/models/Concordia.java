package org.sampledsu.common.models;

/**
 * Represents parent Concordia schema
 * Expectation is that Jersey will format this in JSON correctly
 * please see com.sun.jersey.api.json.POJOMappingFeature
 * @author royrim
 *
 */
public class Concordia {
	
	public static String TYPE_OBJECT = "object";
	public static String TYPE_STRING = "string";
	public static String TYPE_NUMBER = "number";
	public static String TYPE_BOOLEAN = "boolean";
	public static String TYPE_ARRAY = "array";
	
	private String type;
	private String doc;
	private ConcordiaField[] fields;
	public Concordia(String type, String doc, ConcordiaField[] fields) {
		super();
		this.type = type;
		this.doc = doc;
		this.fields = fields;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public ConcordiaField[] getFields() {
		return fields;
	}
	public void setFields(ConcordiaField[] fields) {
		this.fields = fields;
	}

}

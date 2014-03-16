package org.sampledsu.common.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@CompoundIndex(name = "user_entrydate_idx", def = "{'user' : 1, 'entryDate' : 1}")
@Document
public class Pain {

	@Id
    private String id;
	
	@Version
	private Long version;
	
	@CreatedBy
	private String user;

	@LastModifiedBy
	@DateTimeFormat( pattern = "MM/dd/yyyy HH:mm" )
	private String modifiedBy;

	@CreatedDate
	@DateTimeFormat( pattern = "MM/dd/yyyy HH:mm" )
	private DateTime createdDate;

	@LastModifiedDate
	@DateTimeFormat( pattern = "MM/dd/yyyy HH:mm" )
	private DateTime updateDate;
	
	@DateTimeFormat( pattern = "MM/dd/yyyy HH:mm" )
	private DateTime entryDate;
	
	private String gas;
	private String bloating;
	private String abdominal;
	private String burning;
	private String headache;
	private String lethargy;
	private String joints;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	public DateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(DateTime updateDate) {
		this.updateDate = updateDate;
	}
	public DateTime getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(DateTime entryDate) {
		this.entryDate = entryDate;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getBloating() {
		return bloating;
	}
	public void setBloating(String bloating) {
		this.bloating = bloating;
	}
	public String getAbdominal() {
		return abdominal;
	}
	public void setAbdominal(String abdominal) {
		this.abdominal = abdominal;
	}
	public String getBurning() {
		return burning;
	}
	public void setBurning(String burning) {
		this.burning = burning;
	}
	public String getHeadache() {
		return headache;
	}
	public void setHeadache(String headache) {
		this.headache = headache;
	}
	public String getLethargy() {
		return lethargy;
	}
	public void setLethargy(String lethargy) {
		this.lethargy = lethargy;
	}
	public String getJoints() {
		return joints;
	}
	public void setJoints(String joints) {
		this.joints = joints;
	}

}

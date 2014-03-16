package org.sampledsu.common.models;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
 
@CompoundIndex(name = "user_entrydate_idx", def = "{'user' : 1, 'entryDate' : 1}")
@Document
public class Meal {

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
	
	private String mealType;
	
	private String mealName;
	
	private String containsGluten;
	
	private String brand;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getContainsGluten() {
		return containsGluten;
	}
	public void setContainsGluten(String containsGluten) {
		this.containsGluten = containsGluten;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}

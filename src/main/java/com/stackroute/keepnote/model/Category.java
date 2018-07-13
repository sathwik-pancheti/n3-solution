package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * The class "Category" will be acting as the data model for the Category Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */
@Entity
public class Category {
	/*
	 * This class should have six fields
	 * (categoryId,categoryName,categoryDescription,
	 * categoryCreatedBy,categoryCreationDate,notes). Out of these six fields, the
	 * field categoryId should be primary key and auto-generated. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. The value of
	 * categoryCreationDate should not be accepted from the user but should be
	 * always initialized with the system date. annotate notes field with @OneToMany
	 * and @JsonIgnore
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int categoryId;
	@Column(nullable = false)
	String categoryName;
	@Column(nullable = false)
	String categoryDescription;
	@Column(nullable = false)
	String categoryCreatedBy;
	
	Date categoryCreationDate;
	
	
	@OneToMany
	@JsonIgnore
	List<Note> notes;

	public Category() {

	}

	

	public Category(int categoryId, String categoryName, String categoryDescription,Date categoryCreationDate,
	 String categoryCreatedBy,List<Note> notes) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categoryCreatedBy = categoryCreatedBy;
		this.categoryCreationDate = categoryCreationDate;
		this.notes = notes;
	}



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getCategoryDescription() {
		return categoryDescription;
	}



	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}



	public String getCategoryCreatedBy() {
		return categoryCreatedBy;
	}



	public void setCategoryCreatedBy(String categoryCreatedBy) {
		this.categoryCreatedBy = categoryCreatedBy;
	}



	public Date getCategoryCreationDate() {
		return categoryCreationDate;
	}



	public void setCategoryCreationDate(Date categoryCreationDate) {
		this.categoryCreationDate = categoryCreationDate;
	}



	public List<Note> getNotes() {
		return notes;
	}



	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	

}
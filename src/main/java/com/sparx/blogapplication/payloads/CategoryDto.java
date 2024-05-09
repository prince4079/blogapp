package com.sparx.blogapplication.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoryDto {
	private Integer categoryId;
	
	@Pattern(regexp="^[a-zA-Z ]+$",message="title  Must Contain only Alphabets ")
	@NotEmpty(message="Title cannot be Empty")
	@Schema(
		    description = "title of the particular category ", 
		    name = "categoryTitle", 
		    type = "string", 
		    example = "motor blogging ")
	private String categoryTitle;
	@NotEmpty(message="description cannot be empty ")
	@Size(min=10,max=250,message="description must be within 10 to 250 character ")
	private String categoryDescription;
	
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryDto(Integer categoryId, String categoryTitle, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	

}

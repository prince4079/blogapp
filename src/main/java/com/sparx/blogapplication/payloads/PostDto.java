package com.sparx.blogapplication.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sparx.blogapplication.entities.Category;
import com.sparx.blogapplication.entities.Comment;
import com.sparx.blogapplication.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PostDto {
	private Integer postId;
	@Size(min=5,max=150, message="title must be provided between 5 to 15 charachter")
	@Pattern(regexp="^[a-zA-Z ]+$",message="title  Must Contain only Alphabets ")
	private String postTitle;
	@Size(min=10,max=500, message="title must be provided between 10 to 500 charachter")
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
    private Set<Comment> comments=new HashSet<>();
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDto(Integer postId,
			@Size(min = 5, max = 150, message = "title must be provided between 5 to 15 charachter") @Pattern(regexp = "^[a-zA-Z ]+$", message = "title  Must Contain only Alphabets ") String postTitle,
			@Size(min = 10, max = 500, message = "title must be provided between 10 to 500 charachter") String content,
			String imageName, Date addedDate, CategoryDto category, UserDto user, Set<Comment> comments) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
		this.comments=comments;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	

	

	
}

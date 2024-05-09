package com.sparx.blogapplication.payloads;

public class UserResponseDto {
	private String name ;
	private String email;
	private String about;
	
	public UserResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponseDto(String name, String email, String about) {
		super();
		this.name = name;
		this.email = email;
		this.about = about;
	}
	@Override
	public String toString() {
		return "UserResponseDto [name=" + name + ", email=" + email + ", about=" + about + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
}

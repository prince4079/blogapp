package com.sparx.blogapplication.payloads;

public class CommentDto {
	private int cId;
	private String content;
	public CommentDto(int cId, String content) {
		super();
		this.cId = cId;
		this.content = content;
	}
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}

package com.sparx.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.payloads.ApiResponse;
import com.sparx.blogapplication.payloads.CommentDto;
import com.sparx.blogapplication.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comment")
@Tag(name="CommentController", description="this Controller is realted for theh comment related Operations ")
public class CommentController {
	@Autowired
	private CommentService commentService;
	// adding a comment 
    @PostMapping("/add/{postId}")
	public ResponseEntity<CommentDto> addComment(
	@RequestBody CommentDto commentDto,@PathVariable("postId")Integer postId)
			{
    	
		CommentDto addedComment =commentService.addComment(commentDto, postId);
		return new ResponseEntity<CommentDto> (addedComment,HttpStatus.OK);
	}
    // deleting a comment 
    @DeleteMapping("/delete/{cId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer cId){
    	String message=commentService.deleteComment(cId);
    	return new ResponseEntity<ApiResponse> (new ApiResponse(message,true),HttpStatus.OK);
    }
}

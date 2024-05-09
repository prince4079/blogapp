package com.sparx.blogapplication.service;

import java.util.List;

import com.sparx.blogapplication.entities.Post;
import com.sparx.blogapplication.payloads.PostDto;
import com.sparx.blogapplication.payloads.PostResponse;

public interface PostService {
	//create 
	PostDto createPost(PostDto postDto,Integer  userId,Integer  categoryId);
	
	// update 
	PostDto updatePost(PostDto postDto,Integer postId);
	
	// delete 
	
	String  deletePost(Integer postId);
	
	// get a single Post 
	
	PostDto getPostById(Integer postId);
	
	// get All Post 
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	// get All post by Category id
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get All Post By User 
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//search post 
	//List<PostDto> searchPosts(String keyword);
	

}

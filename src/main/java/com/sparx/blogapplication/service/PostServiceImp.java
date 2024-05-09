package com.sparx.blogapplication.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sparx.blogapplication.entities.Category;
import com.sparx.blogapplication.entities.Post;
import com.sparx.blogapplication.entities.User;
import com.sparx.blogapplication.exception.ResourceNotFoundException;
import com.sparx.blogapplication.payloads.PostDto;
import com.sparx.blogapplication.payloads.PostResponse;
import com.sparx.blogapplication.repository.CategoryRepository;
import com.sparx.blogapplication.repository.PostRepository;
import com.sparx.blogapplication.repository.UserRepository;
import org.springframework.data.domain.*;
@Service
public class PostServiceImp implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));
		Post post = modelMapper.map(postDto, Post.class);
//		Post post=new Post();
//		post.setPostTitle(postDto.getPostTitle());
//		post.setContent(postDto.getContent());
		post.setImageName("Default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post addedPost = postRepository.save(post);
		return modelMapper.map(addedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = postRepository.save(post);
		PostDto updatedPostDto = modelMapper.map(updatedPost, PostDto.class);
		return updatedPostDto;
	}
    
	@Override
	public String deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post fetchedPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));
		postRepository.delete(fetchedPost);
		return "post deleted Sucessfully with id: " + postId;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post fetchedPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));
		PostDto postDto = modelMapper.map(fetchedPost, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		org.springframework.data.domain.Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			
			 sort=org.springframework.data.domain.Sort.by(sortBy).ascending();
		}else {
			
			sort=org.springframework.data.domain.Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		// TODO Auto-generated method stub
		Page<Post> pagePost = postRepository.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		// List<Post> postList = postRepository.findAll();
		List<PostDto> postDtoList = allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtoList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
//		Pageable pagePost =PageRequest.of(pageNumber, pageSize);
//		Category fetcheCategory = categoryRepository.findById(categoryId)
//				.orElseThrow(() -> new ResourceNotFoundException("category", "category id ", categoryId));
//		Page<Post> allpost = (Page<Post>) postRepository.findByCategory(fetcheCategory,pagePost);
//	  List<Post> foundList=	allpost.getContent();
//		List<PostDto> postDtoList = foundList.stream()
//				.map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList()); 
		// return postDtoList;
		Category fetcheCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id ", categoryId));
		List<Post> fetchePostListByCategory = postRepository.findByCategory(fetcheCategory);
		List<PostDto> postDtoList = fetchePostListByCategory.stream()
				.map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;

	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));
		List<Post> postListByUserId = postRepository.findByUser(user);
		List<PostDto> postlist = postListByUserId.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postlist;
	}
    
//	@Override
//	public List<PostDto> searchPosts(String keyword) {
//		// TODO Auto-generated method stub
//		List<Post> postlist=postRepository.findByTitleContaining(keyword);
//		
//		return postlist.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList()) ;
//	}

}

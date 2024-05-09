package com.sparx.blogapplication.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sparx.blogapplication.config.AppConstant;
import com.sparx.blogapplication.payloads.ApiResponse;
import com.sparx.blogapplication.payloads.PostDto;
import com.sparx.blogapplication.payloads.PostResponse;
import com.sparx.blogapplication.service.FileService;
import com.sparx.blogapplication.service.PostService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
@EnableMethodSecurity(prePostEnabled = true)
@Tag(name="Post Controller " ,description=" This Controller is define to handle Post Related Operation ")
public class PostController {
	@Autowired
	private FileService fileService;
	@Autowired
	private PostService postService;
	@PostMapping("/create/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") 
	Integer userId, @PathVariable("categoryId") Integer categoryId){
		PostDto addedPost=postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto> (addedPost,HttpStatus.CREATED);
		
	}
	// getting all post from the database 
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",description="Post fetched Sucessfully"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",description ="unauthorised Request ")
	
	})
	
	/* @Parameter annotation helps to specify the name,  description, and example value of the parameter 
	 * And @Schema annotation allows us to control Swagger-specific 
	 * definitions such as description, name, type, example values,
	 *  and allowed values for the model properties.*/
	@Parameters(
			{
				
				@Parameter(
			            name =  "pageNumber",
			            description  = "it will show which page data we are seeing ",
			            example = "1",
			            required = false) ,
				@Parameter(
			            name =  "pageSize",
			            description  = "how may record will be there on a single page ",
			            example = "5",
			            required = false),
				@Parameter(
			            name =  "sortBy",
			            description  = "This will represnt how the data will be sorted when representing  ",
			            example = "Id",
			            required = false),
				@Parameter(
			            name =  "sortDir",
			            description  = "this will represnt the data will be in which order  ",
			            example = "asc",
			            required = false)
				
				
			})
			
	@GetMapping("/allPost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstant.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue=AppConstant.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstant.SORT_BY,required=false)String sortBy,
			@RequestParam(value="sortDir" ,defaultValue=AppConstant.SORT_DIR,required=false)String sortDir 
			){
		PostResponse postResponse= postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return ResponseEntity.status(HttpStatus.OK).body(postResponse);
	}
	// getting post by post id
	@GetMapping("/postid/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
		PostDto fetchedPost=postService.getPostById(postId);
		return new ResponseEntity<PostDto>(fetchedPost,HttpStatus.OK);
	}
    // deleteing post by post Id 
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		String message=postService.deletePost(postId);
		ApiResponse response=new ApiResponse();
		response.setMessage(message);
		response.setSucess(false);
		return new ResponseEntity<ApiResponse> (response,HttpStatus.OK);
	}
	// upadate a post  
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatedPostDto=postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto> (updatedPostDto,HttpStatus.OK);
	}
	
	// fetching all the post which belongs to a particular Category by Category id 
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostListByCategory(@PathVariable Integer categoryId){
		List<PostDto> postlist=postService.getPostsByCategory(categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(postlist);
	}
	// fetching all  the post that has been posted by a Particular User based Upon their id 
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable  Integer  userId){
		 
	     List<PostDto> postDtoListbyUserId=postService.getPostsByUser(userId);
	     return new ResponseEntity<List<PostDto>>(postDtoListbyUserId,HttpStatus.OK);
	}
	// api for performing the searching operations 
//	@GetMapping("/search/{keywords}")
//	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
//		List<PostDto> postDto=postService.searchPosts(keywords);
//		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
//	}
	
	// post image Upload 
	@PostMapping("/upload/image/{postId}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable("postId") Integer postId,
			@RequestParam("file")MultipartFile file){
		PostDto postDto=postService.getPostById(postId);
		String fileName=fileService.upload("image", file);
		postDto.setImageName(fileName);
		PostDto updatedPost=postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	// method to serve file 
		@GetMapping(value="/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=	fileService.getResource("image/", imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    StreamUtils.copy(resource, response.getOutputStream());
		
		}
	
}

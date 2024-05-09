package com.sparx.blogapplication.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparx.blogapplication.entities.Comment;
import com.sparx.blogapplication.entities.Post;
import com.sparx.blogapplication.exception.ResourceNotFoundException;
import com.sparx.blogapplication.payloads.CommentDto;
import com.sparx.blogapplication.payloads.PostDto;
import com.sparx.blogapplication.repository.CommentRepository;
import com.sparx.blogapplication.repository.PostRepository;

@Service
public class CommentServiceImp  implements CommentService{
    @Autowired
	private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public CommentDto addComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=postRepository.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("Post","post id",postId));
	    Comment comment=  modelMapper.map(commentDto, Comment.class);
	    comment.setPost(post);
		Comment saveComment=commentRepository.save(comment);
		CommentDto res=modelMapper.map(saveComment, CommentDto.class);
		return res;
	}

	@Override
	public String deleteComment(Integer cId) {
		// TODO Auto-generated method stub
		Comment commentResult=commentRepository.findById(cId).orElseThrow(()->new ResourceNotFoundException("comment","commennt id",cId));
		commentRepository.delete(commentResult);
		
		return "deleted Sucessfully";
	}

}

package com.sparx.blogapplication.service;

import com.sparx.blogapplication.payloads.CommentDto;

public interface CommentService {
   CommentDto addComment(CommentDto commentDto  , Integer postId);
   String deleteComment(Integer cId);
}

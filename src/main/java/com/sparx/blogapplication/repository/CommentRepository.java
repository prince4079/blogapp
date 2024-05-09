package com.sparx.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparx.blogapplication.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
 
}

package com.sparx.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparx.blogapplication.entities.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer>{

}

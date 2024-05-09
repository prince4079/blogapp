package com.sparx.blogapplication.service;

import java.util.List;

import com.sparx.blogapplication.payloads.CategoryDto;

public interface CategoryService {
	// add
	public CategoryDto addCategory(CategoryDto categoryDto);
	// get
	public CategoryDto getCategory (Integer categoryId);
	// getAll
	public List<CategoryDto> getAllCategory ();
	// update 
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	// delete 
	public String deleteCategory(Integer categoryId);

}

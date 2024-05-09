package com.sparx.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.payloads.CategoryDto;
import com.sparx.blogapplication.service.CategoryService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name="Category Controller" ,description="This Controller is defined for the Categories related Operations ")
//@Hidden // this annoation is bassically use to hide the particular Controller from the Swagger Documentation 
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	// adding a category 
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto addedCategory=categoryService.addCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);
	}
	// get a single category
	@GetMapping("/{categoryId}")
	@Parameter(
            name =  "categoryId",
            description  = "category id  Which identify the category ",
            example = "124",
            required = true) 
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto fetchedCategory=categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(fetchedCategory,HttpStatus.OK);
	}
	
	// get all the caregory 
	@GetMapping("/allCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
	List<CategoryDto> allfetchedCategory=	categoryService.getAllCategory();
	return new ResponseEntity<List<CategoryDto>>(allfetchedCategory,HttpStatus.OK);
	}
	
	// delete a category 
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId){
		String message =categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	// update a category 
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
	{
		CategoryDto upatedCategory=categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto> (upatedCategory,HttpStatus.OK);
	}
}

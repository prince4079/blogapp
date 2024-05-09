package com.sparx.blogapplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparx.blogapplication.entities.Category;
import com.sparx.blogapplication.exception.ResourceNotFoundException;
import com.sparx.blogapplication.payloads.CategoryDto;
import com.sparx.blogapplication.repository.CategoryRepository;
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired 
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category=modelMapper.map(categoryDto, Category.class);
		Category addedCategory =categoryRepository.save(category);
		CategoryDto addedCategoryDto=modelMapper.map(addedCategory, CategoryDto.class);
		return addedCategoryDto;
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category","category id",categoryId));
		CategoryDto fetchedUser=modelMapper.map(category,CategoryDto.class);
		return fetchedUser;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> fetchedList=categoryRepository.findAll();
		List<CategoryDto> result=fetchedList.stream()
		.map((cat) -> modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return  result;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category fetchedCategory=categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category","category id",categoryId));
		fetchedCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		fetchedCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory=categoryRepository.save(fetchedCategory);
		CategoryDto updateCategoryDto=modelMapper.map(updatedCategory,CategoryDto.class);
		return updateCategoryDto;
	}
    
	@Override
	public String deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category fetchedCatergory=categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("category","category id",categoryId));
		categoryRepository.delete(fetchedCatergory);
		return "resource deleted Sucessfully with "+categoryId;
	}
     
	
	

}

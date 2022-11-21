package com.blog.services.services.impl;

import com.blog.services.entities.Category;
import com.blog.services.exception.ResourceNotFoundException;
import com.blog.services.payloads.CategoryDTO;
import com.blog.services.repositories.CategoryRepo;
import com.blog.services.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{

    // Model mapper object
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        Category newCategory= this.categoryDtoToCategory(categoryDTO);
     Category saved= this.categoryRepo.save(newCategory);

        return this.categoryToCategoryDto(saved);
    }

    @Override
    public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
   Category fcat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category_Id",categoryId));
        this.categoryRepo.delete(fcat);

    }

    @Override
    public List<CategoryDTO> getAllCategory() {
     List<Category> categories=this.categoryRepo.findAll();

      List<CategoryDTO> categoryDTO= categories.stream().map(category -> this.categoryToCategoryDto(category)).collect(Collectors.toList());

        return categoryDTO;
    }

    @Override
    public CategoryDTO getSingleCategory(Integer categoryId) throws ResourceNotFoundException {
     Category fcat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category_Id",categoryId));
       return this.categoryToCategoryDto(fcat);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) throws ResourceNotFoundException {

     Category findCategory=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category_Id",categoryId));
      findCategory.setCategoryTitle(categoryDTO.getCategoryTitle());
      findCategory.setCategoryDescription(categoryDTO.getCategoryDescription());

     Category saved= this.categoryRepo.save(findCategory);
        return this.categoryToCategoryDto(saved);
    }

    // Model Mapper

    public CategoryDTO categoryToCategoryDto(Category category)
    {
        return this.modelMapper.map(category, CategoryDTO.class);
    }
    public Category categoryDtoToCategory(CategoryDTO categoryDTO)
    {
        return this.modelMapper.map(categoryDTO, Category.class);
    }
}

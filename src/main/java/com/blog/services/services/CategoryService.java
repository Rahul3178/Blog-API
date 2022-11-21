package com.blog.services.services;

import com.blog.services.exception.ResourceNotFoundException;
import com.blog.services.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService
{

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    void deleteCategory(Integer categoryId) throws ResourceNotFoundException;

    List<CategoryDTO> getAllCategory();

    CategoryDTO getSingleCategory(Integer categoryId) throws ResourceNotFoundException;

    CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryId) throws ResourceNotFoundException;

}

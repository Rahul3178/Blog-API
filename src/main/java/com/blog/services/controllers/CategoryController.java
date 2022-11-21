package com.blog.services.controllers;

import com.blog.services.exception.ResourceNotFoundException;
import com.blog.services.payloads.ApiResponse;
import com.blog.services.payloads.CategoryDTO;
import com.blog.services.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDTO> createUser(@Valid @RequestBody CategoryDTO categoryDTO)
    {
      CategoryDTO categoryDTO1=  this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/{cid}")
    public ResponseEntity<CategoryDTO> getSingleCat(@PathVariable Integer cid) throws ResourceNotFoundException {

        return ResponseEntity.ok(this.categoryService.getSingleCategory(cid));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getSingleCat() {

        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    @DeleteMapping("/delete/{cid}")
    public ResponseEntity<ApiResponse> deleteCat(@PathVariable Integer cid) throws ResourceNotFoundException {
        this.categoryService.deleteCategory(cid);
        return new ResponseEntity<>(new ApiResponse("Category deleted Successfully !!!", true), HttpStatus.OK);
    }

    @PutMapping("/update/{cid}")
    public ResponseEntity<CategoryDTO> updateCat(@Valid @RequestBody CategoryDTO categoryDTO ,@PathVariable Integer cid) throws ResourceNotFoundException {
     CategoryDTO update=  this.categoryService.updateCategory(categoryDTO,cid);
        return ResponseEntity.ok(update);
    }

}

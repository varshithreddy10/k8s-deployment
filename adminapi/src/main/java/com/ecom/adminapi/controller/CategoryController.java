package com.ecom.adminapi.controller;

import com.ecom.adminapi.dto.CategoryDto;
import com.ecom.adminapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class CategoryController
{
    // . . . . . . . . 1 time 2 time 3 time 4 time
    @Autowired
    private CategoryService categoryservice;


    @GetMapping("/check/adminapi")
    public ResponseEntity<String> checkapi()
    {
        String message = "adminapi";
        return new ResponseEntity<>(message , HttpStatus.CREATED);
    }

    @PostMapping("/add/category")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categorydto)
    {
        CategoryDto categorydtosaved = categoryservice.createCategory(categorydto);
        return new ResponseEntity<>(categorydtosaved , HttpStatus.CREATED);
    }

    @GetMapping("/get/all/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> allcategorydtos = categoryservice.getallCategory();
        return new ResponseEntity<>(allcategorydtos , HttpStatus.FOUND);
    }

    @GetMapping("/get/byid/{categoryId}")
    public ResponseEntity<CategoryDto> getCategorybyId(@PathVariable Long categoryId)
    {
        CategoryDto categorydto = categoryservice.getCategoryByIdserv(categoryId);
        return new ResponseEntity<>(categorydto , HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        String message = categoryservice.deleteCategorybyId(categoryId);
        return new ResponseEntity<>(message , HttpStatus.GONE);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId , @RequestBody CategoryDto categorydto)
    {
        CategoryDto categorydtoupdated = categoryservice.updateCategoryserv(categorydto , categoryId);
        return new ResponseEntity<>(categorydtoupdated , HttpStatus.OK);
    }

}

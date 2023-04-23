package com.project.librarymanagementsystem.controller;

import com.project.librarymanagementsystem.entity.Category;
import com.project.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public String findAllCategories(Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories/index";
    }

    @GetMapping("/add-category")
    public String addCategory(Category category){
        return "categories/add";
    }

    @PostMapping("/create-category")
    public String createCategory(Category category, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-category";
        }
        categoryService.createCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model){
        categoryService.deleteCategory(id);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories/index";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "categories/edit";
    }

    @PostMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Long id, Category category, BindingResult result, Model model){
        if(result.hasErrors()){
            return "categories/edit";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }
}

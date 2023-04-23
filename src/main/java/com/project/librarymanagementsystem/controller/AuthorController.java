package com.project.librarymanagementsystem.controller;

import com.project.librarymanagementsystem.entity.Author;
import com.project.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public String findAllAuthors(Model model){
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors/index";
    }
    @GetMapping("/add-author")
    public String addAuthor(Author author){
        return "authors/add";
    }

    @PostMapping("/create-author")
    public String createAuthor(Author author, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-author";
        }

        authorService.createAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/remove-author/{id}")
    public  String deleteAuthor(@PathVariable Long id, Model model){
        authorService.deleteAuthor(id);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors/index";
    }

    @GetMapping("/edit-author/{id}")
    public String editAuthor(@PathVariable Long id, Model model){
        model.addAttribute("author", authorService.findAuthorById(id));
        return "authors/edit";
    }

    @PostMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Author author, BindingResult result, Model model){
        if(result.hasErrors()){
            return "authors/edit";
        }
        authorService.updateAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors/index";
    }
}

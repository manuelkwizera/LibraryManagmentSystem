package com.project.librarymanagementsystem.controller;

import com.project.librarymanagementsystem.entity.Book;
import com.project.librarymanagementsystem.service.AuthorService;
import com.project.librarymanagementsystem.service.BookService;
import com.project.librarymanagementsystem.service.CategoryService;
import com.project.librarymanagementsystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/books")
    public String index(){
        return "books/index";
    }

    public String findAllBooks(Model model){
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "book/index";
    }

    @GetMapping("/add-book")
    public String addBook(Book book, Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "books/add";
    }
    @PostMapping("/save-book")
    public String saveBook(Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-book";
        }

        bookService.createBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }
}

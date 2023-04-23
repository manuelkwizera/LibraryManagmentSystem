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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    public String findAllBooks(Model model){
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books/index";
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

    @GetMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable Long id, Model model){
        bookService.deleteBook(id);
        model.addAttribute("books", bookService.findAllBooks());
        return "books/index";
    }
    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id, Model model){
        //Book book = bookService.findBookById(id);
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "books/edit";
    }
    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "edit-book";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }
}

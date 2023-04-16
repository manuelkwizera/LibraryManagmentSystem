package com.project.librarymanagementsystem.service;

import com.project.librarymanagementsystem.entity.Author;
import com.project.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    //fetch all the authors
    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }

    //fetch single author based on id
    public Author findAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not Found"));
        return author;
    }

    //create an author
    public void createAuthor(Author author){
        authorRepository.save(author);
    }

    //delete an author
    public void deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not Found"));
        authorRepository.deleteById(author.getId());
    }

}

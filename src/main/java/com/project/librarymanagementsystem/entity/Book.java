package com.project.librarymanagementsystem.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_authors",
        joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_publishers",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<Publisher>();

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {
        this.publishers = publishers;
    }

    public Book(Long id, String isbn, String name, String description, Set<Author> authors, Set<Category> categories, Set<Publisher> publishers) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.authors = authors;
        this.categories = categories;
        this.publishers = publishers;
    }

    //publisher

    public void removePublisher(Publisher publisher){
        this.publishers.remove(publisher);
        publisher.getBooks().remove(publisher);
    }

    public void addPublisher(Publisher publisher){
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }

    //author
    public void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(author);
    }

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    //category
    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getBooks().remove(category);
    }

    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }

}

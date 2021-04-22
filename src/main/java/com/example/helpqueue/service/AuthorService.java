package com.example.helpqueue.service;

import com.example.helpqueue.model.Author;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthorService {

    boolean createAuthor(Author author);

    Author getAuthor(Long id);

    Iterable<Author> getAuthors() throws JsonProcessingException;

    void update(Long id, Author author);

    void delete(Long id);

}

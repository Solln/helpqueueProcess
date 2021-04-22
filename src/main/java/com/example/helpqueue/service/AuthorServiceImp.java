package com.example.helpqueue.service;

import com.example.helpqueue.model.Author;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8081/authors/";

    @Override
    public boolean createAuthor(Author author) {
        //TODO logic for if save fails
        ResponseEntity<String> result = restTemplate.postForEntity(baseUrl, author, String.class);
        return 201 == result.getStatusCodeValue();
    }

    @Override
    public List<Author> getAuthors() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String result = restTemplate.getForObject(baseUrl, String.class);

        return objectMapper.readValue(result, new TypeReference<>(){});
    }


    @Override
    public Author getAuthor(Long id) {
        return restTemplate.getForObject(baseUrl + id, Author.class);
    }

    @Override
    public void update(Long id, Author author) {

    }

    @Override
    public void delete(Long id) {

    }

}

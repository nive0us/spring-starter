package com.example.tryDB.controller;


import com.example.tryDB.objEntity.Book;
import com.example.tryDB.repos.BookRepository;
import com.example.tryDB.repos.BookRepositoryRestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositoryRestResource bookRepositoryRestResource;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map hello() {
        Map map = new HashMap();
        map.put("say", "hello");
        return map;
    }
}

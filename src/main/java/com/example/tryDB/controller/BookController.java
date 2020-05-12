package com.example.tryDB.controller;


import com.example.tryDB.dto.BookDto;
import com.example.tryDB.objEntity.Book;
import com.example.tryDB.repos.BookRepository;
import com.example.tryDB.repos.BookRepositoryRestResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "bookRepository-取得書本", notes = "bookRepository-列出所有書本")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @ApiOperation(value = "hello world", notes = "hello")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map hello() {
        Map map = new HashMap();
        map.put("say", "hello");
        return map;
    }




    @ApiOperation(value = "新增書本", notes = "新增書本的內容")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "存檔成功")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/v1/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto create(
            @ApiParam(required = true, value = "書本內容") @RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setBookid(bookDto.getBookid());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book = bookRepository.save(book);
        bookDto.setBookid(book.getBookid());
        return bookDto;
    }

    @ApiOperation(value = "取得書本內容", notes = "取得書本內容")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "書本資訊")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/book/{bookid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto get(
            @ApiParam(required = true, name = "bookid", value = "書本ID") @PathVariable Integer bookid) {
        Book book = bookRepository.getOne(bookid);
        BookDto bookDto = new BookDto();
        bookDto.setBookid(book.getBookid());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        return bookDto;
    }




}

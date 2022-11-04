package com.tianhong.spring_boot_mybatis.controller;


import com.tianhong.spring_boot_mybatis.domain.Book;
import com.tianhong.spring_boot_mybatis.service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private Bookservice bookservice;

    @PostMapping
    public Result save(@RequestBody Book book){
        boolean flag = bookservice.save(book);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book){
        boolean flag = bookservice.update(book);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        boolean flag = bookservice.delete(id);
        return new Result(flag ? Code.DELETE_OK: Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Book book = bookservice.getbyId(id);
        Integer code = book != null ? Code.GET_OK:Code.GET_ERR;
        String msg = book != null ? "":"Request failed, please retry";
        return new Result(code, book, msg);
    }

    @GetMapping
    public Result getAll(){
        List<Book> bookList = bookservice.getAll();
        Integer code = bookList != null ? Code.GET_OK: Code.GET_ERR;
        String msg = bookList != null ? "":"Request failed, please retry";
        return new Result(code, bookList, msg);
    }


}

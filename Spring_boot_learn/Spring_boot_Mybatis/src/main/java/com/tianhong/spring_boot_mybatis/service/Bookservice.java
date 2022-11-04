package com.tianhong.spring_boot_mybatis.service;

import com.tianhong.spring_boot_mybatis.domain.Book;

import java.util.List;

public interface Bookservice {
    public Book getbyId(Integer id);
    public boolean save(Book book);
    public boolean delete(Integer id);
    public boolean update(Book book);
    public List<Book> getAll();
}

package com.tianhong.spring_boot_mybatis.service.impl;

import com.tianhong.spring_boot_mybatis.dao.BookDao;
import com.tianhong.spring_boot_mybatis.domain.Book;
import com.tianhong.spring_boot_mybatis.service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements Bookservice {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getbyId(Integer id) {
        return bookDao.getById(id);
    }

    @Override
    public boolean save(Book book) {
        return bookDao.save(book) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.delete(id) > 0;
    }

    @Override
    public boolean update(Book book) {
        return bookDao.update(book) > 0;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}

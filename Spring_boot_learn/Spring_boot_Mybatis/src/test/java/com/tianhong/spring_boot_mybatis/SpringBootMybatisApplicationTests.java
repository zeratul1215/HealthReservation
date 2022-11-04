package com.tianhong.spring_boot_mybatis;

import com.tianhong.spring_boot_mybatis.dao.BookDao;
import com.tianhong.spring_boot_mybatis.domain.Book;
import com.tianhong.spring_boot_mybatis.service.Bookservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisApplicationTests {

    @Autowired
    private Bookservice bookservice;

    @Test
    void testGetById() {
        Book book = bookservice.getbyId(1);
        System.out.println(book);
    }

    @Test
    void testGetAll(){
        List<Book> books = bookservice.getAll();
        System.out.println(books);
    }

    @Test
    void testSave(){
        Book b = new Book(null,"abc","cs","verygood");
        System.out.println(bookservice.save(b));

    }

}

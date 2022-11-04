package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.dao.BookDao;
import com.example.mybatisplus.domain.Book;
import com.example.mybatisplus.domain.query.BookQuery;
import kotlin.jvm.internal.Lambda;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownServiceException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    BookDao bookDao;

//    @Test
//    void testSave(){
//        Book book = new Book(null,"abc","abc","abc");
//        bookDao.insert(book);
//    }

    @Test
    void testDelete(){
        bookDao.deleteById(1);
    }


    @Test
    void testGetByWrapper(){
//        QueryWrapper qw = new QueryWrapper();
//        qw.lt("id",15);
//        List<Book> userList = bookDao.selectList(qw);

//        QueryWrapper<Book> qw = new QueryWrapper();
//        qw.lambda().lt(Book::getId,15);
//        List<Book> userList = bookDao.selectList(qw);

        BookQuery bq = new BookQuery();
        bq.setId(1);
        bq.setId2(10);



        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.lt(Book::getId, 10);
        lqw.gt(null != bq.getId2(), Book::getId, 30);
        List<Book> userList = bookDao.selectList(lqw);

        QueryWrapper<Book> qm = new QueryWrapper<Book>();
        qm.select("count(*) as nums, type");
        qm.groupBy("type");
        List<Map<String,Object>> maps = bookDao.selectMaps(qm);


        //等值查询
        lqw.eq(Book::getName,"abc").eq(Book::getType,"abc");
        Book b = bookDao.selectOne(lqw);
        System.out.println(b);


        //范围查询
        lqw.between(Book::getId,10,20);
        List<Book> bookList = bookDao.selectList(lqw);


        //模糊匹配
        lqw.likeRight(Book::getName,"a");
        //right 指的是右边加百分号
        List<Book> bookList1 = bookDao.selectList(lqw);
    }

    @Test
    void testeOptimistic(){
        //查询数据 获取version数据
        Book book = bookDao.selectById(1);
        book.setName("tom and jerry");
        bookDao.updateById(book);
        //修改时使用乐观锁检查数据 update tbl_book SET name = ? age = ? tel = ? version = ? where id = ? and version = ?
    }

    @Test
    void testGetAll() {
        List<Book> l = bookDao.selectList(null);
        System.out.println(l);
    }


}

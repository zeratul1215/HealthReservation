package com.tianhong.spring_boot_mybatis.dao;

import com.tianhong.spring_boot_mybatis.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDao {

    @Select("select * from tbl_book where id = #{id}")
    public Book getById(Integer id);

    @Insert("insert into tbl_book (type, name, description) values(#{type},#{name},#{description})")
    public int save(Book book);

    @Insert("update tbl_book set type = #{type}, name = #{name}, description = #{description} where id = #{id}")
    public int update(Book book);

    @Delete("delete from tbl_book where id = #{id}")
    public int delete(Integer id);

    @Select("select * from tbl_book")
    public List<Book> getAll();


}

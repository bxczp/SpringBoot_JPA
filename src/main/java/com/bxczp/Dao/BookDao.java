package com.bxczp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bxczp.entity.Book;

//注入泛型的类的主键是什么类型，序列就是什么类型（主键类型要为包装类）
public interface BookDao extends JpaRepository<Book, Integer>{

}

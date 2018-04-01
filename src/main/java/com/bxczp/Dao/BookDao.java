package com.bxczp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bxczp.entity.Book;

//注入泛型的类的主键是什么类型，序列就是什么类型（主键类型要为包装类）
public interface BookDao extends JpaRepository<Book, Integer>,JpaSpecificationExecutor<Book>{

//    @Query("select * from db_book where name like %?1%")  // 本地语句查询
    @Query("select b from Book b where b.name like %?1%")  // HQL语句查询
    public List<Book> findByName(String name);
    
    @Query(value="select * from t_book order by RAND() limit ?1", nativeQuery=true)
    public List<Book> randomList(Integer limit);
}

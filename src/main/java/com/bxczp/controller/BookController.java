package com.bxczp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxczp.Dao.BookDao;
import com.bxczp.entity.Book;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookDao bookDao;
    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> bookList = bookDao.findAll();
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("bookList");
        return modelAndView;
    }
    
    @RequestMapping("/list2")
    public ModelAndView list2(Book book) {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> bookList = bookDao.findAll(new Specification<Book>() {
            
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (book != null) {
                    if (book.getName() != null && !"".equals(book.getName())) {
                        // root获取匹配的字段 book获取匹配的值
                        predicate.getExpressions().add(cb.like(root.get("name"), "%"+book.getName()+"%"));
                    }
                    if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
                        predicate.getExpressions().add(cb.like(root.get("author"), "%"+book.getAuthor()+"%"));
                    }
                }
                return predicate;
            }
        });
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("bookList");
        return modelAndView;
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(Book book) {
        bookDao.save(book);
        return "forward:/book/list";
    }
    
    @GetMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable(name="id", required=true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bookDao.getOne(id));
        modelAndView.setViewName("update");
        return modelAndView;
    }
    
    @GetMapping("/preUpdate")
    public ModelAndView preUpdate2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        return modelAndView;
    }
    
    //指定Post方法
    @PostMapping("/update")
    public String update(Book book) {
        bookDao.save(book);
        return "redirect:/book/list";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name="id", required=true)Integer id) {
        bookDao.deleteById(id);
        return "redirect:/book/list";
    }
    
    @ResponseBody
    @RequestMapping("/findByName")
    public List<Book> findByName(String name) {
        List<Book> listBook = bookDao.findByName(name);
        return listBook;
    }
    
    @ResponseBody
    @RequestMapping("/random")
    public List<Book> random(Integer limit) {
        List<Book> listBook = bookDao.randomList(limit);
        return listBook;
    }
}

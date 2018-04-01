package com.bxczp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @PostMapping("/update")
    public String update(Book book) {
        bookDao.save(book);
        return "redirect:/book/list";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name="id", required=true)Integer id) {
        bookDao.deleteById(id);
        return "redirect:/book/list";
    }
}

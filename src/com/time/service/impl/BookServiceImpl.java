package com.time.service.impl;

import com.time.mapper.BookMapper;
import com.time.pojo.Book;
import com.time.service.BookService;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public Book findBookByIsbn(String isbn) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.findBookByIsbn(isbn);
        return book;
    }

    @Override
    public Book findBookByName(String bookName) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.findBookByName(bookName);
        return book;
    }

    @Override
    public Book findBookById(Integer id) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.findBookById(id);
        return book;
    }

    @Override
    public List<Book> findNewBook() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> list = mapper.findBookByTime();
        return list;
    }

    @Override
    public List<Book> findHotBook() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> list = mapper.findBookByWeight();
        return list;
    }
}

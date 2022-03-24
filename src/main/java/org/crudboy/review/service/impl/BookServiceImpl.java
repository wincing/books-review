package org.crudboy.review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.crudboy.review.Entity.Book;
import org.crudboy.review.mapper.BookMapper;
import org.crudboy.review.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    public IPage<Book> paging(Integer categoryId, String order, Integer page, Integer rows) {
        Page<Book> bookPage = new Page<Book>(page, rows);
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<Book>();
        if (categoryId != null && categoryId != -1) {
            bookQueryWrapper.eq("category_id", categoryId);
        }
        if (order != null) {
            if (order.equals("score")) {
                bookQueryWrapper.orderByDesc("evaluation_score");
            } else if (order.equals("quantity")) {
                bookQueryWrapper.orderByDesc("evaluation_quantity");
            }
        }
        return bookMapper.selectPage(bookPage, bookQueryWrapper);
    }

    public Book selectById(Long bookId) {
        return bookMapper.selectById(bookId);
    }

    @Transactional
    public void updateBookState() {
        bookMapper.updateBookState();
    }

    @Transactional
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Transactional
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
    }
}

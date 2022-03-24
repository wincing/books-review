package org.crudboy.review.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.crudboy.review.Entity.Book;


public interface BookService {
    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    IPage<Book> paging(Integer categoryId, String order, Integer page, Integer rows);

    /**
     * 按id查询书籍
     * @param bookId 图书编号
     * @return
     */
    Book selectById(Long bookId);

    /**
     * 更新图书的评分和人数
     */
    void updateBookState();

    /**
     * 新增图书并返回该图书对象
     * @param book
     * @return
     */
    Book createBook(Book book);

    /**
     * 更新图书信息并返回该图书对象
     * @param book
     * @return
     */
    Book updateBook(Book book);


    /**
     * 根据图书id删除图书
     * @param bookId
     */
    void deleteBook(Long bookId);
}

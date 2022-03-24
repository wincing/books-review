package org.crudboy.review.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import junit.framework.TestCase;
import org.crudboy.review.Entity.Book;
import org.crudboy.review.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BookServiceImplTest extends TestCase {

    @Resource
    private BookService bookService;

    @Test
    public void testPaging() {
        IPage<Book> page = bookService.paging(-1, "score", 1, 20);
        List<Book> records = page.getRecords();
        for (Book record : records) {
            System.out.println(record.getEvaluationScore());
        }
    }
}
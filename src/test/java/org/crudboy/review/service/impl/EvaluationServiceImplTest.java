package org.crudboy.review.service.impl;

import junit.framework.TestCase;
import org.crudboy.review.Entity.Book;
import org.crudboy.review.service.BookService;
import org.crudboy.review.service.EvaluationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EvaluationServiceImplTest extends TestCase {

    @Resource
    EvaluationService evaluationService;

    @Test
    public void testSelectByBookId() {
        evaluationService.selectByBookId(1L);
    }
}
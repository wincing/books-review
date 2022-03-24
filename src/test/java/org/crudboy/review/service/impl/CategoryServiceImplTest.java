package org.crudboy.review.service.impl;

import junit.framework.TestCase;
import org.crudboy.review.Entity.Category;
import org.crudboy.review.mapper.CategoryMapper;
import org.crudboy.review.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CategoryServiceImplTest extends TestCase {

    @Resource
    private CategoryService categoryService;
    @Test
    public void testSelectAll() {
        List<Category> categories = categoryService.selectAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}
package org.crudboy.review.service;

import org.crudboy.review.Entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有图书类别
     * @return List<Category>
     */
    public List<Category> selectAll();
}

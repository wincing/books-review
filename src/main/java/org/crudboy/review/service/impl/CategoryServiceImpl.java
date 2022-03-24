package org.crudboy.review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.crudboy.review.Entity.Category;
import org.crudboy.review.mapper.CategoryMapper;
import org.crudboy.review.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> selectAll() {
        return categoryMapper.selectList(new QueryWrapper<Category>());
    }
}

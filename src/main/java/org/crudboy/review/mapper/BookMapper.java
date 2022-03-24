package org.crudboy.review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.crudboy.review.Entity.Book;

public interface BookMapper extends BaseMapper<Book> {
    void updateBookState();
}

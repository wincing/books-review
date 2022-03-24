package org.crudboy.review.task;

import org.crudboy.review.mapper.BookMapper;
import org.crudboy.review.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时统计每本书的评分和人数
 */
@Component
public class ComputeTask {

    @Resource
    private BookService bookService;

    @Scheduled(cron = "0 * * * * ?")
    public void updateEvaluation() {
        bookService.updateBookState();
        System.out.println("-------update books state------");
    }
}

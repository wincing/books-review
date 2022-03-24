package org.crudboy.review.service;

import org.crudboy.review.Entity.Evaluation;

import java.util.List;

public interface EvaluationService {

    /**
     * 根据图书编号查询有效的评论
     * @param bookId 图书编号
     * @return
     */
    List<Evaluation> selectByBookId(Long bookId);

    /**
     * 新增评论并返回评论对象
     * @param bookId
     * @param userId
     * @param score
     * @param content
     * @return
     */
    Evaluation createEvaluation(Long bookId, Long userId, Integer score, String content);

    /**
     * 对图书下的评论点赞
     * @param evaluationId
     * @return
     */
    Evaluation enjoy(Long evaluationId);
}

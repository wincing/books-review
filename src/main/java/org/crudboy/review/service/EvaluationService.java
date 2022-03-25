package org.crudboy.review.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 按时间顺序显示获取评论
     * @param current 当前页码
     * @param rows 每页行数
     * @return
     */
    IPage<Evaluation> paging(Integer current, Integer rows);


    /**
     * 禁用评论
     * @param evaluationId
     * @param reason 禁用原因
     * @return Evaluation 被禁用的评论对象
     */
    Evaluation disable(Long evaluationId, String reason);
}

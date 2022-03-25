package org.crudboy.review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.crudboy.review.Entity.Book;
import org.crudboy.review.Entity.Evaluation;
import org.crudboy.review.Entity.User;
import org.crudboy.review.mapper.BookMapper;
import org.crudboy.review.mapper.EvaluationMapper;
import org.crudboy.review.mapper.UserMapper;
import org.crudboy.review.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("evaluationService")

public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    private EvaluationMapper evaluationMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Evaluation> selectByBookId(Long bookId) {
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<Evaluation>();
        queryWrapper.eq("book_id", bookId);
        queryWrapper.eq("state", "enable");
        queryWrapper.orderByDesc("create_time");

        Book book = bookMapper.selectById(bookId);
        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);
        for (Evaluation evaluation : evaluationList) {
            evaluation.setBook(book);
            User user = userMapper.selectById(evaluation.getUserId());
            evaluation.setUser(user);
        }
        return evaluationList;
    }

    public Evaluation createEvaluation(Long bookId, Long userId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setUserId(userId);
        evaluation.setBookId(bookId);
        evaluation.setContent(content);
        evaluation.setScore(score);
        evaluation.setState("enable");
        evaluation.setEnjoy(0);
        evaluation.setCreateTime(new Date());
        evaluationMapper.insert(evaluation);
        return evaluation;
    }


    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }

    public IPage<Evaluation> paging(Integer current, Integer rows) {
        if (current == null) {
            current = 1;
        }
        if (rows == null) {
            rows = 10;
        }

        Page<Evaluation> page = new Page<Evaluation>(current, rows);
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<Evaluation>();
        queryWrapper.orderByDesc("create_time");

        return evaluationMapper.selectPage(page, queryWrapper);
    }

    public Evaluation disable(Long evaluationId, String reason) {
        Evaluation evaluation=  evaluationMapper.selectById(evaluationId);
        evaluation.setDisableReason(reason);
        evaluation.setState("disabled");
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }
}

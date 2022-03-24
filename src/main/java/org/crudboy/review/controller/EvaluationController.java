package org.crudboy.review.controller;

import org.crudboy.review.Entity.Evaluation;
import org.crudboy.review.Entity.UserReadState;
import org.crudboy.review.service.EvaluationService;
import org.crudboy.review.service.UserService;
import org.crudboy.review.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EvaluationController {

    @Resource
    private UserService userService;

    @Resource
    private EvaluationService evaluationService;

    @PostMapping("/update_read_state")
    @ResponseBody
    public Map updateReadState(Long bookId, Long userId, Integer readState) {
        HashMap result = new HashMap();
        try {
            UserReadState userReadState = userService.updateUserReadState(bookId, userId, readState);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @PostMapping("/evaluation")
    @ResponseBody
    public Map createEvaluation(Long bookId, Long userId, Integer score, String content) {
        HashMap result = new HashMap();

        try {
            evaluationService.createEvaluation(bookId, userId, score, content);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @PostMapping("/enjoy")
    @ResponseBody
    public Map enjoy(Long evaluationId) {
        HashMap result = new HashMap();
        try {
            Evaluation evaluation = evaluationService.enjoy(evaluationId);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation", evaluation);
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }
}

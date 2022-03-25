package org.crudboy.review.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.crudboy.review.Entity.Evaluation;
import org.crudboy.review.service.BookService;
import org.crudboy.review.service.EvaluationService;
import org.crudboy.review.service.UserService;
import org.crudboy.review.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management/evaluation")
public class EvaluationManagementController {

    @Resource
    private EvaluationService evaluationService;

    @Resource
    private BookService bookService;

    @Resource
    private UserService userService;

    @GetMapping("/editor")
    public ModelAndView showEditor() {
        return new ModelAndView("/backend/evaluation_editor");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map selectEvaluations(Integer page, Integer limit) {
        Map result = new HashMap();

        try {
            IPage<Evaluation> evaluationPage = evaluationService.paging(page, limit);
            List<Evaluation> evaluationList = evaluationPage.getRecords();
            for (Evaluation evaluation : evaluationList) {
                Long bookId = evaluation.getBookId();
                evaluation.setBook(bookService.selectById(bookId));

                Long userId = evaluation.getUserId();
                evaluation.setUser(userService.selectById(userId));
            }
            result.put("code", 0);
            result.put("msg", "success");
            result.put("count", evaluationPage.getTotal());
            result.put("data", evaluationList);
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }

        return result;
    }

    @PostMapping("/disable")
    @ResponseBody
    public Map disableEvaluation(Long evaluationId, String reason) {
        Map result = new HashMap();

        try {
            result.put("code", 0);
            result.put("msg", "success");
            evaluationService.disable(evaluationId, reason);
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }

        return result;
    }
}

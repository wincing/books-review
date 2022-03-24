package org.crudboy.review.controller.backend;

import org.crudboy.review.service.BookService;
import org.crudboy.review.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/management/evaluation")
public class EvaluationManagementController {

    @Resource
    private EvaluationService evaluationService;

    @RequestMapping("/editor")
    public ModelAndView showEditor() {
        return new ModelAndView("/backend/evaluation_editor");
    }
}

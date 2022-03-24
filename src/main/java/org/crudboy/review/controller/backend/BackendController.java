package org.crudboy.review.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backend")
public class BackendController {

    @GetMapping("")
    public ModelAndView showIndex() {
        return new ModelAndView("/backend/index");
    }
}

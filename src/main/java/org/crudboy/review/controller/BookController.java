package org.crudboy.review.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.crudboy.review.Entity.*;
import org.crudboy.review.service.BookService;
import org.crudboy.review.service.CategoryService;
import org.crudboy.review.service.EvaluationService;
import org.crudboy.review.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    @Resource
    private EvaluationService evaluationService;

    @Resource
    private UserService userService;

    /**
     * 显示首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Category> categories = categoryService.selectAll();
        modelAndView.addObject("categoryList", categories);
        return modelAndView;
    }

    /**
     *
     * @param categoryId 查询类别
     * @param order 查询顺序
     * @param p 页数
     * @return
     */
    @GetMapping("/book")
    @ResponseBody
    public IPage<Book> selectBooks(Integer categoryId, String order, Integer p) {
        if (p == null) {
            p = 1;
        }
        IPage<Book> pageObject = bookService.paging(categoryId, order, p, 10);
        return pageObject;
    }

    @GetMapping("/book/{id}")
    public ModelAndView selectById(@PathVariable("id") Long bookId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Book book = bookService.selectById(bookId);
        modelAndView.addObject("book", book);

        List<Evaluation> evaluationList = evaluationService.selectByBookId(bookId);
        modelAndView.addObject("evaluationList", evaluationList);

        User user = (User)session.getAttribute("loginUser");
        if (user != null) {
            UserReadState userReadState = userService.selectUserReadState(bookId, user.getUserId());
            modelAndView.addObject("userReadState", userReadState);
        }

        return modelAndView;
    }
}

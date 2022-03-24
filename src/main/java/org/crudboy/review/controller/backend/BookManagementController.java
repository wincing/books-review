package org.crudboy.review.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.crudboy.review.Entity.Book;
import org.crudboy.review.mapper.BookMapper;
import org.crudboy.review.service.BookService;
import org.crudboy.review.service.exception.BussinessException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequestMapping("/management/book")
public class BookManagementController {

    @Resource
    private BookService bookService;

    @RequestMapping("/editor")
    public ModelAndView showEditor() {
        return new ModelAndView("/backend/book_editor");
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        // 得到上传目录
        String uploadPath = request.getServletContext().getRealPath("/") + "/upload/";
        // 文件名
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // 扩展名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 保存文件到upload目录
        file.transferTo(new File(uploadPath + fileName + suffix));

        Map result = new HashMap();
        result.put("errno", 0);
        result.put("data", new String[]{"/upload/" + fileName + suffix});
        return result;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map createBook(Book book) {
        Map result = new HashMap();
        try {
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            Document doc = Jsoup.parse(book.getDescription());
            Element img = doc.select("img").first(); // 获取第一张图的元素对象
            book.setCover(img.attr("src"));

            bookService.createBook(book);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @GetMapping("/list")
    @ResponseBody
    public Map selectBooks(Integer page, Integer limit) {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }

        IPage<Book> pageObject = bookService.paging(null, null, page, limit);
        Map result = new HashMap();

        result.put("code", 0);
        result.put("msg", "success");
        result.put("data", pageObject.getRecords());
        result.put("count", pageObject.getTotal());

        return result;
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    Map selectBookById (@PathVariable("id") Long bookId) {
        Book book = bookService.selectById(bookId);
        Map result = new HashMap();
        result.put("code", 0);
        result.put("msg", "success");
        result.put("data", book);

        return result;
    }

    @PostMapping("/update")
    @ResponseBody
    Map updateBook(Book book) {
        Map result = new HashMap();
        try {
            Book oldBook = bookService.selectById(book.getBookId());
            book.setEvaluationScore(oldBook.getEvaluationScore());
            book.setEvaluationQuantity(oldBook.getEvaluationQuantity());
            Document doc = Jsoup.parse(book.getDescription());
            String cover = doc.select("img").first().attr("src");
            book.setCover(cover);
            bookService.updateBook(book);

            result.put("code", 0);
            result.put("msg", "success");

        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    Map deleteBook(@PathVariable("id") Long bookId) {
        Map result = new HashMap();

        try {
            bookService.deleteBook(bookId);
            result.put("code", 0);
            result.put("msg", "success");
        } catch(BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }
}

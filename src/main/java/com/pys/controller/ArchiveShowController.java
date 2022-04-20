package com.pys.controller;

import com.pys.queryvo.BlogQuery;
import com.pys.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//时间轴页面显示控制器

//时间轴页面显示只要显示博客标题和时间，
// 因此可以直接共用BlogDao中的持久层接口getAllBlog
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        return "archives";
    }
}

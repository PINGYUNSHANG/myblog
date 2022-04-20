package com.pys.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pys.entity.Blog;
import com.pys.entity.Type;
import com.pys.entity.User;
import com.pys.queryvo.BlogQuery;
import com.pys.queryvo.SearchBlog;
import com.pys.queryvo.ShowBlog;
import com.pys.service.BlogService;
import com.pys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;


//博客新增后，会跳转到博客列表，需要传递博客对象和分类的信息，
// 因此除了博客的model还需要Type相关model
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.queryAllType());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    //博客新增
    @PostMapping("blogs")
    public String post(Blog blog, RedirectAttributes attributes,
                       HttpServletRequest request) {
        //传递用户
        blog.setUser((User) request.getServletContext().getAttribute("user"));
        //设置blog中的type
        blog.setType(typeService.queryTypeById(blog.getType().getId()));
        //设置blog中的typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b = blogService.saveBlog(blog);
        if (b == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //博客列表
    @RequestMapping("/blogs")
    public String blogs(Model model,
                        @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //根据排序字段
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("types", typeService.queryAllType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //修改博客,跳转到编辑修改文章
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.queryAllType();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }
    //编辑要修改的文章
    @PostMapping("/blogs/{id}")
    public String editPost(@Valid ShowBlog showBlog, RedirectAttributes attributes){
        int b = blogService.updateBlog(showBlog);
        if(b == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }
    //搜索博客管理列表

    /**
     *  return "admin/blogs :: blogList" :
     *  这是thymeleaf的一个模板片断，相当于返回admin/blogs模板中的某个片段
     */
    @PostMapping("/blogs/search")
    public  String search(SearchBlog searchBlog,Model model,
                          @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }


}

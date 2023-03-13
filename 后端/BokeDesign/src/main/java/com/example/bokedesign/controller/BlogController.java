package com.example.bokedesign.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bokedesign.common.Result;
import com.example.bokedesign.entity.Blog;
import com.example.bokedesign.service.BlogService;
import com.example.bokedesign.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author boke
 * @since 2022-10-14
 */
@RestController

public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    //分页
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    //详情
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);

        //要是查到的内容为空,那么就assert抛出异常
        Assert.notNull(blog, "该博客已被删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication//需要认证才能访问
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

//        Assert.isTrue(false, "公开版不能任意编辑！");

        Blog temp = null;
        //如果blog有id就是为编辑,不然就是添加新的
        if(blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            System.out.println(ShiroUtil.getProfile().getId());

            //如果登录的id和编辑博客的id一致才可以进行编辑
            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        } else {
 //否则只能增加新的
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.succ(null);
    }

}

package com.example.bokedesign.service.impl;

import com.example.bokedesign.entity.Blog;
import com.example.bokedesign.mapper.BlogMapper;
import com.example.bokedesign.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author boke
 * @since 2022-10-14
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}

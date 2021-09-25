package com.nilu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nilu.common.Result;
import com.nilu.entity.Book;
import com.nilu.mapper.BookMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: 倪路
 * Time: 2021/9/25-9:30
 * StuNo: 1910400731
 * Class: 19104221
 * Description:
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    BookMapper BookMapper;

    @PostMapping
    public Result<?> addBook(@RequestBody Book book){
        BookMapper.insert(book);
        return Result.success();
    }


    @GetMapping
    public Result<?> findBook(@RequestParam(defaultValue = "1") Integer number,@RequestParam(defaultValue = "10") Integer pagesize ,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if(StrUtil.isNotBlank((search))){
            wrapper.like(Book::getBookname, search);
        }
        Page<Book> BookPage = BookMapper.selectPage(new Page<>(number, pagesize), wrapper);
        return Result.success(BookPage);
    }

    @PutMapping
    public Result<?> updateBook(@RequestBody Book Book){
        BookMapper.updateById(Book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteBook(@PathVariable Long id){
        BookMapper.deleteById(id);
        return Result.success();
    }
}

package com.nilu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nilu.common.Result;
import com.nilu.entity.User;
import com.nilu.mapper.UserMapper;
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
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;

    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> Loginin(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }
    @PostMapping("/register")
    public Result<?> Register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res!=null){
            return Result.error("-1","用户名重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @GetMapping
    public Result<?> findUser(@RequestParam(defaultValue = "1") Integer number,@RequestParam(defaultValue = "10") Integer pagesize ,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank((search))){
            wrapper.like(User::getNickname, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(number, pagesize), wrapper);
        return Result.success(userPage);
    }

    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteUser(@PathVariable Long id){
        userMapper.deleteById(id);
        return Result.success();
    }
}

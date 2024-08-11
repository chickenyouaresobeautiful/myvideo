package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.UsersEntity;
import com.example.myvedio.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/get/{id}")
    public R<UsersEntity> get(@PathVariable int id) {
        return R.success(usersService.getById(id));
    }

    @GetMapping("/list")
    public R<List<UsersEntity>> list() {
        return R.success(usersService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody UsersEntity usersEntity) {
        return R.success(usersService.save(usersEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody UsersEntity usersEntity) {
        return R.success(usersService.updateById(usersEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(usersService.removeByIds(ids));
    }
}
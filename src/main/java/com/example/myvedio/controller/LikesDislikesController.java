package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.LikesDislikesEntity;
import com.example.myvedio.service.LikesDislikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likesdislikes")
public class LikesDislikesController {
    @Autowired
    private LikesDislikesService likesDislikesService;

    @GetMapping("/get/{id}")
    public R<LikesDislikesEntity> get(@PathVariable int id) {
        return R.success(likesDislikesService.getById(id));
    }

    @GetMapping("/list")
    public R<List<LikesDislikesEntity>> list() {
        return R.success(likesDislikesService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody LikesDislikesEntity likesDislikesEntity) {
        return R.success(likesDislikesService.save(likesDislikesEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody LikesDislikesEntity likesDislikesEntity) {
        return R.success(likesDislikesService.updateById(likesDislikesEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(likesDislikesService.removeByIds(ids));
    }
}
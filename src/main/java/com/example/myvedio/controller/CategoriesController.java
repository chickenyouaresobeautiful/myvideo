package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.CategoriesEntity;
import com.example.myvedio.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/get/{id}")
    public R<CategoriesEntity> get(@PathVariable int id) {
        return R.success(categoriesService.getById(id));
    }

    @GetMapping("/list")
    public R<List<CategoriesEntity>> list() {
        return R.success(categoriesService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody CategoriesEntity categoriesEntity) {
        return R.success(categoriesService.save(categoriesEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody CategoriesEntity categoriesEntity) {
        return R.success(categoriesService.updateById(categoriesEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(categoriesService.removeByIds(ids));
    }
}
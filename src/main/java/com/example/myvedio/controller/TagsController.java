package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.TagsEntity;
import com.example.myvedio.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
    @Autowired
    private TagsService tagsService;

    @GetMapping("/get/{id}")
    public R<TagsEntity> get(@PathVariable int id) {
        return R.success(tagsService.getById(id));
    }

    @GetMapping("/list")
    public R<List<TagsEntity>> list() {
        return R.success(tagsService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody TagsEntity tagsEntity) {
        return R.success(tagsService.save(tagsEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody TagsEntity tagsEntity) {
        return R.success(tagsService.updateById(tagsEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(tagsService.removeByIds(ids));
    }
}

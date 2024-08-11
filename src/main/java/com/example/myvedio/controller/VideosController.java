package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.VideosEntity;
import com.example.myvedio.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideosController {
    @Autowired
    private VideosService videosService;

    @GetMapping("/get/{id}")
    public R<VideosEntity> get(@PathVariable int id) {
        return R.success(videosService.getById(id));
    }

    @GetMapping("/list")
    public R<List<VideosEntity>> list() {
        return R.success(videosService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody VideosEntity videosEntity) {
        return R.success(videosService.save(videosEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody VideosEntity videosEntity) {
        return R.success(videosService.updateById(videosEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(videosService.removeByIds(ids));
    }
}
package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.PlayHistoryEntity;
import com.example.myvedio.service.PlayHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playhistory")
public class PlayHistoryController {
    @Autowired
    private PlayHistoryService playHistoryService;

    @GetMapping("/get/{id}")
    public R<PlayHistoryEntity> get(@PathVariable int id) {
        return R.success(playHistoryService.getById(id));
    }

    @GetMapping("/list")
    public R<List<PlayHistoryEntity>> list() {
        return R.success(playHistoryService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody PlayHistoryEntity playHistoryEntity) {
        return R.success(playHistoryService.save(playHistoryEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody PlayHistoryEntity playHistoryEntity) {
        return R.success(playHistoryService.updateById(playHistoryEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(playHistoryService.removeByIds(ids));
    }
}
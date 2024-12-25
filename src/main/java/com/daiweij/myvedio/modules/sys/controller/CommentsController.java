package com.daiweij.myvedio.modules.sys.controller;

import com.daiweij.myvedio.common.utils.R;
import com.daiweij.myvedio.modules.sys.entity.CommentsEntity;
import com.daiweij.myvedio.modules.sys.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/get/{id}")
    public R<CommentsEntity> get(@PathVariable int id) {
        return R.success(commentsService.getById(id));
    }

    @GetMapping("/list")
    public R<List<CommentsEntity>> list() {
        return R.success(commentsService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody CommentsEntity commentsEntity) {
        return R.success(commentsService.save(commentsEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody CommentsEntity commentsEntity) {
        return R.success(commentsService.updateById(commentsEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(commentsService.removeByIds(ids));
    }
}
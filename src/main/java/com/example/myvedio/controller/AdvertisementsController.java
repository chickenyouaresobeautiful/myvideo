package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.AdvertisementsEntity;
import com.example.myvedio.service.AdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementsController {
    @Autowired
    private AdvertisementsService advertisementsService;

    @GetMapping("/get/{id}")
    public R<AdvertisementsEntity> get(@PathVariable("id") int id) {
        return R.success(advertisementsService.getById(id));
    }

    @GetMapping("/list")
    public R<List<AdvertisementsEntity>> list() {
        return R.success(advertisementsService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody AdvertisementsEntity advertisementsEntity) {
        return R.success(advertisementsService.save(advertisementsEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody AdvertisementsEntity advertisementsEntity) {
        return R.success(advertisementsService.updateById(advertisementsEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(advertisementsService.removeByIds(ids));
    }
}
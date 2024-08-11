package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.FavoritesEntity;
import com.example.myvedio.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/get/{id}")
    public R<FavoritesEntity> get(@PathVariable int id) {
        return R.success(favoritesService.getById(id));
    }

    @GetMapping("/list")
    public R<List<FavoritesEntity>> list() {
        return R.success(favoritesService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody FavoritesEntity favoritesEntity) {
        return R.success(favoritesService.save(favoritesEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody FavoritesEntity favoritesEntity) {
        return R.success(favoritesService.updateById(favoritesEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(favoritesService.removeByIds(ids));
    }
}
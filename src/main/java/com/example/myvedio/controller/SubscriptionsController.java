package com.example.myvedio.controller;

import com.example.myvedio.common.R;
import com.example.myvedio.entity.SubscriptionsEntity;
import com.example.myvedio.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {
    @Autowired
    private SubscriptionsService subscriptionsService;

    @GetMapping("/get/{id}")
    public R<SubscriptionsEntity> getSubscription(@PathVariable int id) {
        return R.success(subscriptionsService.getById(id));
    }

    @GetMapping("/list")
    public R<List<SubscriptionsEntity>> list() {
        return R.success(subscriptionsService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody SubscriptionsEntity subscriptionsEntity) {
        return R.success(subscriptionsService.save(subscriptionsEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody SubscriptionsEntity subscriptionsEntity) {
        return R.success(subscriptionsService.updateById(subscriptionsEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(subscriptionsService.removeByIds(ids));
    }
}
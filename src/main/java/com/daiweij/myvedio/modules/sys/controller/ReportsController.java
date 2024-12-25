package com.daiweij.myvedio.modules.sys.controller;

import com.daiweij.myvedio.common.utils.R;
import com.daiweij.myvedio.modules.sys.entity.ReportsEntity;
import com.daiweij.myvedio.modules.sys.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportsService;

    @GetMapping("/get/{id}")
    public R<ReportsEntity> get(@PathVariable int id) {
        return R.success(reportsService.getById(id));
    }

    @GetMapping("/list")
    public R<List<ReportsEntity>> list() {
        return R.success(reportsService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody ReportsEntity reportsEntity) {
        return R.success(reportsService.save(reportsEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody ReportsEntity reportsEntity) {
        return R.success(reportsService.updateById(reportsEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(reportsService.removeByIds(ids));
    }
}
package com.daiweij.myvedio.modules.sys.controller;

import com.daiweij.myvedio.common.utils.LogUtil;
import com.daiweij.myvedio.common.utils.R;
import com.daiweij.myvedio.modules.sys.entity.UsersEntity;
import com.daiweij.myvedio.modules.sys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/get/{id}")
    public R<UsersEntity> get(@PathVariable int id) {
        return R.success(usersService.getById(id));
    }

    @GetMapping("/list")
    public R<List<UsersEntity>> list() {
        return R.success(usersService.list());
    }

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody UsersEntity usersEntity) {
        return R.success(usersService.save(usersEntity));
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody UsersEntity usersEntity) {
        return R.success(usersService.updateById(usersEntity));
    }

    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestBody List<Integer> ids) {
        return R.success(usersService.removeByIds(ids));
    }

    @PostMapping("/uploadAvatar")
    public R<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId) {
        if (file == null) {
            return R.error(4000, "上传文件为空");
        }

        // 文件大小校验
        long maxSize = 1024 * 1024 * 5;
        if (file.getSize() > maxSize) {
            return R.error(4001, "文件大小超出限制");
        }

        String originalFilename = file.getOriginalFilename();
        if (!StringUtils.hasText(originalFilename)) {
            return R.error(4002, "文件名无效");
        }
        // 文件扩展名校验
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "jpeg", "gif");
        if (!allowedExtensions.contains(fileExtension)) {
            return R.error(4003, "不支持的文件类型，只允许上传图片文件！");
        }

        try {
            // 生成唯一文件名，防止泄露
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            // 指定文件保存的目录(可以配置到yml中)
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            // 构造文件的完整路径
            Path filePath = uploadDir.resolve(fileName);
            // 保存文件到服务器
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            //todo 可选：将 fileName 保存到数据库中，与当前用户关联
            usersService.updateUserAvatar(userId, fileName);
            return R.success("头像上传成功，文件名：" + fileName);
        } catch (IOException e) {
            LogUtil.error(this.getClass(), "头像上传失败", e);
            return R.error(4005, "头像上传失败");
        }
    }
}
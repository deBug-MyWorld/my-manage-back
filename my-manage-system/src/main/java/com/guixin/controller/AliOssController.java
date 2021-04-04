package com.guixin.controller;

import com.guixin.exception.AjaxResponse;
import com.guixin.service.AliOssService;
import com.guixin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// 另外写成一个controller 方便以后对oss进行操作 如后续图片管理
@RestController
@RequestMapping("/OSS")
@Api(tags = "阿里OSS")
public class AliOssController {
    @Autowired
    private AliOssService aliOssService;
    @Autowired
    private UserService userService;

    @ApiOperation("头像上传")
    @PostMapping("/uploadAvatar")
    public AjaxResponse uploadAvatar(@RequestParam("avatar") MultipartFile file){
        String avatar = aliOssService.upload(file);
        userService.updateAvatar(avatar);
        return AjaxResponse.success();
    }

    @ApiOperation(value = "删除头像")
    @PostMapping("/deleteImgFile")
    public AjaxResponse deleteImgFile(String file){
            aliOssService.deleteFile(file);
            return AjaxResponse.success();
    }
}

package com.guixin.service;

import org.springframework.web.multipart.MultipartFile;

public interface AliOssService {
    /**
     * 上传文件
     * @param file 文件对象
     * @return 返回地址
     */
    String upload(MultipartFile file);


    /**
     * 删除文件
     * @param fileName 文件名
     */
    void deleteFile(String fileName);

}

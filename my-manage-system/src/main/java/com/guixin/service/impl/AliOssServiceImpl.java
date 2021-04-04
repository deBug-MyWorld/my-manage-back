package com.guixin.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.guixin.service.AliOssService;
import com.guixin.util.AliOssUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class AliOssServiceImpl implements AliOssService, InitializingBean {

    @Autowired
    private AliOssUtil aliOssUtil;

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 初始化bean之后需要进行的操作
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        endpoint = aliOssUtil.getEndpoint();
        accessKeyId = aliOssUtil.getAccessKeyId();
        accessKeySecret = aliOssUtil.getAccessKeySecret();
        bucketName = aliOssUtil.getBucketName();
    }
    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 返回地址
     */
    @Override
    public String upload(MultipartFile file) {
        // 上传的地址
        String uploadUrl = null;
        try {
            // 创建OSSClient实例
            OSS ossClient =new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
            // 判断bucketName是否存在
            if (!ossClient.doesBucketExist(bucketName)){
                // 创建bucket
                ossClient.createBucket(bucketName);
                // 设置bucket属性
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileType = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replaceAll("-","") + fileType;
            String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String fileName = filePath + "/" + newFileName;

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(getcontentType(fileType));

            ossClient.putObject(bucketName,fileName,inputStream,meta);
            ossClient.shutdown();

            uploadUrl = "http://"+bucketName+"."+endpoint+"/"+fileName;
        } catch (Exception e){
            e.printStackTrace();
        }
        return uploadUrl;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    @Override
    public void deleteFile(String fileName) {
        // <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        System.out.println(fileName);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。
        ossClient.deleteObject(bucketName, fileName.split(".com/")[1]);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpg";
    }
}

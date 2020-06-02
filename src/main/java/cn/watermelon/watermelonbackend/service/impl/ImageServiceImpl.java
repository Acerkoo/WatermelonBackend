package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.mapper.ImageMapper;
import cn.watermelon.watermelonbackend.service.ImageService;
import cn.watermelon.watermelonbackend.utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("/home/admin/image/")
//    @Value("/Users/szki/Desktop/image/")
    private String fileDir;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String uploadImage(MultipartFile file, int userId, int imageType) {
        String fileName = file.getOriginalFilename();
        if (Upload.uploadFileInPath(file, fileName, fileDir) == true) {
            String param = "user_" + userId + "_type_" + imageType;
            redisTemplate.opsForValue().set(param, fileName, 1, TimeUnit.HOURS);
            imageMapper.insertImage(userId, imageType, fileName, new Date());
            return fileName;
        } else {
            return null;
        }
    }

    @Override
    public String getImageUrl(int userId, int imageType) {
        String param = "user_" + userId + "_type_" + imageType;
        if (redisTemplate.opsForValue().get(param) != null) {
            return (String) redisTemplate.opsForValue().get(param);
        } else {
            String fileName = imageMapper.getUserImage(userId, imageType);
            redisTemplate.opsForValue().set(param, fileName, 1, TimeUnit.HOURS);
            return fileName;
        }
    }
}

package cn.watermelon.watermelonbackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile file, int userId, int imageType);

    String getImageUrl(int userId, int imageType);

}

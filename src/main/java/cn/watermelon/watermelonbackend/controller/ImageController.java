package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    String uploadImage(MultipartFile file, int userId, Integer imageType) {
        if (imageType == null) imageType = 0;
        return imageService.uploadImage(file, userId, imageType);
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    String getImageURL(int userId, Integer imageType) {
        if (imageType == null) imageType = 0;
        return imageService.getImageUrl(userId, imageType);
    }

}

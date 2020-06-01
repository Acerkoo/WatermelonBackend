package cn.watermelon.watermelonbackend.controller;


import cn.watermelon.watermelonbackend.utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("/home/admin/user/")
//    @Value("/Users/szki/Desktop/test/")
    private String filePathDir;

    @RequestMapping(value = "/uploadFile/{filename}", method = RequestMethod.POST)
    public boolean uploadInputAndOutput(@PathVariable("filename") String fileName,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("type") String type,
                                        @RequestParam("id") int problemId) {
        if (file != null) {
            String filePath = "/home/admin/problem/" + Integer.toString(problemId) + "/" + type;
            if (Upload.getInstance().uploadFileInPath(file, fileName, filePath)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public boolean uploadUserImg(@RequestParam("file") MultipartFile file,
                                 @RequestParam("userId") int userId) {
        if (file != null) {
            String filePath = filePathDir + Integer.toString(userId) + "/";
            String fileName = "avator.jpg";
            if (Upload.getInstance().uploadFileInPath(file, fileName, filePath)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/background", method = RequestMethod.POST)
    public boolean uploadUserBackGroud(@RequestParam("file") MultipartFile file,
                                       @RequestParam("id") int userId) {
        if (file != null) {
            String filePath = filePathDir + Integer.toString(userId) + "/";
            String fileName = "background.jpg";
            if (Upload.getInstance().uploadFileInPath(file, fileName, filePath)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPic(int userId) throws IOException {
        String url = filePathDir + Integer.toString(userId) + "/avator.jpg";
        FileInputStream picInput = new FileInputStream(url);
        return picInput.readAllBytes();

    }

    @RequestMapping(value = "/img", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] previewAvator(int userId) throws IOException {
        String url = filePathDir + Integer.toString(userId) + "/avator.jpg";
        FileInputStream picInput = new FileInputStream(url);
        return picInput.readAllBytes();
    }

    @RequestMapping(value = "/background", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] previewBackground(int userId) throws IOException {
        String url = "file:" + filePathDir + Integer.toString(userId) + "/background.jpg";
        FileInputStream picInput = new FileInputStream(url);
        return picInput.readAllBytes();
    }

}
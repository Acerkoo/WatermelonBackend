package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.utils.Upload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @RequestMapping(value = "/uploadfile/{filename}", method = RequestMethod.POST)
    public boolean uploadInputAndOutput(@PathVariable("filename") String fileName,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("type") String type, @RequestParam("id") int id) {
        if (file != null) {
            String filePath = "/home/admin/problem/" + Integer.toString(id) + "/" + type;
            if (Upload.getInstance().uploadFileInPath(file, fileName, filePath)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/uploadimg/{filename}", method = RequestMethod.POST)
    public boolean uploadUserImg(@PathVariable("filename") String fileName,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("id") int userId) {
        if (file != null) {
            String filePath = "/home/admin/user/Img/" + Integer.toString(userId) + "/";
            if (Upload.getInstance().uploadFileInPath(file, fileName, filePath)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/uploadbackground/{filename}", method = RequestMethod.POST)
    public boolean uploadUserBackGroud(@PathVariable("filename") String fileName,
                                       @RequestParam("file") MultipartFile file,
                                       @RequestParam("id") int userId) {
        if (file != null) {
            String filePath = "/home/admin/user/BackGround/" + Integer.toString(userId) + "/";
            if (Upload.getInstance().uploadFileInPath(file, fileName, filePath)) {
                return true;
            }
        }
        return false;
    }
}
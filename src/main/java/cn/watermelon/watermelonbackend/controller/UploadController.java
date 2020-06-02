package cn.watermelon.watermelonbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
//
//    @Autowired
//    private ImageJob imageJob;
//
//    @RequestMapping(value = "/uploadFile/{filename}", method = RequestMethod.POST)
//    public boolean uploadInputAndOutput(@PathVariable("filename") String fileName,
//                                        @RequestParam("file") MultipartFile file,
//                                        @RequestParam("type") String type,
//                                        @RequestParam("id") int problemId) {
//        return imageJob.uploadInputAndOutput(fileName, file, type, problemId);
//    }
//
//    @RequestMapping(value = "/img", method = RequestMethod.POST)
//    public boolean uploadUserImg(@RequestParam("file") MultipartFile file,
//                                 @RequestParam("userId") int userId) throws IOException {
//        return imageJob.uploadUserImg(file, userId);
//    }
//
//    @RequestMapping(value = "/background", method = RequestMethod.POST)
//    public boolean uploadUserBackGroud(@RequestParam("file") MultipartFile file,
//                                       @RequestParam("id") int userId) throws IOException {
//        return imageJob.uploadUserBackGroud(file, userId);
//    }
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getPic(int userId) throws IOException {
//        return imageJob.getPic(userId);
//    }
//
//    @RequestMapping(value = "/img", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] previewAvator(int userId) throws IOException {
//        return imageJob.previewAvator(userId);
////        String url = filePathDir + Integer.toString(userId) + "/avator.jpg";
////        try {
////            FileInputStream picInput = new FileInputStream(url);
////            return picInput.readAllBytes();
////        } catch (Exception e) {
////            System.out.println(e);
////            return null;
////        }
//    }
//
//    @RequestMapping(value = "/background", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public byte[] previewBackground(int userId) throws IOException {
//        return imageJob.previewBackground(userId);
////        String url = "file:" + filePathDir + Integer.toString(userId) + "/background.jpg";
////        FileInputStream picInput = new FileInputStream(url);
////        return picInput.readAllBytes();
//    }

}
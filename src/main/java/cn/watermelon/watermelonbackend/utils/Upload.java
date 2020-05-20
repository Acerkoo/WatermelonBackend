package cn.watermelon.watermelonbackend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Upload {

    private static volatile Upload upload;

    private Upload() {
    }

    public static Upload getInstance() {
        if (upload == null) {
            synchronized (Upload.class) {
                if (upload == null) {
                    upload = new Upload();
                }
            }
        }
        return upload;
    }

    public static boolean uploadFileInPath(MultipartFile file, String fileName, String path) {
        if (file.isEmpty()) return false;
        File dest = new File(path + File.separator + fileName);
        try {
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

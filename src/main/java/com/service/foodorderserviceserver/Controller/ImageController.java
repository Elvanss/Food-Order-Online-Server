package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.Entity.Image;
import com.service.foodorderserviceserver.Service.ImageService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {

    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/photos/";
    private static String imageURL = "/photos/";

    private ImageService imageService;

    @GetMapping("/")
    public ResponseEntity<?> getList() {
        List<Image> listImage = imageService.getAllImages();

        return  ResponseEntity.ok(listImage);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getListByUser(@PathVariable("id") Integer userId) {
        List<Image> listImage = imageService.getListImageByUserId(userId);

        return ResponseEntity.ok(listImage);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws BadRequestException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BadRequestException("This file format is not supported");
            }
            try {
                Image img = new Image();
                img.setName(file.getName());
                img.setSize(file.getSize());
                img.setType(extension);

                String uid = UUID.randomUUID().toString();
                String link = UPLOAD_DIR + uid + "." + extension;
                String url = imageURL + uid + "." + extension;

                img.setFilepath(url);

                File serverFile = new File(link);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                imageService.saveImage(img);
                return ResponseEntity.ok(img);
            }
            catch (Exception e) {
                throw new BadRequestException("Error Uploading File");
            }
        }

        throw new BadRequestException("Invalid File");
    }

}

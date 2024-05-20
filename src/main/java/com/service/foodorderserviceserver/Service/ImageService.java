package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Image;
import com.service.foodorderserviceserver.Repository.ImageRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image getImageById(int id) {
        return imageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Image not found"));
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> getListImageByUserId(int userId) {
        return imageRepository.getListImageOfUser(userId);
    }

    public void deleteImageById(int id) {
        imageRepository.deleteById(id);
    }

}

package com.albumApp.Photo;

import com.albumApp.Photo.Photo;
import com.albumApp.Photo.PhotoRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import com.albumApp.Exceptions.ResourceNotFoundException;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    // Get all photos
    @Transactional(readOnly = true)
    public List<Photo> getAllPhotos() {
        return (List<Photo>) photoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Photo> getAllPhotos(Pageable pageable) {
        return photoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Photo getPhotoByIdMetadata(Integer id) {
        Optional<Photo> photo = photoRepository.findById(id);
        if(photo.isPresent()){
            return photo.get();
        }
        else{
            throw new ResourceNotFoundException("Photo not found with id " + id);
        }
    }

    // Get a photo by ID
    @Transactional(readOnly = true)
    public Resource getPhotoById(Integer id) {
        Optional<Photo> photo = photoRepository.findById(id);
        String imagePath = photo.get().getFilePath();
        Path path = Paths.get(imagePath);
        
        if(photo.isPresent()){
            try {
                return new InputStreamResource(new FileInputStream(path.toFile()));
            } catch (Exception e) {
                throw new ResourceNotFoundException("Photo not found with id " + id);
            }
        }
        else{
            throw new ResourceNotFoundException("Photo not found with id " + id);
        }
    }

    // Save a photo
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    // Delete a photo by ID
    public void deletePhotoById(Integer id) {
        photoRepository.deleteById(id);
    }

    // Find photos by title
    public List<Photo> findPhotosByTitle(String title) {
        return photoRepository.findByTitle(title);
    }
}
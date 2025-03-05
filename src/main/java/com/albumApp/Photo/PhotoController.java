package com.albumApp.Photo;

import com.albumApp.Photo.PhotoService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/photos")
public class PhotoController {

    PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos() {
        return new ResponseEntity<>(photoService.getAllPhotos(), HttpStatus.OK);
    }

    @GetMapping("/{id}/metadata")
    public ResponseEntity<Photo> getPhotoMetadata(@PathVariable Integer id) {
        Photo photo = photoService.getPhotoByIdMetadata(id);
        return new ResponseEntity<>(photo, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPhotoById(@PathVariable Integer id) throws IOException{
        Resource photo = photoService.getPhotoById(id); 
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(photo);   
    }
    
}

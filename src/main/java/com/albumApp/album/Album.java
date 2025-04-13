package com.albumApp.album;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.albumApp.user.User;
import com.albumApp.photo.Photo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime uploadDate;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String url;
    
    @ManyToMany(mappedBy = "ownedAlbums")
    private Set<User> owners;

    @OneToMany(mappedBy = "album")
    private Set<Photo> photos;

    @OneToOne
    @JoinColumn(name = "cover_photo_id", referencedColumnName = "id")
    private Photo coverPhoto;

    @OneToOne
    @JoinColumn(name = "background_photo_id", referencedColumnName = "id")
    private Photo backgroundPhoto;

    //JPA constructor
    public Album() {}

    public Album(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Set<Photo> getGalleryPhotos(){
        return photos.stream().filter(p -> !p.equals(coverPhoto) && !p.equals(backgroundPhoto)).collect(Collectors.toSet());
    }

    //GETTERS AND SETTERS
    //GETTERS
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
} 

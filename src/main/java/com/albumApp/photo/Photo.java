package com.albumApp.photo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.albumApp.album.Album;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false) 
    private String filePath;

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

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    //JPA constructor
    public Photo() {}

    public Photo(String title, String filePath, String description, String url) {
        this.title = title;
        this.filePath = filePath;
        this.description = description;
        this.url = url;
    }

    //GETTERS AND SETTERS
    //GETTERS
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

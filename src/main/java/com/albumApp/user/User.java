package com.albumApp.user;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.albumApp.album.Album;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.persistence.JoinColumn;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime joinedDate;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String profilePic;

    @ManyToMany
    @JoinTable(
        name = "user_albums",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Album> ownedAlbums;

    //JPA constructor
    public User() {}

    public User(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    //GETTERS AND SETTERS
    //GETTERS
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public String getProfilePic() {
        return profilePic;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
} 

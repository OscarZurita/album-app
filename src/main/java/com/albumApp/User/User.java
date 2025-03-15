package com.albumApp.User;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.albumApp.Album.Album;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime joinedDate;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String profilePic;

    @ManyToMany
    private Set<Album> albums;

    //JPA constructor
    public User() {}

    public User(String name, String description) {
        this.name = name;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
} 

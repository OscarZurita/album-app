package com.albumApp.Photo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    Page<Photo> findAll(Pageable pageable); 
    List<Photo> findByTitle(String title); // Finds photos with the exact title
}

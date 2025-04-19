package com.albumApp.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> findAll(Pageable pageable); 
    Optional<User> findByName(String name); // Finds photos with the exact title
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    User save(User user);
    void deleteById(Integer id);
}

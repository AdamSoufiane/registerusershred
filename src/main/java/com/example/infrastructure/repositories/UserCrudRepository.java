package com.example.infrastructure.repositories;

import com.example.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<UserEntity, Long> {
    @Override
    Optional<UserEntity> findById(Long id);
    
    @Override
    List<UserEntity> findAll();
    
    Optional<UserEntity> findByEmail(String email);
    
    @Override
    void deleteById(Long id);
    
    @Override
    boolean existsById(Long id);
}
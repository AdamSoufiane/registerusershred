package com.example.infrastructure.repositories;

import com.example.domain.entities.UserEntity;
import com.example.domain.exceptions.UserRepositoryException;
import com.example.domain.ports.UserRepositoryPort;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserRepositoryAdapter(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) throws UserRepositoryException {
        try {
            return userRepository.save(userEntity);
        } catch (DataAccessException e) {
            throw new UserRepositoryException("Unable to save user", e);
        }
    }

    @Override
    public Optional<UserEntity> findById(Long id) throws UserRepositoryException {
        try {
            return userRepository.findById(id);
        } catch (DataAccessException e) {
            throw new UserRepositoryException("Unable to find user by ID", e);
        }
    }

    @Override
    public void deleteById(Long id) throws UserRepositoryException {
        try {
            userRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new UserRepositoryException("Unable to delete user by ID", e);
        }
    }

    // The following methods are inherited from the UserRepositoryPort interface
    // and are implemented using the UserCrudRepository methods.

    @Override
    public List<UserEntity> findAll() throws UserRepositoryException {
        try {
            return userRepository.findAll();
        } catch (DataAccessException e) {
            throw new UserRepositoryException("Unable to find all users", e);
        }
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) throws UserRepositoryException {
        try {
            return userRepository.findByEmail(email);
        } catch (DataAccessException e) {
            throw new UserRepositoryException("Unable to find user by email", e);
        }
    }

    @Override
    public boolean existsById(Long id) throws UserRepositoryException {
        try {
            return userRepository.existsById(id);
        } catch (DataAccessException e) {
            throw new UserRepositoryException("Unable to check if user exists by ID", e);
        }
    }
}

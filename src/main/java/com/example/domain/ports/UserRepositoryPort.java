package com.example.domain.ports;

import com.example.domain.entities.UserEntity;
import com.example.domain.exceptions.UserRepositoryException;
import java.util.List;
import java.util.Optional;

/**
 * UserRepositoryPort defines the necessary operations for user persistence within the domain layer,
 * abstracting away the details of data storage.
 */
public interface UserRepositoryPort {

    /**
     * Retrieve a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return an Optional containing the user entity if found
     * @throws UserRepositoryException if there is an issue with the repository operation
     */
    Optional<UserEntity> findById(Long id) throws UserRepositoryException;

    /**
     * Retrieve all user entities from the data storage.
     *
     * @return a list of user entities
     * @throws UserRepositoryException if there is an issue with the repository operation
     */
    List<UserEntity> findAll() throws UserRepositoryException;

    /**
     * Retrieve a user by their email address.
     *
     * @param email the email address of the user
     * @return an Optional containing the user entity if found
     * @throws UserRepositoryException if there is an issue with the repository operation
     */
    Optional<UserEntity> findByEmail(String email) throws UserRepositoryException;

    /**
     * Persist a user entity to the data storage.
     *
     * @param userEntity the user entity to be saved
     * @return the persisted user entity
     * @throws UserRepositoryException if there is an issue with the repository operation
     */
    UserEntity save(UserEntity userEntity) throws UserRepositoryException;

    /**
     * Delete a user entity from the data storage by their unique identifier.
     *
     * @param id the unique identifier of the user to be deleted
     * @throws UserRepositoryException if there is an issue with the repository operation
     */
    void deleteById(Long id) throws UserRepositoryException;

    /**
     * Check if a user entity exists in the data storage by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return true if the user entity exists, false otherwise
     * @throws UserRepositoryException if there is an issue with the repository operation
     */
    boolean existsById(Long id) throws UserRepositoryException;

}
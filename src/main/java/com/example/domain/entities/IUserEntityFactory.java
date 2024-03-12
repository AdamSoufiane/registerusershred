package com.example.domain.entities;

import com.example.domain.exceptions.InvalidUserDetailsException;

public interface IUserEntityFactory {

    UserEntity createUser(String username, String email, String password) throws InvalidUserDetailsException;

}
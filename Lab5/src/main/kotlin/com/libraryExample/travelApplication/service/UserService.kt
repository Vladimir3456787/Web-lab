package com.libraryExample.travelApplication.service

import com.libraryExample.travelApplication.model.Users
import com.libraryExample.travelApplication.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findByEmail(email: String): Users? {
        return userRepository.findByEmail(email)
    }

    //fun save user
    fun save(users: Users): Users {
        return userRepository.save(users)
    }
}
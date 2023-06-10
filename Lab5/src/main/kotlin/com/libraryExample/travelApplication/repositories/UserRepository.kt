package com.libraryExample.travelApplication.repositories

import com.libraryExample.travelApplication.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Int> {
    fun findByEmail(email: String): Users?
}
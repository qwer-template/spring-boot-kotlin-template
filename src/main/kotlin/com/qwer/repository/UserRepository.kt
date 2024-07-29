package com.qwer.repository

import com.qwer.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, String>


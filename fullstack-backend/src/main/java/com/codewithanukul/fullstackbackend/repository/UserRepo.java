package com.codewithanukul.fullstackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithanukul.fullstackbackend.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}

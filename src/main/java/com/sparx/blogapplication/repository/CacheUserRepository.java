package com.sparx.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparx.blogapplication.entities.CacheUser;

public interface CacheUserRepository  extends JpaRepository<CacheUser, String> {

}

package com.sparx.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparx.blogapplication.entities.CaptchaUser;
@Repository
public interface CaptchaUserRepository  extends JpaRepository<CaptchaUser, Integer>{

}

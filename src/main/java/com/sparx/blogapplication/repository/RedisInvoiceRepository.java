package com.sparx.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparx.blogapplication.entities.RedisInvoice;
@Repository
public interface RedisInvoiceRepository extends JpaRepository<RedisInvoice, Integer> {

}

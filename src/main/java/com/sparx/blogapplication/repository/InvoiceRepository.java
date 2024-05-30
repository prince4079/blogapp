package com.sparx.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparx.blogapplication.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}

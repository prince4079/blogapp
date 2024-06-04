package com.sparx.blogapplication.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity	
public class RedisInvoice implements Serializable{
	private static final long serialVersionUID = -4439114469417994311L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invId;
    private String invName;
    private Double invAmount;

}

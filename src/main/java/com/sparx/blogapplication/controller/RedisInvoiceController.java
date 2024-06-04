package com.sparx.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.entities.RedisInvoice;
import com.sparx.blogapplication.service.RedisInvoiceService;

@RestController
@RequestMapping("/redis")
public class RedisInvoiceController {
	@Autowired
	private RedisInvoiceService invoiceService;
	@PostMapping("/saveInv")
    public RedisInvoice saveInvoice(@RequestBody RedisInvoice inv) {
       return invoiceService.saveInvoice(inv);
    }

    @GetMapping("/allInv") 
    public ResponseEntity<List<RedisInvoice>> getAllInvoices(){
       return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/getOne/{id}")
    public RedisInvoice getOneInvoice(@PathVariable Integer id) {
       return invoiceService.getOneInvoice(id);
    }

    @PutMapping("/modify/{id}")
    public RedisInvoice updateInvoice(@RequestBody RedisInvoice inv, @PathVariable Integer id) {
       return invoiceService.updateInvoice(inv, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable Integer id) {
       invoiceService.deleteInvoice(id);
       return "Employee with id: "+id+ " Deleted !";
    }


}

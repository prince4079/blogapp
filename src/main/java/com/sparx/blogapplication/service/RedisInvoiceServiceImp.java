package com.sparx.blogapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sparx.blogapplication.entities.RedisInvoice;
import com.sparx.blogapplication.exception.InvoiceNotFoundException;
import com.sparx.blogapplication.repository.RedisInvoiceRepository;
@Service
public class RedisInvoiceServiceImp implements RedisInvoiceService{
	@Autowired
	private RedisInvoiceRepository invoiceRepo;

    @Override
    @CachePut(value="Invoice", key="#invId")
    public RedisInvoice updateInvoice(RedisInvoice inv, Integer invId) {
    	RedisInvoice invoice = invoiceRepo.findById(invId)
            .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
       invoice.setInvAmount(inv.getInvAmount());
       invoice.setInvName(inv.getInvName());
       return invoiceRepo.save(invoice);
    }

    @Override
    @CacheEvict(value="Invoice", key="#invId")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple records to delete
    public void deleteInvoice(Integer invId) {
    	RedisInvoice invoice = invoiceRepo.findById(invId)
           .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
       invoiceRepo.delete(invoice);
    }

    @Override
    @Cacheable(value="Invoice", key="#invId")
    public RedisInvoice getOneInvoice(Integer invId) {
    	try {
    		Thread.sleep(5000);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	RedisInvoice invoice = invoiceRepo.findById(invId)
         .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
       return invoice;
    }

    @Override
    @Cacheable(value="Invoice")
    public List<RedisInvoice> getAllInvoices() {
       return invoiceRepo.findAll();
    }

	@Override
	public RedisInvoice saveInvoice(RedisInvoice inv) {
		// TODO Auto-generated method stub
	    RedisInvoice invoice= 	invoiceRepo.save(inv);
		return invoice ;
	}

}

package com.sparx.blogapplication.service;

import java.util.List;

import com.sparx.blogapplication.entities.RedisInvoice;

public interface RedisInvoiceService {
	

    public RedisInvoice saveInvoice(RedisInvoice inv);
    public RedisInvoice updateInvoice(RedisInvoice inv, Integer invId);
    public void deleteInvoice(Integer invId);
    public RedisInvoice getOneInvoice(Integer invId);
    public List<RedisInvoice> getAllInvoices();

}

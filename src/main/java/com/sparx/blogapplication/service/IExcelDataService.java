package com.sparx.blogapplication.service;

import java.util.List;

import com.sparx.blogapplication.entities.Invoice;

public interface IExcelDataService {
	List<Invoice> getExcelDataAsList();

	int saveExcelData(List<Invoice> invoices);
}

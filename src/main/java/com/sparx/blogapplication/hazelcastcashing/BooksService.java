package com.sparx.blogapplication.hazelcastcashing;

import org.springframework.stereotype.Service;

@Service
public class BooksService {

	public String getBook(String bookId) {
		return getTheBookInSlowProcessingForCheckingCaching(bookId);
	}

	public String getTheBookInSlowProcessingForCheckingCaching(String bookId) {
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: hale exception
			e.printStackTrace();

		}
		return "sample Book returned";
	}
}
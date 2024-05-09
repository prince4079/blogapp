package com.sparx.blogapplication.hazelcastcashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BooksService bookService;

    @PostMapping("/id/{bookId}")
    public String getBooks(@PathVariable("bookId") String bookId) {
        return bookService.getBook(bookId);
    }
}

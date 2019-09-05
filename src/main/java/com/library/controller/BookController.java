package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.AuthorRatingDTO;
import com.library.dto.BookDTO;
import com.library.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/book/{isbn}")
	public ResponseEntity<?> getBook(@PathVariable("isbn") String isbn) {
		Optional<BookDTO> bookDto = Optional.of(bookService.getBookByIsbn(isbn));
		if (bookDto.isPresent()) {
			return new ResponseEntity<BookDTO>(bookDto.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("No results found", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/category/{categoryName}/books")
	public ResponseEntity<?> getBooksByCategory(@PathVariable("categoryName") String category) {
		return new ResponseEntity<List<BookDTO>>(bookService.getBooksByCategory(category), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getBooksByPhrase(@RequestParam("q") String query) {
		return new ResponseEntity<List<BookDTO>>(bookService.searchBooks(query), HttpStatus.OK);
	}
	
	@GetMapping("/rating")
	public ResponseEntity<?> getAuthorsRatings() {
		return new ResponseEntity<List<AuthorRatingDTO>>(bookService.getAuthorsRatings(), HttpStatus.OK);
	}

}

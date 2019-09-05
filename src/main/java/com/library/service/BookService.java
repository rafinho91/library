package com.library.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.AuthorRatingDTO;
import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.repository.BookSearch;
import com.library.utils.CommonUtils;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookSearch bookSearch;
	
	
	public BookDTO getBookByIsbn(String isbn) {
		return Optional.ofNullable(bookRepository.findByIsbn(isbn))
					.map(BookDTO::new)
					.orElse(null);
	}

	public List<BookDTO> getBooksByCategory(String category) {
		return bookRepository.getBooksByCategory(category.toLowerCase())
				.stream()
				.map(BookDTO::new)
				.collect(Collectors.toList());
	}
	
	public List<BookDTO> searchBooks(String query) {
		return bookSearch.search(query)
				.stream()
				.map(BookDTO::new)
				.collect(Collectors.toList());
	}

	public List<AuthorRatingDTO> getAuthorsRatings() {
		List<Book> booksWithRating = bookRepository.getBooksWithRating();
		Map<String, List<Double>> authorsMap = createAuthorsRatingsMap(booksWithRating);
		List<AuthorRatingDTO> authorRatingDTOs = new ArrayList<>();
		authorsMap
			.forEach((k, v) -> authorRatingDTOs.add(new AuthorRatingDTO(k, CommonUtils.averageOf(v))));
		return authorRatingDTOs;
	}
	

	private Map<String, List<Double>> createAuthorsRatingsMap(List<Book> booksWithRating) {
		Map<String, List<Double>> authorsMap = new HashMap<>();
		for (Book book : booksWithRating) {
			assignRatingToAuthors(authorsMap, book);
		}
		return authorsMap;
	}

	private void assignRatingToAuthors(Map<String, List<Double>> authorsMap, Book book) {
		double bookRating = book.getAverageRating();
		String[] authors = book.getAuthors().split(",");
		for (String author : authors) {
			addBookRatingToAuthor(authorsMap, bookRating, author);
		}
	}

	private void addBookRatingToAuthor(Map<String, List<Double>> authorsMap, double bookRating, String author) {
		if (authorsMap.containsKey(author)) {
			authorsMap.get(author).add(bookRating);
		} else {
			List<Double> authorRating = new ArrayList<>();
			authorRating.add(bookRating);
			authorsMap.put(author, authorRating);
		}
	}
	
}

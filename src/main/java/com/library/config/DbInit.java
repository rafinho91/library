package com.library.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.input.BooksWrapper;
import com.library.model.Book;
import com.library.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DbInit {
	
	@Value("${database.source.name}")
	private String jsonFileName;

	@Autowired
	private BookRepository bookRepository;
	
	@PostConstruct
    private void saveDataFromJsonFile() {
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/" + jsonFileName);
		try {
			BooksWrapper items = mapper.readValue(inputStream, BooksWrapper.class);
			Set<Book> books = items.getItems().stream().map(Book::new).collect(Collectors.toSet());
			bookRepository.saveAll(books);
			log.info("Books list saved successfully");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
    }
}

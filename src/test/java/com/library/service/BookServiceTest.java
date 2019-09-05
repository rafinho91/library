package com.library.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.dto.AuthorRatingDTO;
import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.repository.BookSearch;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	
	@TestConfiguration
    static class BookServiceTestContextConfiguration {
  
        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }
	
	@Autowired
	private BookService bookService;
	
	@MockBean
	private BookRepository bookRepository;
	
	@MockBean
	private BookSearch bookSearch;
	
	private String isbn = "1234567890123";
	private String category = "category";
    private Book book = 
    		Book.builder().isbn(isbn).title("Test Title").categories(category).description("About mushrooms").build();
    private Book book1 = 
    		Book.builder().isbn(isbn).authors("John").averageRating(5.0).build();
    private Book book2 = 
    		Book.builder().isbn(isbn).authors("John").averageRating(2.0).build();
    
	@Before
	public void setUp() {
	    Mockito.when(bookRepository.findByIsbn(isbn)).thenReturn(book);
	    Mockito.when(bookRepository.getBooksByCategory(category)).thenReturn(Arrays.asList(book));
	    Mockito.when(bookSearch.search("mushroom")).thenReturn(Arrays.asList(book));
	    Mockito.when(bookRepository.getBooksWithRating()).thenReturn(Arrays.asList(book1, book2));
	}
	
	@Test
	public void getBookByIsbnTest() {
		BookDTO expected = new BookDTO(book);
		BookDTO result = bookService.getBookByIsbn(isbn);
		
		assertThat(result.getTitle()).isEqualTo(expected.getTitle());
	}
	
	@Test
	public void getBooksByCategoryTest() {
		List<BookDTO> expected = Arrays.asList(new BookDTO(book));
		List<BookDTO> result = bookService.getBooksByCategory(category);
		
		assertThat(result.get(0).getTitle()).isEqualTo(expected.get(0).getTitle());
	}
	
	@Test
	public void searchBooksTest() {
		List<BookDTO> expected = Arrays.asList(new BookDTO(book));
		List<BookDTO> result = bookService.searchBooks("mushroom");
		
		assertThat(result.get(0).getTitle()).isEqualTo(expected.get(0).getTitle());
	}
	
	@Test
	public void getAuthorsRatingsTest() {
		List<AuthorRatingDTO> expected = Arrays.asList(new AuthorRatingDTO("John", 3.5));
		List<AuthorRatingDTO> result = bookService.getAuthorsRatings();
		
		assertThat(result.get(0)).isEqualTo(expected.get(0));
	}

}

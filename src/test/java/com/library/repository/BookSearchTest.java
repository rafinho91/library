package com.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.model.Book;

@Ignore  // TODO doesn't work
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookSearchTest {
	
	@TestConfiguration
    static class BookSearchTestContextConfiguration {
  
        @Bean
        public BookSearch bookSearch() {
            return new BookSearch();
        }
    }

	@Autowired
    private TestEntityManager entityManager;
 
	@Autowired
    private BookSearch bookSearch;
    
    private Book book1 = Book.builder().isbn("isbn11").title("Title one").build();
	private Book book2 = Book.builder().isbn("isbn22").title("title2").build();
	private Book book3 = Book.builder().isbn("isbn33").description("Description number one").build();
    
    @Before
    public void init() {
    	entityManager.persist(book1);
    	entityManager.persist(book2);
    	entityManager.persist(book3);
    	entityManager.flush();
    }
    
    @Test
    public void searchTest() {
        
        List<Book> foundSearchOne = bookSearch.search("one");
        List<Book> foundSearchTitle = bookSearch.search("title");
        
        assertThat(foundSearchOne).isEqualTo(Arrays.asList(book1, book3));
        assertThat(foundSearchTitle).isEqualTo(Arrays.asList(book1));
    }
	
}

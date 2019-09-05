package com.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.model.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private BookRepository bookRepository;
    
    private Book book1 = Book.builder().isbn("isbn1").averageRating(6.0).categories("cat1,cat2,cat3").build();
	private Book book2 = Book.builder().isbn("isbn2").averageRating(5.0).categories("cat1,cat2").build();
	private Book book3 = Book.builder().isbn("isbn3").categories("cat1").build();
    
    @Before
    public void init() {
    	entityManager.persist(book1);
    	entityManager.persist(book2);
    	entityManager.persist(book3);
    	entityManager.flush();
    }
    
    @Test
    public void getBooksByCategoryTest() {
        
        List<Book> foundCat1Books = bookRepository.getBooksByCategory("cat1");
        List<Book> foundCat3Books = bookRepository.getBooksByCategory("cat3");
        List<Book> foundCat4Books = bookRepository.getBooksByCategory("cat4");
        
        assertThat(foundCat1Books).isEqualTo(Arrays.asList(book1, book2, book3));
        assertThat(foundCat3Books).isEqualTo(Arrays.asList(book1));
        assertThat(foundCat4Books).isEqualTo(Collections.emptyList());
    }
    
    @Test
    public void getBooksWithRatingTest() {
    	
        List<Book> foundBooks = bookRepository.getBooksWithRating();
        
        assertThat(foundBooks).isEqualTo(Arrays.asList(book1, book2));
    }
    
    
}

package com.library.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.library.dto.AuthorRatingDTO;
import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private BookService service;
    
    private BookDTO bookTest1 = 
    		new BookDTO(Book.builder().title("Test title1").isbn("0123456789123").description("About mushrooms").build());
    private BookDTO bookTest2 = 
    		new BookDTO(Book.builder().title("Test title2").isbn("1231231231231").categories("Java,Computers").build());
    
    private AuthorRatingDTO author = new AuthorRatingDTO("Test Author1", 6.5);
    private AuthorRatingDTO author2 = new AuthorRatingDTO("Test Author2", 2.3);
    
    @Test
    public void getBookTest() throws Exception {
    	when(service.getBookByIsbn("0123456789123")).thenReturn(bookTest1);
    	
    	this.mvc.perform(get("/api/book/0123456789123"))
    		.andDo(print())
    		.andExpect(status().isOk())
    		.andExpect(content().string(containsString("Test title1")));
    }
    
    @Test
    public void getBooksByCategoryTest() throws Exception {
    	when(service.getBooksByCategory("java")).thenReturn(Arrays.asList(bookTest2));
    	
    	this.mvc.perform(get("/api/category/java/books"))
    		.andDo(print())
    		.andExpect(status().isOk())
    		.andExpect(content().string(containsString("Test title2")));
    }
    
    @Test
    public void getBooksByPhraseTest() throws Exception {
    	when(service.searchBooks("mushroom")).thenReturn(Arrays.asList(bookTest1));
    	
    	this.mvc.perform(get("/api/search?q=mushroom"))
    		.andDo(print())
    		.andExpect(status().isOk())
    		.andExpect(content().string(containsString("title1")));
    }
    
    @Test
    public void getAuthorsRatingsTest() throws Exception {
    	when(service.getAuthorsRatings()).thenReturn(Arrays.asList(author, author2));
    	
    	this.mvc.perform(get("/api/rating"))
    		.andDo(print())
    		.andExpect(status().isOk())
    		.andExpect(content().string(containsString("Author1")))
    		.andExpect(content().string(containsString("2.3")));
    }

}

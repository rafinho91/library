package com.library.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Serializable> {
	
	Book findByIsbn(String isbn);
	
	@Query(nativeQuery = true, 
			value = "SELECT * "
					+ "FROM books b "
					+ "WHERE LOWER(b.categories) regexp CONCAT('^',?1,'$|,',?1,'$|^',?1,',')"
					)
	List<Book> getBooksByCategory(@Param("category") String category);

	@Query("SELECT b FROM Book b WHERE b.averageRating IS NOT NULL")
	List<Book> getBooksWithRating();
	
}

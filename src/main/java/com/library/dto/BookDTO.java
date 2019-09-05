package com.library.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.library.model.Book;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookDTO {
	
	private String isbn;
	private String title;
	private String subtitle;
	private String publisher;
	private Long publishedDate;
	private String description;
	private Integer pageCount;
	private String thumbnailUrl;
	private String language;
	private String previewLink;
	private Double averageRating;
	private String[] authors;
	private String[] categories;
	
	public BookDTO(Book book) {
		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.subtitle = book.getSubtitle();
		this.publisher = book.getPublisher();
		this.publishedDate = transformToLong(book.getPublishedDate());
		this.description = book.getDescription();
		this.pageCount = book.getPageCount();
		this.thumbnailUrl = book.getThumbnailUrl();
		this.language = book.getLanguage();
		this.previewLink = book.getPreviewLink();
		this.averageRating = book.getAverageRating();
		this.authors = book.getAuthors() != null ? 
						book.getAuthors().split(",") : null;
		this.categories = book.getCategories() != null ? 
							book.getCategories().split(",") : null;
	}

	
	private Long transformToLong(String date) {
		if (date != null) {
			return date.length() == 4 ? 
					getLongTimestamp(date + "-01-01") : getLongTimestamp(date);
		}
		return null;
	}

	private long getLongTimestamp(String date) {
		return Timestamp.valueOf(LocalDate.parse(date).atStartOfDay()).getTime();
	}

}

package com.library.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.library.input.BookInput;
import com.library.utils.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Indexed
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Field
	@Column(name = "title")
	private String title;
	
	@Field
	@Column(name = "subtitle")
	private String subtitle;
	
	@Field
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "published_date")
	private String publishedDate;
	
	@Field
	@Column(name = "description", length = 300)
	private String description;
	
	@Column(name = "page_count")
	private Integer pageCount;
	
	@Column(name = "thumbnail_url")
	private String thumbnailUrl;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "preview_link")
	private String previewLink;
	
	@Column(name = "average_rating")
	private Double averageRating;
	
	@Field
	@Column(name = "authors")
	private String authors;
	
	@Field
	@Column(name = "categories")
	private String categories;

	
	public Book (BookInput bookInput) {
		super();
		this.isbn = getMatchingIsbn(bookInput, "ISBN_13") != null ? 
						getMatchingIsbn(bookInput, "ISBN_13") : bookInput.getId();
		this.title = bookInput.getVolumeInfo().getTitle();
		this.subtitle = bookInput.getVolumeInfo().getSubtitle();
		this.publisher = bookInput.getVolumeInfo().getPublisher();
		this.publishedDate = bookInput.getVolumeInfo().getPublishedDate();
		this.description = CommonUtils.getShortenedString(bookInput.getVolumeInfo().getDescription(), 300);
		this.pageCount = bookInput.getVolumeInfo().getPageCount();
		this.thumbnailUrl = bookInput.getVolumeInfo().getImageLink().getThumbnail();
		this.language = bookInput.getVolumeInfo().getLanguage();
		this.previewLink = bookInput.getVolumeInfo().getPreviewLink();
		this.averageRating = bookInput.getVolumeInfo().getAverageRating();
		this.authors = CommonUtils.transformArrayToString(bookInput.getVolumeInfo().getAuthors());
		this.categories = CommonUtils.transformArrayToString(bookInput.getVolumeInfo().getCategories());
	}


	private String getMatchingIsbn(BookInput bookInput, String isbnType) {
		return Arrays.asList(bookInput.getVolumeInfo().getIsbns())
						.stream()
						.filter(i -> isbnType.equals(i.getType()))
						.findFirst()
						.map(i -> i.getIdentifier())
						.orElse(null);
	}
}

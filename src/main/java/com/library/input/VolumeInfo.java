package com.library.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {
	
	@JsonProperty("industryIdentifiers")
	private Isbn[] isbns;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("subtitle")
	private String subtitle;
	
	@JsonProperty("publisher")
	private String publisher;
	
	@JsonProperty("publishedDate")
	private String publishedDate;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("pageCount")
	private Integer pageCount;
	
	@JsonProperty("imageLinks")
	private ImageLink imageLink;
	
	@JsonProperty("language")
	private String language;
	
	@JsonProperty("previewLink")
	private String previewLink;
	
	@JsonProperty("averageRating")
	private Double averageRating;
	
	@JsonProperty("authors")
	private String[] authors;
	
	@JsonProperty("categories")
	private String[] categories;
	
}

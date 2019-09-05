package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorRatingDTO {
	
	private String author;
	private double averageRating;
	
}

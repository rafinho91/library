package com.library.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ImageLink {

	@JsonProperty("smallThumbnail")
	private String smallThumbnail;
	
	@JsonProperty("thumbnail")
	private String thumbnail;
}

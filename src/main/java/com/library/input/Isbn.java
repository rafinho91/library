package com.library.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Isbn {
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("identifier")
	private String identifier;

}

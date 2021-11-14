package com.kaustubh.urlshortner.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UrlErrorResponseDto {
	private String status;
	private String error;

}

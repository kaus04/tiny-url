package com.kaustubh.urlshortner.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UrlResponseDto {
	private String originalUrl;
	private String shortLink;
	private LocalDateTime expirationDate;
}

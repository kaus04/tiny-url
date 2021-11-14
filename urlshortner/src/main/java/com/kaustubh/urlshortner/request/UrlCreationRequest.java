package com.kaustubh.urlshortner.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UrlCreationRequest {
	@NotNull
	private LocalDateTime expirationDate;
	@NotNull
	private String longUrl;
	
	public UrlCreationRequest(final String longUrl, final LocalDateTime expirationDate) {
		this.longUrl = longUrl;
		this.expirationDate = expirationDate;
	}


}

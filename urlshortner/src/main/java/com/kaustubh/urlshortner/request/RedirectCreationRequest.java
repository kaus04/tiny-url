package com.kaustubh.urlshortner.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RedirectCreationRequest {
	@NotNull
	private String alias;
	@NotNull
	private String url;
	
	public RedirectCreationRequest(final String alias, final String url) {
		this.alias = alias;
		this.url = url;
	}


}

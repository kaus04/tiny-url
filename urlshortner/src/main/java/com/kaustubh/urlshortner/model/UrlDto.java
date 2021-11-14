package com.kaustubh.urlshortner.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UrlDto {
		
		private String url;
		private String expirationDate; //optional
}

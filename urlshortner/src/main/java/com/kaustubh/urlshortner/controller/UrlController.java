package com.kaustubh.urlshortner.controller;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaustubh.urlshortner.entity.Url;
import com.kaustubh.urlshortner.model.UrlDto;
import com.kaustubh.urlshortner.model.UrlErrorResponseDto;
import com.kaustubh.urlshortner.model.UrlResponseDto;
import com.kaustubh.urlshortner.service.UrlService;
import com.kaustubh.urlshortner.service.UrlServiceImpl;

@RestController
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody @Valid UrlDto urlDto){
		Url urlToRet = urlService.generateShortLink(urlDto);	
		if(urlToRet != null) {
			UrlResponseDto urlResponseDto = new UrlResponseDto();
			urlResponseDto.setOriginalUrl(urlToRet.getLongUrl());
			urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
			urlResponseDto.setShortLink(urlToRet.getShortLink());
			return new ResponseEntity<UrlResponseDto>(urlResponseDto,HttpStatus.OK);
		}
		UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		urlErrorResponseDto.setStatus("404");
		urlErrorResponseDto.setError("Link not found");
		return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);
	}
	
	  @GetMapping("/{shortLink}") 
	  public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink) throws URISyntaxException{ 
		  UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		  if(StringUtils.isEmpty(shortLink)) {
		  
		  urlErrorResponseDto.setStatus("400");
		  urlErrorResponseDto.setError("Invalid Url");
	  }
	  Url urlToRet = urlService.getEncodedUrl(shortLink);
	  if(urlToRet == null) {
		  urlErrorResponseDto.setStatus("400");
		  urlErrorResponseDto.setError("Url Doesnt exist or expired");  
	  }
	  if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
		  urlErrorResponseDto.setError("URL Expired");
		  urlErrorResponseDto.setStatus("200");
	  }
	  URI uri = new URI(urlToRet.getLongUrl());
	  HttpHeaders httpsHeaders = new HttpHeaders();
	  httpsHeaders.setLocation(uri);
	  
	  
	  return new ResponseEntity<>(httpsHeaders,MOVED_PERMANENTLY); 
	  }
	 
	
}

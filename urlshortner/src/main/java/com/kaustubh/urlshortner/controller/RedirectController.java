package com.kaustubh.urlshortner.controller;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaustubh.urlshortner.entity.Redirect;
import com.kaustubh.urlshortner.request.RedirectCreationRequest;
import com.kaustubh.urlshortner.service.RedirectService;

@RestController
public class RedirectController {
	
	private RedirectService redirectService;
	
	@Autowired
	public RedirectController(RedirectService redirectService) {
		this.redirectService = redirectService;
	}
	
	@GetMapping("/{alias}")
	public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException{
		Redirect redirect = redirectService.getRedirect(alias);
		System.out.println("redirect:"+ redirect);
		URI uri = new URI(redirect.getUrl());
		HttpHeaders httpsHeaders = new HttpHeaders();
		httpsHeaders.setLocation(uri);
		return new ResponseEntity<>(httpsHeaders,MOVED_PERMANENTLY);
	}
	@PostMapping("/")
	public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectCreationRequest redirectCreationRequest){
		return ResponseEntity.ok(redirectService.createRedirect(redirectCreationRequest));
		
	}
}

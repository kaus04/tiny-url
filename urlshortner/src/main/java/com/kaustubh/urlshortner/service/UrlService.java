package com.kaustubh.urlshortner.service;

import org.springframework.stereotype.Service;

import com.kaustubh.urlshortner.entity.Url;
import com.kaustubh.urlshortner.model.UrlDto;

@Service
public interface UrlService {
	public Url generateShortLink(UrlDto urlDto);
	public Url persistShortLink(Url url);
	public Url getEncodedUrl(String url);
	public void deleteShortLink(Url url);
}

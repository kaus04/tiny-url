package com.kaustubh.urlshortner.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaustubh.urlshortner.entity.Redirect;
import com.kaustubh.urlshortner.exception.BadRequestException;
import com.kaustubh.urlshortner.exception.NotFoundException;
import com.kaustubh.urlshortner.repository.RedirectRepository;
import com.kaustubh.urlshortner.request.RedirectCreationRequest;

@Service
public class RedirectService {
	
	private RedirectRepository redirectRepository;
	
	@Autowired
	public RedirectService(RedirectRepository redirectRepository) {
		this.redirectRepository = redirectRepository;
	}
	
	public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest){
		if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias())) {
			throw new BadRequestException("Alais already exists");
		}
		System.out.println("Redirect Request" + redirectCreationRequest.toString());
		Redirect redirect = new Redirect(redirectCreationRequest.getAlias(), redirectCreationRequest.getUrl());
		redirectRepository.save(redirect);
		System.out.println(redirect);
		return Optional.ofNullable(redirect);
	}
	
	public Redirect getRedirect(String alias){
		Redirect redirect = redirectRepository.findByAlias(alias).orElseThrow(()-> new NotFoundException("Alias Not Found"));
		return redirect;

	}

}

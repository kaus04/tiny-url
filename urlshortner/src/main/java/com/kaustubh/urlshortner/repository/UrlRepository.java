package com.kaustubh.urlshortner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.kaustubh.urlshortner.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	public Url findByShortLink(String shortLink);
}

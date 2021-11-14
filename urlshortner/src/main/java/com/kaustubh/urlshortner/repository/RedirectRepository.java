package com.kaustubh.urlshortner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.kaustubh.urlshortner.entity.Redirect;

@Repository
public interface RedirectRepository extends JpaRepository<Redirect, Long> {
	Optional<Redirect> findByAlias(String alias);
	
	Boolean existsByAlias(String alias);

}

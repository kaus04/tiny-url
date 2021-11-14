package com.kaustubh.urlshortner.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
public class Url {
	@Id
	@GeneratedValue
	private long id;
	
	private String shortLink;
	
	@Lob
	private String longUrl;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime expirationDate;
	
}

package com.kaustubh.urlshortner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
public class Redirect {
	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;
	
	@NaturalId
	@Column(unique = true, nullable = false)
	@NotNull
	private String alias;
	
	@Column(nullable = false)
	@NotNull
	private String url;
	
	public Redirect(final String alias, final String url) {
		this.alias = alias;
		this.url  = url;
	}
}

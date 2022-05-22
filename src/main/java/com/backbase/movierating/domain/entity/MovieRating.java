package com.backbase.movierating.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_rating")
public class MovieRating {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private AcademyAwards academyAwards;

	@Column(name = "rate")
	private int rate;

	public MovieRating(AcademyAwards academyAwardsId, int rate) {
		this.academyAwards = academyAwardsId;
		this.rate = rate;
	}

	public MovieRating(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AcademyAwards getAcademyAwardsId() {
		return academyAwards;
	}

	public void setAcademyAwardsId(AcademyAwards academyAwardsId) {
		this.academyAwards = academyAwardsId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}

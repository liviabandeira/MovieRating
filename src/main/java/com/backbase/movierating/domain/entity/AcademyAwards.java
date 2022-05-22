package com.backbase.movierating.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "academy_awards")
public class AcademyAwards {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name = "`Year`")
	private String year;

	@Column(name = "Category")
	private String category;

	@Column(name = "Nominee")
	private String nominee;

	@Column(name = "`Additional Info`")
	private String additionalInfo;

	@Column(name = "`Won?`")
	private boolean won;

	@OneToMany(mappedBy = "academyAwards")
	private List<MovieRating> movieRatings;


	public AcademyAwards(Long id, String year, String category, String nominee, String additionalInfo, boolean won) {
		this.id = id;
		this.year = year;
		this.category = category;
		this.nominee = nominee;
		this.additionalInfo = additionalInfo;
		this.won = won;
	}

	public AcademyAwards() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}
}

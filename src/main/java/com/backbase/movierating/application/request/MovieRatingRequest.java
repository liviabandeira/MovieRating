package com.backbase.movierating.application.request;

public class MovieRatingRequest {
	private String nominee;
	private String category;
	private int rate;

	public MovieRatingRequest(String nominee, String category, int rate) {
		this.nominee = nominee;
		this.category = category;
		this.rate = rate;
	}

	public MovieRatingRequest(){
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}

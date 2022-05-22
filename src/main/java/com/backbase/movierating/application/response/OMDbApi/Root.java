package com.backbase.movierating.application.response.OMDbApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Root{
	@JsonProperty("Title")
	public String title;
	@JsonProperty("Year")
	public String year;
	@JsonProperty("Rated")
	public String rated;
	@JsonProperty("Released")
	public String released;
	@JsonProperty("Runtime")
	public String runtime;
	@JsonProperty("Genre")
	public String genre;
	@JsonProperty("Director")
	public String director;
	@JsonProperty("Writer")
	public String writer;
	@JsonProperty("Actors")
	public String actors;
	@JsonProperty("Plot")
	public String plot;
	@JsonProperty("Language")
	public String language;
	@JsonProperty("Country")
	public String country;
	@JsonProperty("Awards")
	public String awards;
	@JsonProperty("Poster")
	public String poster;
	@JsonProperty("Ratings")
	public ArrayList<Rating> ratings;
	@JsonProperty("Metascore")
	public String metascore;
	public String imdbRating;
	public String imdbVotes;
	public String imdbID;
	@JsonProperty("Type")
	public String type;
	@JsonProperty("DVD")
	public String dVD;
	@JsonProperty("BoxOffice")
	public String boxOffice;
	@JsonProperty("Production")
	public String production;
	@JsonProperty("Website")
	public String website;
	@JsonProperty("Response")
	public String response;

	public Root() {}

	public Root(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String poster, ArrayList<Rating> ratings, String metascore, String imdbRating, String imdbVotes, String imdbID, String type, String dVD, String boxOffice, String production, String website, String response) {
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.awards = awards;
		this.poster = poster;
		this.ratings = ratings;
		this.metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		this.imdbID = imdbID;
		this.type = type;
		this.dVD = dVD;
		this.boxOffice = boxOffice;
		this.production = production;
		this.website = website;
		this.response = response;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getdVD() {
		return dVD;
	}

	public void setdVD(String dVD) {
		this.dVD = dVD;
	}

	public String getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(String boxOffice) {
		this.boxOffice = boxOffice;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}


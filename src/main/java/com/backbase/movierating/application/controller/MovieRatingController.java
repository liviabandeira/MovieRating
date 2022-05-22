package com.backbase.movierating.application.controller;

import com.backbase.movierating.application.request.MovieRatingRequest;
import com.backbase.movierating.domain.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("movie/rating")
public class MovieRatingController {

	@Autowired
	private MovieRatingService movieRatingService;

	@GetMapping(produces = "application/json")
	public Map<String, BigDecimal> top10MovieRated() throws UnsupportedEncodingException {
		return movieRatingService.topMovieNomineeRated();
	}

	@PostMapping(produces = "application/json")
	public void rating(@RequestBody MovieRatingRequest movieRatingRequest){
		movieRatingService.rate(movieRatingRequest);
	}
}

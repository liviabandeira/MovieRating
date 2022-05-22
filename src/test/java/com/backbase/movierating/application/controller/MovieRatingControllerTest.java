package com.backbase.movierating.application.controller;

import com.backbase.movierating.MovieRatingApplication;
import com.backbase.movierating.application.request.MovieRatingRequest;
import com.backbase.movierating.domain.service.MovieRatingService;
import com.backbase.movierating.resource.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MovieRatingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private MovieRatingController movieRatingController;

	@Mock
	private MovieRatingService movieRatingService;

	@Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(movieRatingController).build();
	}

	@After
	public void reset_mocks() {
		Mockito.reset(movieRatingService);
	}

	@Test
	public void awardsAverageTest() throws Exception {
		Map<String, BigDecimal> expected = Map.of("The Hurt Locker", new BigDecimal("8.0"),
				"Trouble the Water", new BigDecimal("8.0"),
				"How to Train Your Dragon", new BigDecimal("8.0"),
				"Inglourious Basterds", new BigDecimal("8.0"),
				"Inception", new BigDecimal("8.0"),
				"Toy Story 3", new BigDecimal("8.0"),
				"Departures", new BigDecimal("8.0"),
				"The King's Speech", new BigDecimal("8.0"),
				"The Social Network", new BigDecimal("8.0"),
				"The Curious Case of Benjamin Button", new BigDecimal("8.0"));

		Mockito.when(movieRatingService.topMovieNomineeRated()).thenReturn(expected);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/movie/rating")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['The Hurt Locker']").value(8.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(10)));
	}

	@Test
	public void ratingTest_success() throws Exception {
		MovieRatingRequest movieRatingRequest = new MovieRatingRequest("The Wolfman","Makeup", 3);
		String json = new Gson().toJson(movieRatingRequest);
		Mockito.doNothing().when(movieRatingService).rate(Mockito.any());

		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
						.post("/movie/rating")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void ratingTest_fail() throws Exception {
		MovieRatingRequest movieRatingRequest = new MovieRatingRequest("The Wolfman","Makeup", 3);
		String json = new Gson().toJson(movieRatingRequest);
		Mockito.doThrow(new NotFoundException("Movie Awards not found")).when(movieRatingService).rate(Mockito.any());

		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
						.post("/movie/rating")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}

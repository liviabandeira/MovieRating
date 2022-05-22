package com.backbase.movierating.application.controller;


import com.backbase.movierating.MovieRatingApplication;
import com.backbase.movierating.domain.entity.AcademyAwards;
import com.backbase.movierating.domain.service.AcademyAwardsService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AcademyAwardsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	AcademyAwardsController academyAwardsController;

	@Mock
	private AcademyAwardsService academyAwardsService;

	@Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(academyAwardsController).build();
	}

	@After
	public void reset_mocks() {
		Mockito.reset(academyAwardsService);
	}

	@Test
	public void awardsBestPictureByNomineeTest_exists() throws Exception {
		AcademyAwards academyAwards = new AcademyAwards();
		academyAwards.setId(1L);
		academyAwards.setYear("2000");
		academyAwards.setNominee("Avatar");

		Mockito.when(academyAwardsService.awardsBestPictureByNominee(Mockito.any())).thenReturn(academyAwards);

		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
						.get("/movie/awards?nominee=Avatar")
						.header("X-Backbase-Api-Key", "test")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void awardsBestPictureByNomineeTest_lost() throws Exception {
		AcademyAwards academyAwards = new AcademyAwards();
		academyAwards.setId(1L);
		academyAwards.setYear("2000");
		academyAwards.setNominee("Avatar");
		academyAwards.setWon(false);

		Mockito.when(academyAwardsService.awardsBestPictureByNominee(Mockito.any())).thenReturn(academyAwards);

		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
						.get("/movie/awards?nominee=Avatar")
						.header("X-Backbase-Api-Key", "test")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.academyAwards.won").value(false));
	}
	@Test
	public void awardsBestPictureByNomineeTest_won() throws Exception {
		AcademyAwards academyAwards = new AcademyAwards();
		academyAwards.setId(1L);
		academyAwards.setYear("2000");
		academyAwards.setNominee("Avatar");
		academyAwards.setWon(true);

		Mockito.when(academyAwardsService.awardsBestPictureByNominee(Mockito.any())).thenReturn(academyAwards);

		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
						.get("/movie/awards?nominee=Avatar")
						.header("X-Backbase-Api-Key", "test")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.academyAwards.won").value(true));
	}
}

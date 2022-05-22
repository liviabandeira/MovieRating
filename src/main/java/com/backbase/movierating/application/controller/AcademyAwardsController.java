package com.backbase.movierating.application.controller;

import com.backbase.movierating.application.response.WonAwardsResponse;
import com.backbase.movierating.domain.entity.AcademyAwards;
import com.backbase.movierating.domain.service.AcademyAwardsService;
import com.backbase.movierating.resource.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie/awards")
public class AcademyAwardsController {
	@Autowired
	private AcademyAwardsService academyAwardsService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<WonAwardsResponse> awardsBestPictureByNominee(@RequestParam String nominee){
		AcademyAwards academyAwards;
		try {
			academyAwards = academyAwardsService.awardsBestPictureByNominee(nominee);
			WonAwardsResponse wonAwardsResponse = new WonAwardsResponse(academyAwards);
			wonAwardsResponse.setAcademyAwards(academyAwards);
			return new ResponseEntity<>(wonAwardsResponse, HttpStatus.OK);
		}catch (NotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
}
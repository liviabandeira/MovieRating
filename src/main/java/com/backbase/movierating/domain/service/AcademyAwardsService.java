package com.backbase.movierating.domain.service;

import com.backbase.movierating.domain.entity.AcademyAwards;
import com.backbase.movierating.resource.exception.NotFoundException;
import com.backbase.movierating.resource.repository.AcademyAwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AcademyAwardsService {

	private static final Logger logger = LogManager.getLogger(AcademyAwardsService.class);

	static final String CATEGORY_BEST_PICTURE = "Best Picture";

	@Autowired
	AcademyAwardsRepository academyAwardsRepository;

	public AcademyAwards awardsBestPictureByNominee(String nominee) {
		logger.debug("Getting awards Best Picture of film", nominee);
		Optional<AcademyAwards> academyAwards =
				academyAwardsRepository.findAcademyAwardsByNomineeAndCategory(nominee,CATEGORY_BEST_PICTURE);
		return academyAwards.orElseThrow(() -> new NotFoundException("Movie Awards not found"));
	}
}


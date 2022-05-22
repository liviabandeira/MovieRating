package com.backbase.movierating.resource.repository;

import com.backbase.movierating.domain.entity.AcademyAwards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademyAwardsRepository extends JpaRepository<AcademyAwards, Long> {

	Optional<AcademyAwards> findAcademyAwardsByNomineeAndCategory(String nominee, String category);
	AcademyAwards findAcademyAwardsById(Long id);
}

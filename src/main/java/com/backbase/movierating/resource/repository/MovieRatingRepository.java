package com.backbase.movierating.resource.repository;

import com.backbase.movierating.domain.entity.AcademyAwards;
import com.backbase.movierating.domain.entity.MovieRating;
import com.backbase.movierating.resource.dto.Top10AcademyAwards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating,Long> {

	@Query("SELECT AVG(e.rate) FROM MovieRating e WHERE e.academyAwards = ?1")
	double findAllByAcademyAwards(AcademyAwards academyAwardsId);

	@Query("SELECT new com.backbase.movierating.resource.dto.Top10AcademyAwards(AVG(e.rate), e.academyAwards) " + "FROM MovieRating e group by e.academyAwards")
	List<Top10AcademyAwards> findTopRating();
}

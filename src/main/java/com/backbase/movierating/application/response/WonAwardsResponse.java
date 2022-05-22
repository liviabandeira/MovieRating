package com.backbase.movierating.application.response;

import com.backbase.movierating.domain.entity.AcademyAwards;

public class WonAwardsResponse {
	private AcademyAwards academyAwards;

	public AcademyAwards getAcademyAwards() {
		return academyAwards;
	}

	public void setAcademyAwards(AcademyAwards academyAwards) {
		this.academyAwards = academyAwards;
	}

	public WonAwardsResponse(AcademyAwards academyAwards) {
		this.academyAwards = academyAwards;
	}
}


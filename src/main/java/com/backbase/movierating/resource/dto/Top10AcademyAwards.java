package com.backbase.movierating.resource.dto;

import com.backbase.movierating.domain.entity.AcademyAwards;

public class Top10AcademyAwards {

	private double rate;
	private AcademyAwards academyAwardsId;

	public Top10AcademyAwards(double rate, AcademyAwards academyAwardsId) {
		this.rate = rate;
		this.academyAwardsId = academyAwardsId;
	}

	public Top10AcademyAwards() {
	}

	public double getAvg() {
		return rate;
	}

	public void setAvg(double avg) {
		this.rate = avg;
	}

	public AcademyAwards getAcademyAwardsId() {
		return academyAwardsId;
	}

	public void setAcademyAwardsId(AcademyAwards academyAwardsId) {
		this.academyAwardsId = academyAwardsId;
	}
}

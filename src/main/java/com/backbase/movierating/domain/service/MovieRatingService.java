package com.backbase.movierating.domain.service;

import com.backbase.movierating.application.request.MovieRatingRequest;
import com.backbase.movierating.application.response.OMDbApi.Root;
import com.backbase.movierating.domain.entity.AcademyAwards;
import com.backbase.movierating.domain.entity.MovieRating;
import com.backbase.movierating.resource.exception.NotFoundException;
import com.backbase.movierating.resource.repository.AcademyAwardsRepository;
import com.backbase.movierating.resource.repository.MovieRatingRepository;
import com.backbase.movierating.resource.dto.Top10AcademyAwards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieRatingService {

	private static final Logger logger = LogManager.getLogger(MovieRatingService.class);

	@Autowired
	MovieRatingRepository movieRatingRepository;

	@Autowired
	AcademyAwardsRepository academyAwardsRepository;

	@Value( "${omdb.api.key}" )
	private String omdbApiKey;

	public void rate(MovieRatingRequest movieRatingRequest) {
		logger.debug("Adding Rate on film", movieRatingRequest.getNominee());
		Optional<AcademyAwards> academyAwards = academyAwardsRepository.
				findAcademyAwardsByNomineeAndCategory(movieRatingRequest.getNominee(), movieRatingRequest.getCategory());
		academyAwards.orElseThrow(() -> new NotFoundException("Movie Awards not found"));

		MovieRating movieRating = new MovieRating(academyAwards.get(),movieRatingRequest.getRate());
		movieRatingRepository.save(movieRating);
	}

	public double average(Long academyAwardsId) {
		logger.debug("Getting average of film", academyAwardsId);
		AcademyAwards academyAwards = new AcademyAwards();
		academyAwards.setId(academyAwardsId);
		return movieRatingRepository.findAllByAcademyAwards(academyAwards);
	}

	public Map<String, BigDecimal> topMovieNomineeRated() throws UnsupportedEncodingException {
		List<Top10AcademyAwards> topRating = movieRatingRepository.findTopRating();
		List<String> nominees = topRating
				.stream()
				.map(topRate -> academyAwardsRepository.findAcademyAwardsById(topRate.getAcademyAwardsId().getId()))
				.map(AcademyAwards::getNominee)
				.collect(Collectors.toList());

		List list = nominees.stream().limit(10).collect(Collectors.toList());
		return topRatedByBoxOffice(list);
	}

	private Map<String, BigDecimal> topRatedByBoxOffice(List<String> nomineeRated) throws UnsupportedEncodingException {
		Map<String, BigDecimal> moviesRating = new HashMap<>();
		for (int i = 0; i < nomineeRated.size(); i++) {
			String encodeUrl = URLEncoder.encode(nomineeRated.get(i), StandardCharsets.UTF_8.toString());
			HttpClient httpClient = HttpClient.newBuilder().build();
			HttpRequest mainRequest =
					HttpRequest.newBuilder()
							.uri(URI.create("http://www.omdbapi.com/?apikey="+ omdbApiKey + "&t=" + encodeUrl))
							.build();

			CompletableFuture<HttpResponse<String>> responseFuture =
					httpClient.sendAsync(mainRequest, HttpResponse.BodyHandlers.ofString());

			responseFuture.whenComplete((response, error) -> {
				if (response != null) {
					ObjectMapper om = new ObjectMapper();
					try {
//						DecimalFormat df = new DecimalFormat("#,###.00");
						Root root = om.readValue(response.body(), Root.class);
						String replace = root.getBoxOffice().replaceAll(",", "");
						String sub = replace.substring(1);
						BigDecimal converted = new BigDecimal(sub);
						//String bigDecimal = df.format(converted);
						moviesRating.put(root.getTitle(), converted);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}
				if (error != null) {
					error.printStackTrace();
				}
			});
			System.out.println(responseFuture.join().body());
		}

		Map sortedMap = newSortMapByValue(moviesRating);

		return sortedMap;
	}

	private static <K extends Comparable<? super K>, V extends Comparable<? super V>> Map<K, V> newSortMapByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				// Check if values are the same
				if (e1.getValue().equals(e2.getValue()))
					// Compare e1 to e2, because A should be first element
					return e1.getKey().compareTo(e2.getKey());
				else
					// Compare e2 to e1, because largest number should be first
					return e2.getValue().compareTo(e1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}

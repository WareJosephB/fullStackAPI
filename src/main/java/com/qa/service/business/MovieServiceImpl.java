package com.qa.service.business;

import javax.inject.Inject;

import com.qa.domain.persistence.Movie;
import com.qa.persistence.repository.MovieRepository;
import com.qa.util.JSONUtil;

public class MovieServiceImpl implements MovieService {

	@Inject
	private MovieRepository repo;
	@Inject
	private JSONUtil util;

	@Override
	public String getAllMovies() {
		return repo.getAllMovies();
	}

	@Override
	public String addMovie(String movie) {
		Movie aMovie = util.getObjectForJSON(movie, Movie.class);

		try {
			if (aMovie.getTitle().contains("Harry Potter") || aMovie.getGenre().equals("Romantic Comedy") || aMovie.getRating().equals("PG"))
				throw new Exception();
		} catch (Exception e) {
			return "{\"message\":\"You tried to add something awful\"}";
		}

		return repo.addMovie(movie);
	}

	@Override
	public String retrieveMovie(Long id) {
		return repo.retrieveMovie(id);
	}

	@Override
	public String deleteMovie(Long id) {
		return repo.deleteMovie(id);
	}
}

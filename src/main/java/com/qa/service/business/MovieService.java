package com.qa.service.business;

public interface MovieService {

	String getAllMovies();

	String addMovie(String movie);

	String retrieveMovie(Long id);

	String deleteMovie(Long id);
}

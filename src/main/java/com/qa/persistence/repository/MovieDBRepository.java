package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.persistence.Movie;
import com.qa.util.JSONUtil;

public class MovieDBRepository implements MovieRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllMovies() {
		Query query = manager.createQuery("Select a FROM Movie a");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(movies);
	}

	@Override
	@Transactional(REQUIRED)
	public String addMovie(String aMovie) {
		Movie movie = util.getObjectForJSON(aMovie, Movie.class);
		manager.persist(movie);
		return "{\"message\": \"movie has been sucessfully added\"}";
	}

	@Override
	public String retrieveMovie(Long id) {
		Movie movie = findMovie(id);
		return util.getJSONForObject(movie);
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		Movie movie = findMovie(id);
		if (movie != null) {
			manager.remove(movie);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

	private Movie findMovie(long id) {
		return manager.find(Movie.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}

package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.business.MovieService;

@Path("/movie")
public class MovieEndpoint {

	@Inject
	MovieService service;

	@Path("/getMovie/All")
	@GET
	@Produces({ "application/json" })
	public String getAllMovies() {
		return service.getAllMovies();
	}

	@Path("/getMovie/{id}")
	@GET
	@Produces({ "application/json" })
	public String getMovie(@PathParam("id") Long id) {
		return service.retrieveMovie(id);
	}

	@Path("/addMovie")
	@POST
	@Produces({ "application/json" })
	public String addMovie(String movie) {
		return service.addMovie(movie);
	}

	@Path("/deleteMovie/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteMovie(@PathParam("id") Long id) {
		return service.deleteMovie(id);
	}

	public void setService(MovieService service) {
		this.service = service;
	}
}

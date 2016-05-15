package com.rex.cxf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.rex.cxf.domain.fruit;

@Path("/fruit")
public interface IFruitService {

	// you can test this using curl from the command line.
	// curl http://localhost/cxf/fruit/
 
	// @Consumes
	@Path("/getAll")
	@GET()
	@Produces({ "application/json" })
	Map<String, Integer> getAllFruits();

	// you can test this using curl from the command line.
	// curl http://localhost/cxf/fruit/pineapple
	@Path("/{fruit}")
	@GET()
	@Produces({ "application/json" })
	Integer getFruitCount(@PathParam("fruit") String fruit);
	// you can test this using curl from the command line.
	// curl -X PUT http://localhost/cxf/fruit/pineapple/3
	@POST()
	@Path("/addFruit")
	void addFruit(@FormParam("fruit") String fruit, @FormParam("count") String count);
	@PUT()
	@Path("/updateFruit/{fruit}/{count}")
	Integer updateFruit(@PathParam("fruit") String name, @PathParam("count") String count);
	
	@PUT()
	@Path("/updateFruitBatch/{warehouse}")
	@Consumes("application/json")
	@Produces({ "application/json" })
	Response updateFruit(@PathParam("warehouse") String warehouse, List<fruit> ls);

}
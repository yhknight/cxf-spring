package com.rex.cxf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.springframework.stereotype.Controller;

import com.rex.cxf.domain.fruit;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * a collection of fruits Test this using curl.
 */
// in beans.xml there is a "/cxf" prefix, all URLs for the services will be at
// "/cxf/fruit"
@Controller
public class FruitService implements IFruitService {

	private static Map<String, Integer> fruitBasket = new ConcurrentHashMap<String, Integer>();
	static{
		fruitBasket.put("apple", 100);
		fruitBasket.put("apple1", 100);
		fruitBasket.put("apple2", 100);
	}

	// you can test this using curl from the command line.
	// curl http://localhost/cxf/fruit/
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.magicmonster.example.jaxsrscxf3spring4.IFruitService#getAllFruits()
	 */
	@Override
	// @Consumes
	public Map<String, Integer> getAllFruits() {

		return fruitBasket;
	}

	// you can test this using curl from the command line.
	// curl http://localhost/cxf/fruit/pineapple
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.magicmonster.example.jaxsrscxf3spring4.IFruitService#getFruitCount(
	 * java.lang.String)
	 */
	@Override
	public Integer getFruitCount(String fruit) {
		Integer integer = fruitBasket.get(fruit);
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	// you can test this using curl from the command line.
	// curl -X PUT http://localhost/cxf/fruit/pineapple/3
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.magicmonster.example.jaxsrscxf3spring4.IFruitService#addFruit(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void addFruit(String fruit,String count) {
		fruitBasket.put(fruit, Integer.parseInt(count));
	}

	@Override
	public Integer updateFruit(String name, String count) {
		// TODO Auto-generated method stub
		
		fruitBasket.put(name, Integer.parseInt(count));
		return fruitBasket.get(name);
		
	}

	@Override
	public Response updateFruit(String warehouse, List<fruit> ls) {
		// TODO Auto-generated method stub
		
		for(fruit f:ls){
			fruitBasket.put(f.getName(), f.getCount());		
		}
		return Response.status(Status.OK).entity(ls.get(0)).build();
	} 
}

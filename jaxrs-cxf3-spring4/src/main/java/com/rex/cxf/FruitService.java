package com.rex.cxf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.rex.cxf.domain.fruit;

/**
 * a collection of fruits Test this using curl.
 */
// in beans.xml there is a "/cxf" prefix, all URLs for the services will be at
// "/cxf/fruit"
@Controller
public class FruitService implements IFruitService {

	private static Map<String, Integer> fruitBasket = new ConcurrentHashMap<String, Integer>();
	static {
		fruitBasket.put("apple", 100);
		fruitBasket.put("apple1", 100);
		fruitBasket.put("apple2", 100);
	}
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	HttpServletRequest request;

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
	public void addFruit(String fruit, String count, HttpServletRequest req) throws Exception {

		if ("lemon".equals(fruit)) {
			throw new Exception("001");
		} else {
			fruitBasket.put(fruit, Integer.parseInt(count));
		}

	}

	@Override
	public Integer updateFruit(String name, String count) {
		// TODO Auto-generated method stub

		fruitBasket.put(name, Integer.parseInt(count));
		return fruitBasket.get(name);

	}

	@Override
	public Response updateFruit(String warehouse, List<fruit> ls, HttpServletRequest req) {
		// TODO Auto-generated method stub

		for (fruit f : ls) {
			fruitBasket.put(f.getName(), f.getCount());
		}
		System.out.println(req.getLocale());
		System.out.println(request.getLocale());
		System.out.println(this.messageSource.getMessage("001", new String[]{"WRONG!!"}, req.getLocale()));
		return Response.status(Status.OK).entity(ls.get(0)).build();
	}
}

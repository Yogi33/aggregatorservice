package com.nagp.assignment.aggregator.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagp.assignment.aggregator.controller.AggregatorController;
import com.nagp.assignment.aggregator.valueobject.OrderListVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Service class to perform Order Details related business logic.
 * 
 * @author Yogendra
 */
@Service
public class AggregatorService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${USER_URL}")
	private String userService;
	@Value("${ORDERS_URL}")
	private String ordersService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AggregatorController.class);

	// For Implementation of Circuit Breaker & Fallback
	@HystrixCommand(fallbackMethod = "fallBackForOrderDetails")
	public HashMap<String, Object> getOrderDetails(int id) {

		HashMap<String, Object> orderDetails = new HashMap<>();

		LOGGER.info("Calling User Service");
		Object userDetails;
		try {
			userDetails = restTemplate.getForObject(userService + id, Object.class);
			orderDetails.put("userDetails", userDetails);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			orderDetails.put("userDetails", "User with ID: " + id + " Not found");
		}

		LOGGER.info("Calling Order Service");
		OrderListVO orders = restTemplate.getForObject(ordersService + id, OrderListVO.class);
		orderDetails.put("orders", orders.getOrders());

		return orderDetails;
	}

	public HashMap<String, Object> fallBackForOrderDetails(int id) {
		System.out.println("Executing fallback method");

		HashMap<String, Object> orderDetails = new HashMap<>();
		orderDetails.put("userDetails", "Fallback Executed");
		orderDetails.put("orders", "Fallback Executed");
		return orderDetails;

	}

}

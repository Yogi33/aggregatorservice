package com.nagp.assignment.aggregator.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.assignment.aggregator.service.AggregatorService;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class AggregatorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AggregatorController.class);

	@Autowired
	AggregatorService aggregatorService;

	@GetMapping(value = "/orderdetails/{id}")
	public ResponseEntity<HashMap<String, Object>> getOrderDetails(
			@RequestHeader(value = "locale", defaultValue = "en") String locale, @PathVariable("id") int id) {

		LOGGER.info("Calling service method to get user and order details");
		HashMap<String, Object> orderDetails = aggregatorService.getOrderDetails(id);

		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}
}

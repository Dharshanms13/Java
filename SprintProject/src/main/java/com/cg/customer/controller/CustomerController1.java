package com.cg.customer.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer1")
@Validated
public class CustomerController1 {
	
	@GetMapping("/msg")
	public String data() {
		return "Hello....!";
	}
}

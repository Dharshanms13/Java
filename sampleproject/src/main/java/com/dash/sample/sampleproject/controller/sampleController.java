package com.dash.sample.sampleproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sampleController {
	
	@GetMapping("/sampleString")
	public String getSampleString() {
		return "This is a sample string";
	}

}

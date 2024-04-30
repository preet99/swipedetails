package com.employee.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.attendance.config.MessageProducer;
import com.employee.attendance.service.SwipeDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class SwipeInOutController {
	
	@Autowired
	private MessageProducer messageProducer;
	
	@Autowired
	private SwipeDetailService swipeDetailService;

	@PostMapping("/swipeIn")
	public String swipeIn(@RequestParam long employeeId )
	{
		
		swipeDetailService.addSwipeInDetails(employeeId);
		//messageProducer.sendMessage("my_topic", "I am all right");
		return null;
	}
	
	@PostMapping("/swipeOut")
	public String swipeOut(@RequestParam long employeeId )
	{
		
		swipeDetailService.addSwipeOutDetails(employeeId);
		//messageProducer.sendMessage("my_topic", "I am all right");
		return null;
	}
	
	@GetMapping("/totalHoursCalculation")
	public long totalHoursCalculation(@RequestParam long employeeId ) throws JsonProcessingException
	{
		
		long hours =swipeDetailService.getTotalHours(employeeId);
		//messageProducer.sendMessage("my_topic", "I am all right");
		return hours;
	}
	
	
}

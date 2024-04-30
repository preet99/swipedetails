package com.employee.attendance.serviceImpl;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.attendance.Event;
import com.employee.attendance.config.MessageProducer;
import com.employee.attendance.entity.AttendanceData;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.SwipeDetail;
import com.employee.attendance.repository.SwipeDetailRepository;
import com.employee.attendance.service.EmployeeService;
import com.employee.attendance.service.SwipeDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//  @Service marks a Java class that performs some service,
//  such as executing business logic, performing 
//  calculations, and calling external APIs.
@Service 
public class SwipeDetailServiceImpl implements SwipeDetailService {
	
    @Autowired
    SwipeDetailRepository swipeDetailRepository;
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    MessageProducer messageProducer;
    
    @Autowired
    private ObjectMapper objectMapper;

	@Override
	public ResponseEntity<SwipeDetail> addSwipeInDetails(long employeeId) {
		SwipeDetail swipeDetail = new SwipeDetail();
		Optional<Employee> emp =employeeService.findEmployee(employeeId);
		if(emp.isPresent())
		{
	    swipeDetail.setTimeStamp(currentTime());
		swipeDetail.setEmployee(emp.get());
		swipeDetail.setEvent(Event.SWIPE_IN);
		swipeDetailRepository.save(swipeDetail);
		}
		else
		{
			throw new NoSuchElementException("Employee with id " + employeeId + " not found.");
		}
		swipeDetailRepository.save(swipeDetail);
		return ResponseEntity.ok(swipeDetail);
	}

	private String currentTime() {
		LocalDateTime currentTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentTime.format(formatter);
		return formattedDateTime;
	}
	
	@Override
	public ResponseEntity<SwipeDetail> addSwipeOutDetails(long employeeId) {
		SwipeDetail swipeDetail = new SwipeDetail();
		Optional<Employee> emp =employeeService.findEmployee(employeeId);
		if(emp.isPresent())
		{
		swipeDetail.setTimeStamp(currentTime());
		swipeDetail.setEmployee(emp.get());
		swipeDetail.setEvent(Event.SWIPE_OUT);
		swipeDetailRepository.save(swipeDetail);
		
		}
		else
		{
			throw new NoSuchElementException("Employee with id " + employeeId + " not found.");
		}
		return ResponseEntity.ok(swipeDetail);
	}

	@Override
	public long getTotalHours(long employeeId) throws JsonProcessingException {
		
		
		SwipeDetail swipeIn= swipeDetailRepository.findFirstByEvent(Event.SWIPE_IN);
		SwipeDetail swipeOut= swipeDetailRepository.findLastByEvent(Event.SWIPE_OUT);
        Optional<Employee> emp = employeeService.findEmployee(employeeId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime1 = LocalDateTime.parse(swipeIn.getTimeStamp(), formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(swipeOut.getTimeStamp(), formatter);
		
	    Duration duration = Duration.between(dateTime1, dateTime2);
	    AttendanceData attendance = null;
	    if(emp.isPresent())
	    {
	     attendance = new AttendanceData(emp.get().getEmployeeId(), emp.get().getEmployeeName(), duration.toHours());
	    }
	    String json = objectMapper.writeValueAsString(attendance);
        messageProducer.sendMessage("baeldung", json);
		return duration.toHours();
	}
}
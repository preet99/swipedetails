package com.employee.attendance.entity;

import java.util.Optional;

import com.employee.attendance.Event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "swipe_details")
public class SwipeDetail {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private Event event;
	private String timeStamp;
	
	@ManyToOne
	private Employee employee;
	
	public SwipeDetail(Event event, String timeStamp, Employee employee)
	{
		super();
		this.event = event;
		this.timeStamp = timeStamp;
		this.employee = employee;
	}
	
}

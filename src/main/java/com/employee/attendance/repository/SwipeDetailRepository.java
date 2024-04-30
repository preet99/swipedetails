package com.employee.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.attendance.Event;
import com.employee.attendance.entity.SwipeDetail;


@Repository 
public interface SwipeDetailRepository extends JpaRepository<SwipeDetail, Integer>{

	
	 @Query("SELECT s FROM SwipeDetail s WHERE s.event = :event and DATE(s.timeStamp) = CURRENT_DATE ORDER BY s.timeStamp ASC limit 1")
	    SwipeDetail findFirstByEvent(@Param("event") Event event);
	 @Query("SELECT s FROM SwipeDetail s WHERE s.event = :event and DATE(s.timeStamp) = CURRENT_DATE ORDER BY s.timeStamp DESC limit 1")
	    SwipeDetail findLastByEvent(@Param("event") Event event);


}
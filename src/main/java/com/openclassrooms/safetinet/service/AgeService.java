package com.openclassrooms.safetinet.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeService {
	
	public int getAge (String date) {

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate formatterbirthDate = LocalDate.parse(date, formatter);
		Period period = Period.between(formatterbirthDate, now);

		int age = period.getYears();
		return age;
	}
}

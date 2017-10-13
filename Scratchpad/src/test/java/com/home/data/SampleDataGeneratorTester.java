package com.home.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SampleDataGeneratorTester {

	// @Test
	public void testGetPersonList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDepartmentList() {
		SampleDataGenerator.INSTANCE.getDepartmentList().forEach(System.out::println);
	}

	// @Test
	public void testGetEmployeeList() {
		SampleDataGenerator.INSTANCE.getEmployeeList().forEach(System.out::println);
	}

}

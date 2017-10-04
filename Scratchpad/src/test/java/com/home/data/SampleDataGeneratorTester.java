package com.home.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SampleDataGeneratorTester {

	// @Test
	public void testGetPersonList() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetDepartmentList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmployeeList() {
		SampleDataGenerator.getEmployeeList().forEach(System.out::println);
	}

}

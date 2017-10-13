package com.home.data;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.home.pojo.Department;
import com.home.pojo.Employee;
import com.home.pojo.Person;

public enum SampleDataGenerator {

	INSTANCE;

	private final List<Department> departments;
	private final List<Employee> employees;
	{
		departments = Arrays.asList(new Department(1, "DEA", "Drugs Enforcement Agency"),
				new Department(2, "MEDEL", "Medellín Cartel"), new Department(3, "CALI", "Cali Cartel"),
				new Department(4, "PLDC", "Policía Nacional de Colombia"),
				new Department(5, "NOVL", "North Valley Cartel"), new Department(6, "ACIA", "CIA"),
				new Department(7, "AGOV", "U.S Government"), new Department(8, "CGOV", "Columbian Government"),
				new Department(9, "HOME", "House work"));

		Map<String, Department> departmentMap = departments.stream()
				.collect(Collectors.toMap(Department::getAcronym, Function.identity()));

		employees = Arrays.asList(
				new Employee("Javier", "Peña", Date.valueOf("1955-08-28"), departmentMap.get("DEA"), 11351),
				new Employee("Steve", "Murphy", Date.valueOf("1957-05-17"), departmentMap.get("DEA"), 7156),
				new Employee("Horacio", "Carillo", Date.valueOf("1960-10-02"), departmentMap.get("PLDC"), 3125),
				new Employee("Hugo", "Martinez", Date.valueOf("1950-04-12"), departmentMap.get("PLDC"), 5471),
				new Employee("Trujillo", "", Date.valueOf("1972-09-15"), departmentMap.get("PLDC"), 2845),
				new Employee("Pablo", "Escobar", Date.valueOf("1949-01-12"), departmentMap.get("MEDEL"), 4551214),
				new Employee("Gustavo", "Gaviria", Date.valueOf("1950-11-14"), departmentMap.get("MEDEL"), 3417511),
				new Employee("Gonzalo", "Gacha", Date.valueOf("1948-07-01"), departmentMap.get("MEDEL"), 5789612),
				new Employee("Noonan", "", Date.valueOf("1945-12-17"), departmentMap.get("AGOV"), 22147),
				new Employee("Owen", "", Date.valueOf("1955-08-28"), departmentMap.get("ACIA"), 18417),
				new Employee("Pacho", "Herrera", Date.valueOf("1953-01-14"), departmentMap.get("CALI"), 2578963),
				new Employee("Gilberto", "Orejuela", Date.valueOf("1939-01-30"), departmentMap.get("CALI"), 3417511),
				new Employee("José", "Londoño", Date.valueOf("1943-10-01"), departmentMap.get("NOVL"), 3512789),
				new Employee("Diego", "Montoya", Date.valueOf("1958-01-14"), departmentMap.get("NOVL"), 4712563),
				new Employee("Hermilda", "Gaviria", Date.valueOf("1925-03-25"), departmentMap.get("HOME"), 1000),
				new Employee("Abel", "Gaviria", Date.valueOf("1920-11-22"), departmentMap.get("HOME"), 1800));

		departments.stream().forEach(department -> department.setEmployees(employees.stream()
				.filter(employee -> employee.getDepartment().equals(department)).collect(Collectors.toList())));
	}

	public static List<Person> getPersonList() {

		return Collections.unmodifiableList(Arrays.asList(new Person("Jake", "Rockwell", Date.valueOf("1984-12-07")),
				new Person("Max", "Ray", Date.valueOf("1980-01-01")),
				new Person("Ace", "McCloud", Date.valueOf("1987-09-14")),
				new Person("John", "Thunder", Date.valueOf("1983-05-19")),
				new Person("T-Bone", "Chance", Date.valueOf("1984-12-07")),
				new Person("Razor", "Jake", Date.valueOf("1984-12-23")),
				new Person("Speed", "Racer", Date.valueOf("1983-06-06")),
				new Person("Master", "Shifu", Date.valueOf("1950-02-22")),
				new Person("Master", "Oogway", Date.valueOf("1930-11-16")),
				new Person("Master", "Chao", Date.valueOf("1935-08-15")),
				new Person("Tai", "Lung", Date.valueOf("1984-12-07")),
				new Person("Po", "Panda", Date.valueOf("1984-12-31")),
				new Person("Shane", "Peacock", Date.valueOf("1970-01-13")),
				new Person("Master", "Ox", Date.valueOf("1950-07-07")),
				new Person("Master", "Croc", Date.valueOf("1951-03-29"))));
	}

	public List<Department> getDepartmentList() {

		return Collections.unmodifiableList(departments);
	}

	public List<Employee> getEmployeeList() {

		return Collections.unmodifiableList(employees);
	}
}

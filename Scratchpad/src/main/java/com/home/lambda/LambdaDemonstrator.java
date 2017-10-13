package com.home.lambda;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.home.data.SampleDataGenerator;
import com.home.pojo.Department;
import com.home.pojo.Employee;
import com.home.pojo.Person;

public final class LambdaDemonstrator {

	public void demoComparator() {

		LocalDate baseDate = LocalDate.now();

		List<Person> persons = new ArrayList<>(SampleDataGenerator.getPersonList());
		System.out.println(
				"---------------------------------------Persons------------------------------------------------------------");
		persons.forEach(System.out::println);
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		/* Sort persons, eldest first */
		System.out.println("**********Sort persons, eldest first**********");
		persons.sort(Comparator.comparing(Person::getDob, Comparator.naturalOrder()));
		persons.forEach(System.out::println);

		/* Sort persons, youngest first */
		System.out.println("**********Sort persons, youngest first**********");
		persons.sort(Comparator.comparing(Person::getDob, Comparator.reverseOrder()));
		persons.forEach(System.out::println);

		/* Sort persons, by first name, then dob ascending */
		System.out.println("**********Sort persons, by first name, then dob ascending**********");
		persons.sort(
				Comparator.comparing(Person::getFirstName).thenComparing(Person::getDob, Comparator.naturalOrder()));
		persons.forEach(System.out::println);

		/* Sort persons, by first name, then dob descending */
		System.out.println("**********Sort persons, by first name, then dob descending**********");
		persons.sort(
				Comparator.comparing(Person::getFirstName).thenComparing(Person::getDob, Comparator.reverseOrder()));
		persons.forEach(System.out::println);

		System.out.println("**********Find all persons aged more than 60**********");
		/* Find all persons aged more than 60 */
		persons.stream()
				.filter(person -> Period.between(LocalDate.parse(person.getDob().toString()), baseDate).getYears() > 60)
				.forEach(System.out::println);
		;

		System.out.println(
				"----------------------------------------Employees-----------------------------------------------------------");
		List<Employee> employees = new ArrayList<>(SampleDataGenerator.INSTANCE.getEmployeeList());
		employees.forEach(System.out::println);
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		/* Sort employees based on salary, descending */
		System.out.println("**********Sort employees based on salary, ascending**********");
		employees.sort(Comparator.comparingInt(Employee::getSalary));
		employees.forEach(System.out::println);

		/* Sort employees based on salary, descending */
		System.out.println("**********Sort employees based on salary, descending**********");
		employees.sort(Comparator.comparingInt(Employee::getSalary).reversed());
		employees.forEach(System.out::println);

	}

	public void demoGrouping() {

		System.out.println(
				"----------------------------------------Employees-----------------------------------------------------------");
		List<Employee> employees = new ArrayList<>(SampleDataGenerator.INSTANCE.getEmployeeList());
		employees.forEach(System.out::println);
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		/* Department-wise, descending count of employees */
		System.out.println("**********Department-wise, descending count of employees**********");
		employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet()
				.stream().sorted(Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);

	}

	public void demoStreamMethods() {

		System.out.println(
				"----------------------------------------Employees-----------------------------------------------------------");
		List<Employee> employees = new ArrayList<>(SampleDataGenerator.INSTANCE.getEmployeeList());
		employees.forEach(System.out::println);
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		/* boolean allMatch(Predicate<? super T> predicate) */
		System.out.println("##########boolean allMatch(Predicate<? super T> predicate)##########");
		// e.g: Check if all the employees have a minimum salary of 2k
		System.out.println("**********e.g: Check if all the employees have a minimum salary of 2k**********");
		System.out.println(employees.stream().allMatch(employee -> employee.getSalary() > 2000));

		/* boolean anyMatch(Predicate<? super T> predicate) */
		System.out.println("##########boolean anyMatch(Predicate<? super T> predicate)##########");
		// e.g: Check if any of the employees have a salary less than 2k
		System.out.println("**********e.g: Check if any of the employees have a salary less than 2k**********");
		System.out.println(employees.stream().anyMatch(employee -> employee.getSalary() < 2000));

		/* long count() */
		System.out.println("##########long count()##########");
		// e.g: Count the no. of employees
		System.out.println("**********e.g: Count the no. of DEA employees**********");
		System.out.println(
				employees.stream().filter(employee -> employee.getDepartment().getAcronym().equals("DEA")).count());

		/* Stream<T> distinct() */
		System.out.println("##########Stream<T> 	distinct()##########");
		// e.g: Get the distinct departments
		System.out.println("**********e.g: Get the distinct departments**********");
		employees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

		/* Optional<T> findAny() */
		System.out.println("##########Optional<T> findAny()##########");
		Optional<Employee> employee = employees.stream().findAny();
		System.out.println("Found an employee ? " + employee.isPresent());

		List<Department> departments = SampleDataGenerator.INSTANCE.getDepartmentList();

		/*
		 * <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends
		 * R>> mapper)
		 */
		System.out.println(
				"##########<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)##########");
		// e.g: Given a list of departments and each department having several
		// employees, get list of all the employees
		System.out.println(
				"**********e.g: Given a list of departments and each department having several employees, get list of all the employees**********");
		departments.stream().flatMap(department -> department.getEmployees().stream()).forEach(System.out::println);

		/*
		 * IntStream flatMapToInt(Function<? super T,? extends IntStream>
		 * mapper)
		 */
		System.out
				.println("##########IntStream flatMapToInt(Function<? super T,? extends IntStream> mapper)##########");
		// e.g: Given a list of departments and each department having several
		// employees, list all the salaries
		System.out.println(
				"**********e.g: Given a list of departments and each department having several employees, list all the salaries**********");
		departments.stream()
				.flatMapToInt(department -> department.getEmployees().stream().mapToInt(Employee::getSalary))
				.forEach(System.out::println);
			
	}
}

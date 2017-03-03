/**
 * Employee
 * Created on March 1, 2017
 * @author Vinod Pillai <vinodthebest@gmail.com>
 * @version 1.0
 * 
 */
package com.vinod.deptemp;

import java.util.Scanner;

public class Employee {

	private int id;
	private String name;
	private float salary;
	private int age;

	Department dept = new Department();

	public Employee() {
		super();
	}

	public Employee(int id, String name, float salary, int age) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setData() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the ID:");
		this.id = scanner.nextInt();
		System.out.println("Enter the Name:");
		this.name = scanner.next();
		System.out.println("Enter the Age:");
		this.age = scanner.nextInt();
		System.out.println("Enter the Salary:");
		this.salary = scanner.nextInt();
	}
	
	public void updateData() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Name:");
		this.name = scanner.next();
		System.out.println("Enter the Age:");
		this.age = scanner.nextInt();
		System.out.println("Enter the Salary:");
		this.salary = scanner.nextInt();
	}

	public void display() {
		System.out.println("Department Details:");
		System.out.println("ID:" + id);
		System.out.println("Name:" + name);
		System.out.println("Age:" + age);
		System.out.println("Salary:" + salary);
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		if ((obj instanceof Employee)) {
			Employee emp = (Employee) obj;
			if (this.getId() != emp.getId()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {

		return "Employee ID: " + this.getId() + " Name :" + this.getName() + " Salary:" + this.getSalary() + " Age:"
				+ this.getAge();
	}

}

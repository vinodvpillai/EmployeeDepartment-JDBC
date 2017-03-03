/**
 * Department
 * Created on March 1, 2017
 * @author Vinod Pillai <vinodthebest@gmail.com>
 * @version 1.0
 * 
 */
package com.vinod.deptemp;

import java.util.Scanner;

public class Department {

	private int id;
	private String name;
	private String description;

	public Department() {
		super();
	}

	public Department(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setData(){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the ID:");
		this.id=scanner.nextInt();
		System.out.println("Enter the Name:");
		this.name=scanner.next();
		System.out.println("Enter the Description:");
		this.description=scanner.next();
	}
	
	public void display(){
		System.out.println("Department Details:");
		System.out.println("ID:"+id);
		System.out.println("Name:"+name);
		System.out.println("Description:"+description);
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Department)) {
			return false;
		}
		if ((obj instanceof Department)) {
			Department dept = (Department) obj;
			if (this.getId() != dept.getId()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {

		return this.getId();
	}

	@Override
	public String toString() {

		return "Department ID: " + this.getId() + " Name :" + this.getName() + " Description:" + this.getDescription();
	}
}

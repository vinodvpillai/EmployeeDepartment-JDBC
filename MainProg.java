/**
 * MainProg
 * Created on March 1, 2017
 * @author Vinod Pillai <vinodthebest@gmail.com>
 * @version 1.0
 * 
 */
package com.vinod.deptemp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProg {

	public static Department getDepartmentById(int id) {
		Department dept = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root", "admin");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from department where id=" + id);
			while (rs.next()) {
				dept = new Department();
				dept.setId(Integer.parseInt(rs.getString("id")));
				dept.setName(rs.getString("name"));
				dept.setDescription(rs.getString("description"));
			}
			rs.close();
			stat.close();
			con.close();
			return dept;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static Employee getEmployeeById(int id) {
		Employee emp = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root", "admin");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from employee where id=" + id);
			while (rs.next()) {
				emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSalary(Integer.parseInt(rs.getString("salary")));
				emp.setDept(MainProg.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));
			}
			rs.close();
			stat.close();
			con.close();
			return emp;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static void main(String[] args) {

		int operation;

		int employeeoperation;
		int departmentoperation;

		int empback = 0;
		int deptback = 0;
		int id = 0;
		Department dept = null;
		Employee emp = null;
		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("1. Manage Department");
			System.out.println("2. Manage Employee");

			System.out.println("0. Exit");

			System.out.println("Select the Operation:");
			operation = scanner.nextInt();

			switch (operation) {
			case 1:
				deptback = 0;
				departmentoperation = 0;
				while (true) {
					System.out.println("1. Add Department");
					System.out.println("2. Update Department");
					System.out.println("3. Remove Department - ID");
					System.out.println("4. Display all Department");
					System.out.println("0. Back to Main Menu");

					System.out.println("Select the Operation:");
					departmentoperation = scanner.nextInt();

					switch (departmentoperation) {
					case 1:
						// Insert
						dept = new Department();
						dept.setData();
						try {

							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root",
									"admin");

							Statement stat = con.createStatement();

							int rel = stat.executeUpdate("insert into department values(" + dept.getId() + ",'"
									+ dept.getName() + "','" + dept.getDescription() + "')");

							if (rel == 1) {
								System.out.println("\n Record Inserted");

							}

							con.close();
						} catch (Exception e) {
							System.out.println(e);
						}

						break;
					case 2:
						// Update
						dept = null;
						System.out.println("Enter the Department ID:");
						id = scanner.nextInt();
						try {

							dept = MainProg.getDepartmentById(id);

							if (dept != null) {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training",
										"root", "admin");
								Statement stat = con.createStatement();

								System.out.println("Enter the Department Name:");
								dept.setName(scanner.next());
								System.out.println("Enter the Department Description:");
								dept.setDescription(scanner.next());
								int rel = stat.executeUpdate("update department set name='" + dept.getName()
										+ "',description='" + dept.getDescription() + "' where id=" + id);
								if (rel == 1) {
									System.out.println("\n Record Updated");
								}

								stat.close();
								con.close();
								System.out.println(dept);

							} else {
								System.out.println("Record not found!");
							}

						} catch (Exception e) {
							System.out.println(e);
						}
						break;
					case 3:
						// Remove

						dept = null;
						System.out.println("Enter the Department ID:");
						id = scanner.nextInt();
						try {
							dept = MainProg.getDepartmentById(id);
							if (dept != null) {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training",
										"root", "admin");
								Statement stat = con.createStatement();
								int rel = stat.executeUpdate("delete from department where id=" + id);
								if (rel == 1) {
									System.out.println("\n Record Deleted");
								}

								con.close();
								System.out.println(dept);
							} else {
								System.out.println("Record not found!");
							}

						} catch (Exception e) {
							System.out.println(e);
						}

						break;
					case 4:
						// Display All
						dept = null;
						try {

							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root",
									"admin");

							Statement stat = con.createStatement();

							ResultSet rs = stat.executeQuery("select * from department");
							while (rs.next()) {

								dept = new Department();
								dept.setId(Integer.parseInt(rs.getString("id")));
								dept.setName(rs.getString("name"));
								dept.setDescription(rs.getString("description"));

								System.out.println(dept);
							}
							con.close();
						} catch (Exception e) {
							System.out.println(e);
						}
						break;

					case 0:
						deptback = 1;
						break;
					default:
						break;
					}

					if (deptback == 1) {
						break;

					}
				}
				break;

			case 2:
				employeeoperation = 0;
				empback = 0;
				while (true) {
					System.out.println("1. Add Employee");
					System.out.println("2. Update Employee");
					System.out.println("3. Remove Employee - ID");
					System.out.println("4. Display all Employee");
					System.out.println("5. Display all Employee - Department ID");
					System.out.println("0. Back to Main Menu");

					System.out.println("Select the Operation:");
					employeeoperation = scanner.nextInt();

					switch (employeeoperation) {
					case 1:
						// Insert
						ArrayList<Department> deptlist = new ArrayList<>();

						try {

							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root",
									"admin");

							Statement stat = con.createStatement();

							ResultSet rs = stat.executeQuery("select * from department");
							while (rs.next()) {

								dept = new Department();
								dept.setId(Integer.parseInt(rs.getString("id")));
								dept.setName(rs.getString("name"));
								dept.setDescription(rs.getString("description"));

								deptlist.add(dept);
							}

							rs.close();

							for (Department tempdept : deptlist) {
								tempdept.display();

							}
							System.out.println("Enter the Department ID:");
							id = scanner.nextInt();

							dept = new Department();
							dept.setId(id);

							// Found
							if (deptlist.contains(dept)) {

								// Insert
								emp = new Employee();
								emp.setData();
								emp.setDept(dept);
								int rel = stat.executeUpdate(
										"insert into employee values(" + emp.getId() + ",'" + emp.getName() + "',"
												+ emp.getSalary() + "," + emp.getAge() + "," + dept.getId() + ")");
								if (rel == 1) {
									System.out.println("\n Record Inserted");
								}
							}
							stat.close();
							con.close();
						} catch (Exception e) {
							System.out.println(e);
						}

						break;
					case 2:
						// Update
						emp = null;
						System.out.println("Enter the Employee ID:");
						id = scanner.nextInt();
						try {

							emp = MainProg.getEmployeeById(id);

							if (emp != null) {

								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training",
										"root", "admin");
								Statement stat = con.createStatement();
								System.out.println(emp);

								emp.updateData();

								int rel = stat.executeUpdate("update employee set name='" + emp.getName() + "',age="
										+ emp.getAge() + ",salary=" + emp.getSalary() + " where id=" + id);
								if (rel == 1) {
									System.out.println("\n Record Updated");
								}
								stat.close();
								con.close();
								System.out.println(emp);
							} else {
								System.out.println("Record not found!");
							}

						} catch (Exception e) {
							System.out.println(e);
						}
						break;
					case 3:
						// Delete
						emp = null;
						System.out.println("Enter the Employee ID:");
						id = scanner.nextInt();
						try {

							emp = MainProg.getEmployeeById(id);

							if (emp != null) {

								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training",
										"root", "admin");
								Statement stat = con.createStatement();
								System.out.println(emp);

								int rel = stat.executeUpdate("delete from employee where id=" + id);
								if (rel == 1) {
									System.out.println("\n Record Deleted");
								}
								stat.close();
								con.close();
								System.out.println(emp);
							} else {
								System.out.println("Record not found!");
							}

						} catch (Exception e) {
							System.out.println(e);
						}
						break;
					case 4:
						// Display all Employee
						try {

							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root",
									"admin");

							Statement stat = con.createStatement();

							ResultSet rs = stat.executeQuery("select * from employee");
							while (rs.next()) {

								emp = new Employee();
								emp.setId(Integer.parseInt(rs.getString("id")));
								emp.setName(rs.getString("name"));
								emp.setAge(Integer.parseInt(rs.getString("age")));
								emp.setSalary(Integer.parseInt(rs.getString("salary")));

								emp.setDept(MainProg.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));	
								emp.display();
								emp.getDept().display();
							}

							rs.close();
							stat.close();
							con.close();
						} catch (Exception e) {
							System.out.println(e);
						}
						break;
					case 5:
						// Display all Employee - By Department ID
						// Update
						dept = null;
						System.out.println("Enter the Department ID:");
						id = scanner.nextInt();
						try {

							dept = MainProg.getDepartmentById(id);

							if (dept != null) {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_training",
										"root", "admin");

								Statement stat = con.createStatement();

								ResultSet rs = stat.executeQuery("select * from employee where fk_dept="+id);
								while (rs.next()) {

									emp = new Employee();
									emp.setId(Integer.parseInt(rs.getString("id")));
									emp.setName(rs.getString("name"));
									emp.setAge(Integer.parseInt(rs.getString("age")));
									emp.setSalary(Integer.parseInt(rs.getString("salary")));
									emp.setDept(MainProg.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));	
									emp.display();
									emp.getDept().display();
								}

								rs.close();
								stat.close();
								con.close();
							}
						} catch (Exception e) {
							System.out.println(e);
						}
						break;
					case 0:
						empback = 1;
						break;
					default:
						break;
					}

					if (empback == 1) {
						break;

					}
				}
				break;

			case 0:
				System.exit(0);
			}

		}
	}
}

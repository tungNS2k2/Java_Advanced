package com.vti;

import java.util.List;
import com.vti.entity.Department;
import com.vti.repository.DepartmentRepository;

public class Program {
	public static void main(String[] args) {
		// khoi tao repository
		DepartmentRepository departmentRepository = new DepartmentRepository(); 
		
		// view list
		System.out.println("-------------------------View list-------------------------"); 
		List<Department> departments = departmentRepository.getAllDepartments();
		for (Department department : departments) {
			System.out.println(department);
		}
		
		// get department by id
		System.out.println("-------------------------get department by id-----------------------");
		Department getDepartmentByid = departmentRepository.getDepartmentById((short) 2);
		System.out.println(getDepartmentByid);
	}
}

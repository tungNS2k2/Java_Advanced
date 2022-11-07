package com.vti.repository;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Department;
import com.vti.utils.HibernateUtils;

public class DepartmentRepository {
	private HibernateUtils hibernateUtils;
	
	
	public DepartmentRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	
	// view list department
	@SuppressWarnings("unchecked")
	public List<Department> getAllDepartments() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// create hql query
			Query<Department> query = session.createQuery("FROM Department");

			return query.list();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	// get by id function
	public Department getDepartmentById(short id) {
		Session session = null;
		
		
		try {
			// get session
			session = hibernateUtils.openSession();
			
			// get by id
			Department department = session.get(Department.class, id);
			return department;
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
	
	// get department by name function
	public Department getDepartmentByName(String name) {
		Session session = null;
		try {
			// get session
			session = hibernateUtils.openSession();
			
			//// create HQL query
			Query<Department> query = session.createQuery("From Department WHERE name = :nameParameter");
			
			// set parameter
			query.setParameter("nameParameter", name);
			
			Department department = query.uniqueResult();
			return department;
		} finally {
			// TODO: handle finally clause
			if(session != null) {
				session.close();
			}
		}
	}
	
	
	
	
	// create function
	
	public void createDepartment(Department department) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			
			// create
			session.save(department);
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
	
	
//	update function 
//	Cách 1: update tên theo id
	public void updateDepartmentById(short id, String name) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			
			// get đepartment
			Department department = session.load(Department.class, id);
			
			
			// update
			department.setName(name);
			
			session.getTransaction().commit();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
//	cach 2: update department bằng object
	public void updateDepartment(Department department) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			session.beginTransaction();
			
//			update
			session.update(department);
			session.getTransaction().commit();;
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	// delete function
	public void deleteDepartment(short id) {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			
			
			// get department
			Department department = (Department) session.load(Department.class, id);
			
			// delete
			session.delete(department);
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
	
	// check exist by id function
	public boolean isDepartmentExistsById(short id) {
		// get department
		Department department = getDepartmentById(id);
		
		
		// return result
		if(department == null) {
			return false;
		}
		return true;
		
	}
	
	
	// check exists by name function
	public boolean isDepartmentExistsByName(String name) {
		Department department = getDepartmentByName(name);
		
			if(department == null)
				return false;
			return true;
	}
	
	
}

package com.vti.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entity.Department;

public class HibernateUtils {
	private static HibernateUtils instance;
	
	private Configuration confiration;
	private SessionFactory sessionFactory;
	
	
	public static HibernateUtils getInstance() {
		if(null == instance) {
			instance = new HibernateUtils();
		}
		return instance;
	}
	
	
	private HibernateUtils() {
		configure();
	}
	
	
	private void configure() {
//		load configuration
		confiration = new  Configuration();
		confiration.configure("Hibernate.cfg.xml");
		
		
//		add entity
		confiration.addAnnotatedClass(Department.class);
	}
	
	
	private SessionFactory buildSessionFactory() {
		if(null == sessionFactory || sessionFactory.isClosed()) {
			
			ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(confiration.getProperties()).build(); 
			
			sessionFactory = confiration.buildSessionFactory(serviceRegistry);

		}
		return sessionFactory;
	}
	
	
	public void  closedFactory() {
		if(null == sessionFactory && sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}
	
	
	public Session openSession() {
		buildSessionFactory();
		return sessionFactory.openSession();
		
	}
}

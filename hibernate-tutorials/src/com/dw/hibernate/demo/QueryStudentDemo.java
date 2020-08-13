package com.dw.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dw.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java object
			
			
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			//display the students
			displayStudents(theStudents);
			
			//query students : lastName='Thauvin'
			theStudents = session.createQuery("from Student s where s.lastName='Thauvin'").list();
			
			// display the students
			System.out.println("\n\nStudens who have last name of Thauvin");
			displayStudents(theStudents);
			
			//query students: lastName='Payet' OR firstName='Alvaro'
			theStudents = session.createQuery("from Student s where" + " s.lastName='Payet' OR s.firstName='Alvaro'").list();
			System.out.println("\n\nStudens who have last name of Payet OR first name of Alvaro");
			displayStudents(theStudents);
			
			//query students where email like '%digitalwaves.com'
			theStudents = session.createQuery("from Student s where" + " s.email LIKE '%digitalwaves.com' ").list();
			System.out.println("\n\nStudens who have email ends with 'digitalwaves.com'");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}












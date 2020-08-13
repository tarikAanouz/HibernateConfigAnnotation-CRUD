package com.dw.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dw.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId =5;
			
			
			 
			// now get a new session and start transaction 
			session = factory.getCurrentSession();
			session.beginTransaction();		
			
			//retrieve the student based on the id : primary key
			System.out.println("\nGetting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			// delete the student
			//System.out.println("Deleting student: " + myStudent);
			//session.delete(myStudent);  (Student deleted, he's no longer in the database)
			
			//delete student id=2
			System.out.println("Deleting student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit the transaction (updating in the databases)
			session.getTransaction().commit();
			
			
			
		    System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}












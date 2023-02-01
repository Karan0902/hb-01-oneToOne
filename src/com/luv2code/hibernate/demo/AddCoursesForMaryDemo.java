package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
	
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {
//			start a transaction
			session.beginTransaction();
			
//			get the student mary from the database
			int theId = 2;
			Student tempStudent = session.get(Student.class, theId);
			System.out.println("\nLoaded Student: " + tempStudent);
			System.out.println("\nCourses: " + tempStudent.getCourses());
			
//			create more courses
			Course c1 = new Course("Rubik's Cube - How to speed cube");
			Course c2 = new Course("Atari 2600 - Game development");
			
//			add student to courses
			c1.addStudent(tempStudent);
			c2.addStudent(tempStudent);
			
//			save the courses
			System.out.println("\nSaving the Courses ...");
			session.save(c1);
			session.save(c2);
			
//			commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}
}



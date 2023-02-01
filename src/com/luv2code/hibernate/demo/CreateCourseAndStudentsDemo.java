package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

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
			
//			create a course
			Course tempCourse = new Course("Pacman - how to score one million points");
			
			System.out.println("\n\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
//			create the students
			Student s1 = new Student("John", "Doe", "john@luv2code.com");
			Student s2 = new Student("Mary", "Public", "mary@luv2code.com");
//			Student s3 = new Student("John", "Doe", "john@luv2code.com");
			
//			add students to course
			tempCourse.addStudent(s1);
			tempCourse.addStudent(s2);
			
//			save students
			System.out.println("\nSaving the students ...");
			session.save(s1);
			session.save(s2);
			System.out.println("Saved students: " + tempCourse.getStudents());
			
//			commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}

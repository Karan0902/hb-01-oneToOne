package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {
//			add comment
//			start a transaction
			session.beginTransaction();
			
//			option 2: lazy loading: Hibernate query with HQL
			
//			get the instructor from db
			int theId = 1;
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
										+ "JOIN FETCH i.courses "
										+ "where i.id=:theInstructorId", 
							Instructor.class);
			
//			set parameter on query
			query.setParameter("theInstructorId", theId);
			
//			execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			System.out.println("===> Instructor: " + tempInstructor);
						
//			commit the transaction
			session.getTransaction().commit();
			
			session.close();
			System.out.println("\n\nThe session is closed...\n\n");
			
//			get courses
			System.out.println("===> Courses: " + tempInstructor.getCourses());
			
			System.out.println("===> Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}

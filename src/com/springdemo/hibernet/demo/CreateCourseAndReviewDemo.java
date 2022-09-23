package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.Course;
import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;
import com.springdemo.hibernet.demo.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create Session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();

			// create course
			Course tempCourse =
					new Course("CSE");
			
			//add some reviews
			tempCourse.addReview(new Review("Programming is the best"));
			tempCourse.addReview(new Review("Iot is the Hard"));
			tempCourse.addReview(new Review("DDD is the Wierd"));
			
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			//save course in the DB
			session.save(tempCourse);
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}

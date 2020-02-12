package hibernate.demo.secondDB;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            //start transaction
            session.beginTransaction();

           //get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);

            //delete the course
            System.out.println("Deleting course: " + tempCourse);

            session.delete(tempCourse);

            // commit transaction
            System.out.println("Commit transaction");
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {

            // add clean up code

            session.close();

            factory.close();
        }
    }
}

package hibernate.demo.secondDB;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

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

            //get the instructor from DB

            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Instructor " + tempInstructor);

            //get courses for the instructor
            System.out.println("Courses: " + tempInstructor.getCourses());

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

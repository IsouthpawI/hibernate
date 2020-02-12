package hibernate.demo.secondDB;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

            //create some courses

            Course tempCourse1 = new Course("Air guitar - Guide");
            Course tempCourse2 = new Course("The Pinball - Guide");

            //add courses to instructor

            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            //save the courses

            session.save(tempCourse1);
            session.save(tempCourse2);

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

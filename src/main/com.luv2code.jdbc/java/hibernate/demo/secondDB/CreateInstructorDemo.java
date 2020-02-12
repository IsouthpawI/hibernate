package hibernate.demo.secondDB;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            // create the object

            Instructor tempInstructor = new Instructor("Susan", "Pablic", "susan.public@luv2code.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://www.youtube.com",
                    "Video Games!!!");


            // associate the object

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            // save the instructor
            //
            // NOTE : this will also save the details object
            // because of the CascadeType.ALL!!!
            //

            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

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

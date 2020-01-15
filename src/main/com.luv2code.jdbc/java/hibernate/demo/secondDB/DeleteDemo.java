package hibernate.demo.secondDB;

import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            // start transaction

            session.beginTransaction();

            // get instructor by primary key / id

            int theId = 1;

            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor " + tempInstructor);

            // delete instructor

            if(tempInstructor != null ){

                System.out.println("Deleting... " + tempInstructor);
                session.delete(tempInstructor);

            }

            System.out.println("Commit transaction");
            session.getTransaction().commit();

            System.out.println("Done");

        } finally {

        }
    }
}

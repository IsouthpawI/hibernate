package hibernate.demo;

import hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            // query students

            List<Student> theStudents = session.createQuery("from Student").list();

            // display Students

            for (Student tempStudent : theStudents) {
                System.out.println(tempStudent);
            }

            System.out.println("Commit transaction");
            session.getTransaction().commit();

            System.out.println("Done");

        } finally {

        }
    }
}

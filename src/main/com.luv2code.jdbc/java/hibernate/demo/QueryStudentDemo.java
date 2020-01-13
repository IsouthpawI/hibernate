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

            displayStudents(theStudents);

            //query student: lastName='Doe' OR firstName='Daffy'

            theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").list();

            //display the students
            System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
            displayStudents(theStudents);

            //query students where email LIKE '%gmail.com

            theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").list();
            System.out.println("\n\nStudents whose email ends with gmail.com");
            displayStudents(theStudents);

            System.out.println("Commit transaction");
            session.getTransaction().commit();

            System.out.println("Done");

        } finally {

        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}

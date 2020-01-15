package hibernate.demo.secondDB;

import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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

            // get the instructor detail object

            int theId = 3;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            // print the associated instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

            // delete the instructor detail
            System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);

            // remove the associated object reference
            // break bi-directional link

            tempInstructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(tempInstructorDetail);

            // commit transaction
            System.out.println("Commit transaction");
            session.getTransaction().commit();

            System.out.println("Done");

        }

        catch(Exception exc){
            exc.printStackTrace();
        }

        finally {
            // handle connection leak issue
            session.close();

            factory.close();
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "hbstudent";
        String password = "hbstudent";

        try {

            System.out.println("Connecting to: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("CONNECTION OK!");


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CONNECTION FAILED!");
        }
    }
}

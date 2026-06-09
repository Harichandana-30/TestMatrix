import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentService {

    Scanner scanner = new Scanner(System.in);

    public void registerStudent() {

        try {

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO students(name,email,password) VALUES(?,?,?)";

            PreparedStatement pst =
                    connection.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Registered Successfully!");
            }

        } catch (Exception e) {

            System.out.println("Registration Failed!");
            e.printStackTrace();
        }
    }
    public void loginStudent() {

    try {

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM students WHERE email=? AND password=?";

        PreparedStatement pst =
                connection.prepareStatement(query);

        pst.setString(1, email);
        pst.setString(2, password);

        var result = pst.executeQuery();

        if (result.next()) {

            System.out.println("Login Successful!");
            System.out.println("Welcome " +
                    result.getString("name"));

        } else {

            System.out.println("Invalid Email or Password!");
        }

    } catch (Exception e) {

        System.out.println("Login Failed!");
        e.printStackTrace();
    }
}
}
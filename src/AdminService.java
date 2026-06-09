import java.sql.Connection;
import java.util.Scanner;

public class AdminService {

    Scanner scanner = new Scanner(System.in);

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";

    public boolean adminLogin() {

        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME)
                && password.equals(ADMIN_PASSWORD)) {

            System.out.println("Admin Login Successful!");

            adminMenu();

            return true;

        } else {

            System.out.println("Invalid Admin Credentials!");
            return false;
        }
    }

    public void adminMenu() {

        System.out.println("\n===== Admin Menu =====");
        System.out.println("1. Add Question");
        System.out.println("2. View Questions");
        System.out.println("3. Delete Question");
        System.out.print("Enter Choice: ");

        int choice = scanner.nextInt();

        switch (choice) {

    case 1:
        addQuestion();
        break;

    case 2:
        viewQuestions();
        break;

    case 3:
        deleteQuestion();
        break;

    default:
        System.out.println("Invalid Choice");
}
    }

    public void addQuestion() {

        try {

            scanner.nextLine();

            System.out.print("Enter Question: ");
            String question = scanner.nextLine();

            System.out.print("Option 1: ");
            String option1 = scanner.nextLine();

            System.out.print("Option 2: ");
            String option2 = scanner.nextLine();

            System.out.print("Option 3: ");
            String option3 = scanner.nextLine();

            System.out.print("Option 4: ");
            String option4 = scanner.nextLine();

            System.out.print("Correct Answer: ");
            String answer = scanner.nextLine();

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO questions(question, option1, option2, option3, option4, correct_answer) VALUES(?,?,?,?,?,?)";

            var pst =
                    connection.prepareStatement(query);

            pst.setString(1, question);
            pst.setString(2, option1);
            pst.setString(3, option2);
            pst.setString(4, option3);
            pst.setString(5, option4);
            pst.setString(6, answer);

            int rows = pst.executeUpdate();

            if (rows > 0) {

                System.out.println("Question Added Successfully!");
            }

        } catch (Exception e) {

            System.out.println("Failed to Add Question!");
            e.printStackTrace();
        }
    }
    public void viewQuestions() {

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM questions";

        var pst =
                connection.prepareStatement(query);

        var result =
                pst.executeQuery();

        System.out.println("\n===== Questions List =====");

        while (result.next()) {

            System.out.println("\nQuestion ID: "
                    + result.getInt("question_id"));

            System.out.println("Question: "
                    + result.getString("question"));

            System.out.println("1. "
                    + result.getString("option1"));

            System.out.println("2. "
                    + result.getString("option2"));

            System.out.println("3. "
                    + result.getString("option3"));

            System.out.println("4. "
                    + result.getString("option4"));

            System.out.println("Correct Answer: "
                    + result.getString("correct_answer"));
        }

    } catch (Exception e) {

        System.out.println("Failed to View Questions!");
        e.printStackTrace();
    }
}
public void deleteQuestion() {

    try {

        System.out.print("Enter Question ID to Delete: ");
        int questionId = scanner.nextInt();

        Connection connection =
                DBConnection.getConnection();

        String query =
                "DELETE FROM questions WHERE question_id=?";

        var pst =
                connection.prepareStatement(query);

        pst.setInt(1, questionId);

        int rows = pst.executeUpdate();

        if (rows > 0) {

            System.out.println("Question Deleted Successfully!");

        } else {

            System.out.println("Question ID Not Found!");
        }

    } catch (Exception e) {

        System.out.println("Failed to Delete Question!");
        e.printStackTrace();
    }
}
}
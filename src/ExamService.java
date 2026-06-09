import java.sql.Connection;
import java.util.Scanner;

public class ExamService {

    public void startExam() {

        try {

            Scanner scanner = new Scanner(System.in);

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM questions";

            var pst =
                    connection.prepareStatement(query);

            var result =
                    pst.executeQuery();

            System.out.println("\n===== Exam Started =====");

            int questionNumber = 1;
            int score = 0;

            while (result.next()) {

                System.out.println("\nQuestion "
                        + questionNumber);

                System.out.println(
                        result.getString("question"));

                System.out.println("1. "
                        + result.getString("option1"));

                System.out.println("2. "
                        + result.getString("option2"));

                System.out.println("3. "
                        + result.getString("option3"));

                System.out.println("4. "
                        + result.getString("option4"));

                System.out.print(
                        "Enter Your Answer (1-4): ");

                int answer =
                        scanner.nextInt();

                String selectedAnswer = "";

                switch (answer) {

                    case 1:
                        selectedAnswer =
                                result.getString("option1");
                        break;

                    case 2:
                        selectedAnswer =
                                result.getString("option2");
                        break;

                    case 3:
                        selectedAnswer =
                                result.getString("option3");
                        break;

                    case 4:
                        selectedAnswer =
                                result.getString("option4");
                        break;

                    default:
                        System.out.println(
                                "Invalid Choice");
                }

                String correctAnswer =
                        result.getString(
                                "correct_answer");

                if (selectedAnswer.equalsIgnoreCase(
                        correctAnswer)) {

                    score++;
                }

                questionNumber++;
            }

            System.out.println(
                    "\n===== Exam Completed =====");

            System.out.println(
                    "Your Score: " + score);

        } catch (Exception e) {

            System.out.println(
                    "Failed to Start Exam!");

            e.printStackTrace();
        }
    }
}
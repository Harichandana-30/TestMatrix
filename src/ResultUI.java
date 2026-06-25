import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultUI extends JFrame {
int studentId;
  public ResultUI(int studentId,
                String studentName,
                int score,
                int totalQuestions){
this.studentId = studentId;
        setTitle("Exam Result");

        setSize(500,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(18,18,35));

        JLabel title = new JLabel("🎉 Congratulations!");

        title.setBounds(90,30,350,40);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        title.setForeground(
                new Color(215,110,255));

        panel.add(title);

        JLabel completed = new JLabel("Exam Completed");

        completed.setBounds(150,80,200,30);

        completed.setForeground(Color.WHITE);

        completed.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18));

        panel.add(completed);
        JLabel studentLabel =
        new JLabel(studentName);

studentLabel.setBounds(120,115,250,30);

studentLabel.setHorizontalAlignment(
        SwingConstants.CENTER);

studentLabel.setForeground(
        new Color(255,215,0));

studentLabel.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                20));

panel.add(studentLabel);

        JLabel scoreLabel = new JLabel(

                "Score : "

                        + score

                        + " / "

                        + totalQuestions);

        scoreLabel.setBounds(150,150,220,30);

        scoreLabel.setForeground(Color.WHITE);

        scoreLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22));

        panel.add(scoreLabel);

        double percentage =
                (score * 100.0) / totalQuestions;

        JLabel percentageLabel =
                new JLabel(

                        "Percentage : "

                                + String.format("%.2f", percentage)

                                + "%");

        percentageLabel.setBounds(120,200,260,30);

        percentageLabel.setForeground(Color.WHITE);

        percentageLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        panel.add(percentageLabel);
        String currentDate =
        new SimpleDateFormat(
                "dd-MMM-yyyy hh:mm a")
                .format(new Date());

JLabel dateLabel =
        new JLabel(
                "Exam Date : " + currentDate);

dateLabel.setBounds(
        100,290,300,25);

dateLabel.setForeground(
        Color.LIGHT_GRAY);

panel.add(dateLabel);

        String grade;

        if (percentage >= 90)
            grade = "A+";

        else if (percentage >= 80)
            grade = "A";

        else if (percentage >= 70)
            grade = "B";

        else if (percentage >= 60)
            grade = "C";

        else
            grade = "Fail";

        JLabel gradeLabel =
                new JLabel(
                        "Grade : " + grade);

        gradeLabel.setBounds(170,250,180,35);

        gradeLabel.setForeground(
                new Color(255,215,0));

        gradeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24));

        panel.add(gradeLabel);

        JLabel message =
                new JLabel();

        if (percentage >= 80)

            message.setText("Excellent Performance!");

        else if (percentage >= 60)

            message.setText("Good Job!");

        else

            message.setText("Keep Practicing!");

        message.setBounds(120,310,280,30);

        message.setForeground(Color.WHITE);

        message.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18));

        panel.add(message);

       JButton home =
        new JButton("Back to Home");
        JButton history =
        new JButton("View History");
        history.addActionListener(e -> {

    new StudentHistoryUI(studentId);

});

       JButton exit =
        new JButton("Exit");

        home.setBounds(80,380,150,45);
        exit.setBounds(260,380,150,45);

        home.addActionListener(e -> {

    new WelcomeUI();

    dispose();

});

exit.addActionListener(e -> {

    System.exit(0);

});

       panel.add(home);
       panel.add(exit);

        add(panel);

        setVisible(true);

    }

}